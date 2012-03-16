package financeiro.usuario;

import java.util.List;
import java.util.Locale;

import financeiro.categoria.CategoriaRN;
import financeiro.email.mail.Email;
import financeiro.util.DAOFactory;
import financeiro.util.MensagemUtil;
import financeiro.util.RNException;
import financeiro.util.UtilException;

public class UsuarioRN {

	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public void enviarEmailPosCadastramento(Usuario usuario) throws RNException {
		// enviando um e-mail conforme o idioma do usuário
		String[] info = usuario.getIdioma().split("_");
		Locale locale = new Locale(info[0], info[1]);
		String titulo = MensagemUtil.getMensagem(locale, "email_titulo");
		String mensagem = MensagemUtil.getMensagem(locale, "email_mensagem",
				usuario.getNome(), usuario.getLogin(), usuario.getSenha());
		try {
			Email email = new Email();
			email.enviaEmail(null, usuario.getEmail(), titulo, mensagem);

		} catch (UtilException e) {
			throw new RNException(e);
		}
	}

	public Usuario carregar(Integer codigo) {

		return this.usuarioDAO.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) {

		return this.usuarioDAO.buscaPorLogin(login);
	}

	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getCodigo();

		if (codigo == null || codigo == 0) {
			usuario.getPermissao().add("ROLE_USUARIO");
			this.usuarioDAO.salvar(usuario);

			CategoriaRN categoriaRN = new CategoriaRN();
			categoriaRN.salvarEstruturaPadrao(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.excluir(usuario);
		this.usuarioDAO.excluir(usuario);

	}

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}

}
