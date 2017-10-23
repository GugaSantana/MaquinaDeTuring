
public class Regras {
	private String atualEstado, proximoEstado, direcaoCabeca,atualValor, proximoValor;
	//teste
	public Regras(String atualEstado, String atualValor, String proximoEstado, String proximoValor, String direcaoCabeca)
	{
		setAtualEstado(atualEstado);
		setAtualValor(atualValor);
		setProximoEstado(proximoEstado);
		setProximoValor(proximoValor);
		setDirecaoCabeca(direcaoCabeca);
	}
	
	public String getAtualEstado() {
		return atualEstado;
	}
	public void setAtualEstado(String atualEstado) {
		this.atualEstado = atualEstado;
	}
	public String getProximoEstado() {
		return proximoEstado;
	}
	public void setProximoEstado(String proximoEstado) {
		this.proximoEstado = proximoEstado;
	}
	public String getDirecaoCabeca() {
		return direcaoCabeca;
	}
	public void setDirecaoCabeca(String direcaoCabeca) {
		this.direcaoCabeca = direcaoCabeca;
	}
	public String getAtualValor() {
		return atualValor;
	}
	public void setAtualValor(String atualValor) {
		this.atualValor = atualValor;
	}
	public String getProximoValor() {
		return proximoValor;
	}
	public void setProximoValor(String proximoValor) {
		this.proximoValor = proximoValor;
	}
	
}
