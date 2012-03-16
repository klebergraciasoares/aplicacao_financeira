package financeiro.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import financeiro.bolsa.acao.Acao;
import financeiro.bolsa.acao.AcaoRN;
import financeiro.bolsa.acao.AcaoVirtual;
import financeiro.util.ContextoUtil;
import financeiro.util.RNException;
import financeiro.web.util.YahooFinanceUtil;

@ManagedBean
@RequestScoped
public class AcaoBean {

	private AcaoVirtual selecionada = new AcaoVirtual();
	private List<AcaoVirtual> lista = null;
	private String linkCodigoAcao = null;

	private PieChartModel carteiraQuantidade, carteiraValor;

	public AcaoBean() {
		this.carteiraQuantidade = new PieChartModel();
		this.carteiraValor = new PieChartModel();
	}

	public PieChartModel getCarteiraQuantidade() {

		for (AcaoVirtual a : this.getLista()) {
			this.carteiraQuantidade.set(a.getAcao().getSigla(), a.getAcao()
					.getQuantidade());
		}
		return carteiraQuantidade;
	}

	public PieChartModel getCarteiraValor() {
		for(AcaoVirtual a : this.getLista()){
			this.carteiraValor.set(a.getAcao().getSigla(), a.getTotal());
		}
		return carteiraValor;
	}

	public void salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		AcaoRN acaoRN = new AcaoRN();
		Acao acao = this.selecionada.getAcao();
		acao.setSigla(acao.getSigla().toUpperCase());
		acao.setUsuario(contextoBean.getUsuarioLogado());
		acaoRN.salvar(acao);
		this.selecionada = new AcaoVirtual();
		this.lista = null;
	}

	public void excluir() {
		AcaoRN acaoRN = new AcaoRN();
		acaoRN.excluir(this.selecionada.getAcao());
		this.selecionada = new AcaoVirtual();
		this.lista = null;
	}

	private List<AcaoVirtual> recuperarLista() throws RNException {

		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		try {
			AcaoRN acaoRN = new AcaoRN();
			return acaoRN.listarAcaoVirtual(contextoBean.getUsuarioLogado());
		} catch (Exception e) {
			throw new RNException(e);
		}

	}

	public List<AcaoVirtual> getLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (this.lista == null) {
				this.lista = this.recuperarLista();
			}
		} catch (RNException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return this.lista;
	}

	public String getLinkCodigoAcao() {
		AcaoRN acaoRN = new AcaoRN();
		if (this.selecionada != null) {
			this.linkCodigoAcao = acaoRN.montaLinkAcao(this.selecionada
					.getAcao());
		} else {
			this.linkCodigoAcao = YahooFinanceUtil.INDICE_BOVESPA;
		}
		return this.linkCodigoAcao;
	}

	public AcaoVirtual getSelecionada() {
		return this.selecionada;
	}

	public void setLinkCodigoAcao(String linkCodigoAcao) {
		this.linkCodigoAcao = linkCodigoAcao;
	}

	public void setLista(List<AcaoVirtual> lista) {
		this.lista = lista;
	}

	public void setSelecionada(AcaoVirtual selecionada) {
		this.selecionada = selecionada;
	}

}
