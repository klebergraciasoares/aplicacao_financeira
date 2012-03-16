package financeiro.email.mail;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import financeiro.email.autenticacao.AutenticaUsuario;
import financeiro.util.UtilException;

public class Email {
	private  ResourceBundle bundle = ResourceBundle
			.getBundle("financeiro/email/configuracao/email");

	public void enviaEmail(String remetente, String destinatario,
			String assunto, String mensagem) throws UtilException {

		if (remetente == null) {
			remetente = bundle.getString("mail.smtp.user");
		}

		AutenticaUsuario autenticarUsuario = new AutenticaUsuario(
				bundle.getString("mail.smtp.user"),
				bundle.getString("mail.smtp.password"));

		Session session = Session.getDefaultInstance(this.configuracaoEmail(),
				autenticarUsuario);
		// habilita o log das acoes executadas durante o envio do email

		session.setDebug(Boolean.parseBoolean(bundle.getString("mail.debug")));

		try {
			Transport envio = null;
			MimeMessage email = new MimeMessage(session);

			email.setRecipient(Message.RecipientType.TO, new InternetAddress(
					destinatario));
			email.setFrom(new InternetAddress(remetente));
			email.setSubject(assunto);
			email.setContent(mensagem, bundle.getString("mail.type"));
			email.setSentDate(new Date());
			envio = session.getTransport("smtp");
			envio.connect(bundle.getString("mail.smtp.host"),
					bundle.getString("mail.smtp.user"),
					bundle.getString("mail.smtp.password"));
			email.saveChanges();
			envio.sendMessage(email, email.getAllRecipients());
			envio.close();

		} catch (AddressException e) {

			throw new UtilException(e);

		} catch (MessagingException e) {
			throw new UtilException(e);

		}

	}

	public Properties configuracaoEmail() {

		Properties config = new Properties();

		// Configuração adicional para servidor proxy.

		config.setProperty("proxySet", bundle.getString("proxySet"));
		// IP do Servidor Proxy
		config.setProperty("socksProxyHost", bundle.getString("socksProxyHost"));
		// Porta do servidor Proxy
		config.setProperty("socksProxyPort", bundle.getString("socksProxyPort"));

		// define protocolo de envio como SMTP
		config.put("mail.transport.protocol",
				bundle.getString("mail.transport.protocol"));
		config.put("mail.smtp.starttls.enable",
				bundle.getString("mail.smtp.starttls.enable"));

		// servidor SMTP do GMAIL
		config.put("mail.smtp.host", bundle.getString("mail.smtp.host"));

		// ativa autenticacao
		config.put("mail.smtp.auth", bundle.getString("mail.smtp.auth"));

		// conta que esta enviando o email
		config.put("mail.smtp.user", bundle.getString("mail.smtp.user"));

		config.put("mail.debug", bundle.getString("mail.debug"));

		// porta
		config.put("mail.smtp.port", bundle.getString("mail.smtp.port"));

		// mesma porta para o socket
		config.put("mail.smtp.socketFactory.port",
				bundle.getString("mail.smtp.port"));
		config.put("mail.smtp.socketFactory.class",
				bundle.getString("mail.smtp.socketFactory.class"));
		config.put("mail.smtp.socketFactory.fallback",
				bundle.getString("mail.smtp.socketFactory.fallback"));
		return config;
	}
}
