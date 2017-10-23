
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TelaMaquinaTuring extends JFrame implements ActionListener, ChangeListener
{
	private static final long serialVersionUID = 1L;
	
	private JLabel lGatoCabeca, lPassos, lEstado, lPassosTexto, lEstadoTexto;
	private JTextField tFita[];
	private JButton  bBuscarLinguagem, bIniciar, bProximoPasso;
	private JSlider sliVelocidadePassos;
	
	private JPanel pCentro, pNorte, pSul;
	
	private MaquinaTuring maquina;
	private ArrayList<Regras> listaRegras;
	private int posicaoCabeca;
	private boolean fimLinguagem;
	//Velocidade da "Animação" sendo 0 o mais lento e -- o mais rapido
	private int velocidade = 0;
	private int contagemPasso = 0;
	
	public TelaMaquinaTuring()
	{
		super("Maquina de Turing");
		getContentPane();
		setLayout(new BorderLayout(10, 10));

		// Titulo
		JLabel lTitulo = new JLabel("Máquina de Turing");
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lTitulo.setFont(fonteTitulo);
		lTitulo.setAlignmentX(CENTER_ALIGNMENT);
		lTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		Font fonte = new Font("Arial", Font.BOLD, 16);
		lPassosTexto = new JLabel("Passo: ");
		lPassosTexto.setFont(fonte);
		
		lPassos = new JLabel();
		lPassos.setFont(fonte);
		
		lEstadoTexto = new JLabel("Estado: ");
		lEstadoTexto.setFont(fonte);
		
		lEstado = new JLabel();
		lEstado.setFont(fonte);
		
		pNorte = new JPanel(new GridBagLayout());

		pCentro = new JPanel(new GridBagLayout());
		pSul= new JPanel(new GridBagLayout());

		
		lGatoCabeca = new JLabel();
		ImageIcon imagem = new ImageIcon(getClass().getResource("gato.png"));
		lGatoCabeca.setIcon(imagem);
		
		tFita = new JTextField[21];
		
		for(int i = 0; i < 21; i++)
		{
			tFita[i] = new JTextField(2);		
			tFita[i].setFont(tFita[i].getFont().deriveFont(20f));
			tFita[i].setHorizontalAlignment(javax.swing.JTextField.CENTER); 
			tFita[i].setText("-");
		}
		
		bBuscarLinguagem = new JButton("Buscar Linguagem");
		bBuscarLinguagem.addActionListener(this);		
		
		bProximoPasso = new JButton("Proximo Passo");
		bProximoPasso.addActionListener(this);
		bProximoPasso.setEnabled(false);
		
		
		bIniciar = new JButton("Iniciar Automatico");
		bIniciar.addActionListener(this);
		bIniciar.setEnabled(false);
		
		//Configura o slider de velocidade		
		sliVelocidadePassos = new JSlider(JSlider.HORIZONTAL, 0, 12, 0);
		
		sliVelocidadePassos.addChangeListener(this);
		sliVelocidadePassos.setMajorTickSpacing(3);
		sliVelocidadePassos.setPaintTicks(true);	
		
		//Personaliza o Slider para aparecer labels
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new JLabel("Lento") );
		labelTable.put( new Integer(12), new JLabel("Rapido"));
		labelTable.put( new Integer( 6), new JLabel("Medio"));
		sliVelocidadePassos.setLabelTable( labelTable );

		sliVelocidadePassos.setPaintLabels(true);
		
		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.anchor = GridBagConstraints.NORTHWEST;
		
		for(int i = 0; i<21;i++) {
		gBC.gridx = i;//col
		gBC.gridy = 1;//lin
		gBC.insets = new Insets(0, 0, 0, 0);
		pCentro.add(tFita[i], gBC);
		}
		
		gBC.gridx = 10;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 3;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(lGatoCabeca, gBC);
		lGatoCabeca.setLocation(10, lGatoCabeca.getY());
		
//		
//		gBC.gridx = 0;//col
//		gBC.gridy = 1;//lin
//		gBC.gridwidth = 1;
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(lTelefone, gBC);
//		
//		//gBC.fill = GridBagConstraints.WEST;
//		gBC.gridx = 1;//col
//		gBC.gridy = 1;//lin
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(tTelefone, gBC);
//		
//		gBC.gridx = 2;//col
//		gBC.gridy = 1;//lin
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(lCelular, gBC);
//
//		gBC.gridx = 3;//col
//		gBC.gridy = 1;//lin
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(tCelular, gBC);
//		
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.gridx = 0;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bBuscarLinguagem, gBC);
		
		gBC.gridx = 1;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bProximoPasso, gBC);
//		
//			
		gBC.gridx = 2;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bIniciar, gBC);
		
		gBC.gridx = 3;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(sliVelocidadePassos, gBC);
		
		
		gBC.gridx = 0;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pNorte.add(lPassosTexto, gBC);
		
		gBC.gridx = 0;//col
		gBC.gridy = 1;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pNorte.add(lEstadoTexto, gBC);
		
		gBC.gridx = 1;//col
		gBC.gridy = 0;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pNorte.add(lPassos, gBC);
		
		gBC.gridx = 1;//col
		gBC.gridy = 1;//lin
		gBC.gridwidth = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pNorte.add(lEstado, gBC);
		
//		gBC.fill = GridBagConstraints.HORIZONTAL;
//		gBC.gridx = 0;//col
//		gBC.gridy = 2;//lin
//		gBC.gridwidth = 2;
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(bCadastrar, gBC);
//
//		gBC.gridx = 2;//col
//		gBC.gridy = 2;//lin
//		gBC.gridwidth = 2;
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(bCancelar, gBC);
//
//		gBC.gridx = 2;//col
//		gBC.gridy = 3;//lin
//		gBC.gridwidth = 2;
//		gBC.insets = new Insets(5, 5, 5, 5);
//		pCentro.add(bRetomar, gBC);

		//pNorte.add(lTitulo);

		//add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pSul, BorderLayout.NORTH);
		add(pNorte, BorderLayout.SOUTH);
		

		setVisible(true);
		setSize(800, 300);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// setMinimumSize(getSize());
	}

	public static void main(String[] args)
	{
		new TelaMaquinaTuring();
	}

	//--------------------------------------------------------------------------------Eventos dos Botões
	@Override
	public void actionPerformed(ActionEvent e) {
		//Buscar Linguagem
		if(e.getSource() == bBuscarLinguagem){	
			//Limpa a tela atual
			ResetaFitaTela();
			
			maquina = new MaquinaTuring();
		
			posicaoCabeca = 10;
			fimLinguagem = false;
			
			LerArquivoTxt lerLinguagem = new LerArquivoTxt();
			listaRegras = lerLinguagem.ler();

			// Preencher lista valores inseridos
			String valoresFita = JOptionPane.showInputDialog("Informe o valor de entrada");
			maquina.preencherFita(valoresFita);
			
			//Preenche fita da tela
			for(int i = 0; i < valoresFita.length();i++)
			{
				tFita[10+i].setText(valoresFita.substring(i, i+1));
			}		
			
			// Estado inicial
			String estadoAtual = lerLinguagem.getEstadoInicial();
			maquina.setEstadoAtual(estadoAtual);
			

			// maquina.inicioFita(50, lista);
			System.out.println(maquina.mostraFita());
			
			bProximoPasso.setEnabled(true);
			bIniciar.setEnabled(true);
			
		}
		//ProximoPasso
		else if(e.getSource() == bProximoPasso)
		{
			proximoPasso();	
		}
		//Inicio Automatico
		else if(e.getSource() == bIniciar)
		{
			//Bloqueia botão proximo passo
			bProximoPasso.setEnabled(false);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub

					while(!fimLinguagem)
					{
						proximoPasso();
						try {
							Thread.sleep(1100-(velocidade*36));
						} catch (InterruptedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
				}
			}).start();
			
		}
	}
	//--------------------------------------------------Fim Eventos dos Botões
	
	public void cabecaDireita()
	{
		//Movimento gato
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int x=lGatoCabeca.getX();
				int y =lGatoCabeca.getY();
				
				for (int i = 0; i < 36; i++) {
					try {
						Thread.sleep(20-velocidade);
					} catch (InterruptedException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					x += 1;

					lGatoCabeca.setLocation(x, y);

				}
			}
		}).start();
	}
	
	public void cabecaEsquerda()
	{
		//Movimento gato
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int x=lGatoCabeca.getX();
				int y =lGatoCabeca.getY();
				
				for (int i = 0; i < 36; i++) {
					try {
						Thread.sleep(20-velocidade);
					} catch (InterruptedException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					x -= 1;

					lGatoCabeca.setLocation(x, y);

				}
			}
		}).start();
	}
	
	public void proximoPasso()
	{
		for (int i = 0; i < listaRegras.size(); i++) {
			if (listaRegras.get(i).getAtualEstado().equals(maquina.getEstadoAtual())
					&& listaRegras.get(i).getAtualValor().equals(maquina.getValorApontadoCabeca())) {
				// Modifica o valor atual
				String novoValor = listaRegras.get(i).getProximoValor();
				maquina.setValorFitaApontadoCabeca(novoValor);
				tFita[posicaoCabeca].setText(novoValor);
				
				// Verifica pra qual lado vai andar a cabeça
				if (listaRegras.get(i).getDirecaoCabeca().equals("D")) {
					maquina.Direita();
					cabecaDireita();
					posicaoCabeca++;
				} else {
					maquina.Esquerda();
					cabecaEsquerda();
					posicaoCabeca--;
				}

				// Altera o estado atual
				String estado = listaRegras.get(i).getProximoEstado();
				lEstado.setText(estado);
				maquina.setEstadoAtual(estado);
				contagemPasso++;
				lPassos.setText(""+contagemPasso);
				
				System.out.println(maquina.mostraFita());

				return;
			}
		}
		fimLinguagem = true;
	}
	
	public void stateChanged(ChangeEvent e) {
		 JSlider source = (JSlider)e.getSource();
		 
		    if (!source.getValueIsAdjusting()) {
		        int veloci = (int)source.getValue();
		        System.out.println(""+ veloci);
		        velocidade = veloci;
		        
		    }
	}
	
	public void ResetaFitaTela()
	{
		lEstado.setText("");
		lPassos.setText("");
		contagemPasso = 0;
		
		lGatoCabeca.setLocation(379, 35);
		for(int i = 0; i < 21; i++)
		{
			tFita[i].setText("-");			
		}
	}
}
