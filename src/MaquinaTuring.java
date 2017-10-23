import java.util.ArrayList;

public class MaquinaTuring {
	private String estadoAtual;
	private int posicaoCabeca;
	ArrayList<String> fita = new ArrayList<String>();

	public String getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(String estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	public int getPosicaoCabeca() {
		return posicaoCabeca;
	}

	public void setPosicaoCabeca(int posicaoCabeca) {
		this.posicaoCabeca = posicaoCabeca;
	}

	public void Esquerda() {
		posicaoCabeca--;
	}
	
	public void Direita() {
		posicaoCabeca++;		
	}

	
	public void preencherFita(String valores)
	{
		setPosicaoCabeca(50);
		//preenche até a posicao 50 com vazio -> "-"
		for(int i = 0; i < 50; i++)
		{
			fita.add("-");
		}
		
		//preenche com os valores fornecidos pelo usuario
		for(int i = 0; i < valores.length();i++)
		{
			fita.add(valores.substring(i, i+1));
		}		
		
		//preenche mais 50 posições com vazio -> "-"
		for(int i = 0; i < 50; i++)
		{
			fita.add("-");
		}
	}
	
	public String mostraFita() {
		String fitaRetorno = "";
		for (int i = 0; i<fita.size(); i++)
		{		
			fitaRetorno += fita.get(i);
		}
		return fitaRetorno;		
	}
	
	public String getValorApontadoCabeca()
	{
		return fita.get(getPosicaoCabeca());
	}
	
	public void setValorFitaApontadoCabeca(String valor)
	{
		fita.set(getPosicaoCabeca(), valor);
	}
}
