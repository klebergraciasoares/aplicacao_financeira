package financeiro.bolsa.acao;

import java.io.Serializable;

public class AcaoVirtual implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7563724015313341087L;
	private Acao acao = new Acao();
	private float ultimoPreco;
	private float total;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public float getUltimoPreco() {
		return ultimoPreco;
	}

	public void setUltimoPreco(float ultimoPreco) {
		this.ultimoPreco = ultimoPreco;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "AcaoVirtual [acao=" + acao + ", ultimoPreco=" + ultimoPreco
				+ ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result + Float.floatToIntBits(total);
		result = prime * result + Float.floatToIntBits(ultimoPreco);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcaoVirtual other = (AcaoVirtual) obj;
		if (acao == null) {
			if (other.acao != null)
				return false;
		} else if (!acao.equals(other.acao))
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		if (Float.floatToIntBits(ultimoPreco) != Float
				.floatToIntBits(other.ultimoPreco))
			return false;
		return true;
	}

}
