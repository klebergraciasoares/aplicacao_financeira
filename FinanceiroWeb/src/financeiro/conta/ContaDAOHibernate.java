package financeiro.conta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.usuario.Usuario;

public class ContaDAOHibernate implements ContaDAO {

	private Session session;

	@Override
	public void setSession(Session session) {
		this.session = session;

	}

	@Override
	public void salvar(Conta conta) {

		this.session.saveOrUpdate(conta);
	}

	@Override
	public void excluir(Conta conta) {
		this.session.delete(conta);

	}

	@Override
	public Conta carregar(Integer conta) {

		return (Conta) this.session.get(Conta.class, conta);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conta> listar(Usuario usuario) {
		Criteria criteria = this.session.createCriteria(Conta.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.list();
	}

	@Override
	public Conta buscarFavorita(Usuario usuario) {
		Criteria criteria = this.session.createCriteria(Conta.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("favorita", true));
		return (Conta) criteria.uniqueResult();
	}

}
