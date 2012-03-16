package financeiro.lancamento;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import financeiro.conta.Conta;

public interface LancamentoDAO {

	void salvar(Lancamento lancamento);

	void excluir(Lancamento lancamento);

	Lancamento carregar(Integer lancamento);

	List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim);

	float saldo(Conta conta, Date data);

	void setSession(Session session);
}
