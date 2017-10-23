import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.PortableServer.ServantRetentionPolicyValue;

public class LerArquivoTxt {
	
	private String estadoInicial = "";
	
	public String getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(String estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	public ArrayList<Regras> ler() {
		ArrayList<Regras> listaRegras = new ArrayList<Regras>();
		File arquivo = null;
		// --------------------File Chooser---------------------------
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos *.txt", "txt"));
		int i = chooser.showOpenDialog(null);
		if (i == 1) {

		} else {
			arquivo = chooser.getSelectedFile();
//			System.out.println(arquivo.getPath());
//			System.out.println(arquivo.getName());
		}
		// --------------------Fim File Chooser---------------------------

		 File arq = new File(arquivo.getPath());
		
		 try {
		 //Indicamos o arquivo que será lido
		 FileReader fileReader = new FileReader(arq);
		
//		 Criamos o objeto bufferReader que nos
//		  oferece o método de leitura readLine()
		 BufferedReader bufferedReader = new BufferedReader(fileReader);
		
//		 String que irá receber cada linha do arquivo
		 String linha = "";
		
//		 Fazemos um loop linha a linha no arquivo, enquanto ele seja diferente de null.
//		 O método readLine() devolve a linha na posicao do loop para a variavel linha.
		 
		 linha = bufferedReader.readLine();
		 if(linha != null)
		 {
			 linha = linha.replaceAll(" ", "");
			 setEstadoInicial(linha);
		 }
		 while ((linha = bufferedReader.readLine()) != null) {
				// Aqui imprimimos a linha
//				System.out.println(linha);
				linha = linha.replaceAll(" ", "");
//				System.out.println(linha);
				String linhas[] = linha.split("[,=]");
//				System.out.println(linha);
				
				Regras regras = new Regras(linhas[0], linhas[1], linhas[2], linhas[3], linhas[4]);
				listaRegras.add(regras);					
				
		 }
//		 liberamos o fluxo dos objetos ou fechamos o arquivo
		 fileReader.close();
		 bufferedReader.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 
		 //Retorna o array com as regras
		 return listaRegras;
	}

	public static void main(String[] args) {
		LerArquivoTxt lerTxt = new LerArquivoTxt();
		lerTxt.ler();
	}


}
