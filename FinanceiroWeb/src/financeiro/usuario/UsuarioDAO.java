package financeiro.usuario;

import java.util.List;

import org.hibernate.Session;

public interface UsuarioDAO {

	void salvar(Usuario usuario);

	void atualizar(Usuario usuario);

	void excluir(Usuario usuario);

	Usuario carregar(Integer codigo);

	Usuario buscaPorLogin(String login);

	List<Usuario> listar();

	void setSession(Session session);
}
