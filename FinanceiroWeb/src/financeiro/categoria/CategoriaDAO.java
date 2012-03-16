package financeiro.categoria;

import java.util.List;

import org.hibernate.Session;

import financeiro.usuario.Usuario;

public interface CategoriaDAO {

	Categoria salvar(Categoria categoria);

	void excluir(Categoria categoria);

	Categoria carregar(Integer categoria);

	List<Categoria> listar(Usuario usuario);
	
	void setSession(Session session);
}
