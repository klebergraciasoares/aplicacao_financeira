package financeiro.cheque;

import java.util.List;

import org.hibernate.Session;

import financeiro.conta.Conta;

public interface ChequeDAO {

	void setSession(Session session);

	void salvar(Cheque cheque);

	void excluir(Cheque cheque);
	
	Cheque carregar(ChequeId chequeId);

	List<Cheque> listar(Conta conta);

}
