package financeiro.cheque;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.conta.Conta;

public class ChequeDAOHibernate implements ChequeDAO {

	private Session session;

	@Override
	public void setSession(Session session) {
		this.session = session;

	}

	@Override
	public void salvar(Cheque cheque) {
		this.session.merge(cheque);
		this.session.flush();
		this.session.clear();
	}

	@Override
	public void excluir(Cheque cheque) {

		this.session.delete(cheque);
		this.session.flush();
		this.session.clear();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cheque> listar(Conta conta) {
		Criteria criteria = this.session.createCriteria(Cheque.class);
		criteria.add(Restrictions.eq("conta", conta));
		return criteria.list();
	}

	@Override
	public Cheque carregar(ChequeId chequeId) {

		return (Cheque) this.session.get(Cheque.class, chequeId);
	}

}
