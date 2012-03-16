package financeiro.util;

import financeiro.bolsa.acao.AcaoDAO;
import financeiro.bolsa.acao.AcaoDAOHibernate;
import financeiro.categoria.CategoriaDAO;
import financeiro.categoria.CategoriaDAOHibernate;
import financeiro.cheque.ChequeDAO;
import financeiro.cheque.ChequeDAOHibernate;
import financeiro.conta.ContaDAO;
import financeiro.conta.ContaDAOHibernate;
import financeiro.lancamento.LancamentoDAO;
import financeiro.lancamento.LancamentoDAOHibernate;
import financeiro.usuario.UsuarioDAO;
import financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAO usuarioDao = new UsuarioDAOHibernate();
		usuarioDao.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return usuarioDao;
	}

	public static ContaDAO criarContaDAO() {
		ContaDAO contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return contaDAO;
	}

	public static CategoriaDAO criarCategoriaDAO() {
		CategoriaDAO categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return categoriaDAO;
	}

	public static LancamentoDAO criarLancamentoDAO() {
		LancamentoDAO lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return lancamentoDAO;
	}

	public static ChequeDAO criarChequeDAO() {
		ChequeDAO chequeDAO = new ChequeDAOHibernate();
		chequeDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());

		return chequeDAO;
	}

	public static AcaoDAO criarAcaoDAO() {
		AcaoDAO acaoDAO = new AcaoDAOHibernate();
		acaoDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return acaoDAO;
	}
}
