package financeiro.conta;

import java.util.List;

import org.hibernate.Session;

import financeiro.usuario.Usuario;

public interface ContaDAO {

	void salvar(Conta conta);

	void excluir(Conta conta);

	Conta carregar(Integer conta);

	List<Conta> listar(Usuario usuario);

	Conta buscarFavorita(Usuario usuario);

	void setSession(Session session);
}
