package visual;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entidades.Frete;
import entidades.Perfil;
import servicos.ArquivadorDadosFrete;
import servicos.BancoDeDadosPerfil;
import servicos.CalculadorFrete;

public class TelaNovoFrete extends JPanel implements Tela{

	Perfil perfilReferencia;
	
	NovaEntrega campoSuperior;
	VereditoNovaEntrega campoInferior;
	
	Frete freteMaisRapido,		// _1
		  freteMaisBarato,		// _2
		  freteCustoBeneficio;	// _3
	
	public TelaNovoFrete( Perfil perfilReferencia ) {
		this.perfilReferencia = perfilReferencia;
		setLayout( new CardLayout() );
		iniciarCampos();
		reiniciar();
		exibir();
	}
	
	private void iniciarCampos() {
		campoSuperior = new NovaEntrega();
		campoInferior = new VereditoNovaEntrega();
		add( campoSuperior );
		add( campoInferior );
	}
	
	//Calcula os fretes pedidos
	private void calcularFrete( double carga , double distancia , double prazo , boolean _1 , boolean _2 , boolean _3  ) {
		System.out.println(perfilReferencia);
		CalculadorFrete freteCalculado = new CalculadorFrete( perfilReferencia , carga , distancia , prazo );
		
		freteMaisRapido = null;
		freteMaisBarato = null;
		freteCustoBeneficio = null;
		
		if( _1 )
			this.freteMaisRapido = freteCalculado.getMaisRapido();
		if( _2 )
			this.freteMaisBarato = freteCalculado.getMaisBarato();
		if( _3 )
			this.freteCustoBeneficio = freteCalculado.getMelhorCustoBeneficio();
	}

	@Override
	public void exibir() {
		setVisible(true);
	}

	@Override
	public void ocultar() {
		setVisible(false);
	}

	@Override
	public void reiniciar() {
		campoSuperior.reiniciar();
		campoInferior.reiniciar();
		
	}
	
	//Declaração da classe privada NovaEntrega
	private class NovaEntrega extends JPanel implements Tela{
		
		private JLabel titulo;
		
		private JPanel painelBaixo;
		
		//private JPanel painelBotoes;
		private JButton go;
		private JButton help;
		
		private PainelCampo_NE painelCampo;
		private PainelSeletor_NE painelSeletor;
		
		public NovaEntrega( ) {
			setLayout( new GridLayout( 4 , 1 , 5 , 5 ) );
			iniciarCampos();
			exibir();
		}
		
		//Declaracao do metodo iniciarCampos
		private void iniciarCampos() {
			FlowLayout layout = new FlowLayout();
			painelCampo = new PainelCampo_NE( );
			painelSeletor = new PainelSeletor_NE( layout );
			
			titulo = new JLabel("Novo Frete");
			titulo.setFont( new Font( "Courier" , Font.PLAIN , 14 ) );
			titulo.setIcon( new ImageIcon( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/freteicon.png" ) );
			titulo.setHorizontalAlignment( SwingConstants.CENTER );
			titulo.setVerticalAlignment( SwingConstants.CENTER );
			titulo.setVisible(true);
			
			
			painelBaixo = new JPanel();
			painelBaixo.setLayout( new FlowLayout() );
			
			go = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/goicon.png"));
			go.setRolloverIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/gorollovericon.png") );
			go.addActionListener( new CalcularInput() );
			go.setToolTipText("Calcula o frete");
			go.setVisible(true);
			go.setHorizontalAlignment( SwingConstants.CENTER );
			go.setVerticalAlignment( SwingConstants.CENTER );
			
			help = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/helpicon.png") );
			help.setToolTipText("Ajuda");
			help.setHorizontalAlignment( SwingConstants.CENTER );
			help.setVerticalAlignment( SwingConstants.CENTER );
			help.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent event ) {
					JOptionPane.showMessageDialog(null, "Diremos a você a melhor opção do momento!\n"
														+ "Digite a carga a ser transportada (em quilos),\n"
														+ "a distância até o destino da entrega (em Km)\n"
														+ "e o tempo que você dispõe para realizar essa entrega.\n\n"
														+ "Marque as opções desejadas. Você pode marcar mais de uma.\n"
														+ "\tMostraremos todas!" , "Ajuda", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			
			painelBaixo.add( go );
			painelBaixo.add( help );
			
			
			painelCampo.setVisible(true);
			painelSeletor.setVisible(true);
			
			add( titulo );
			add( painelCampo );
			add( painelSeletor );
			add( painelBaixo );
			
		}//Fim do método iniciarCampos
		
		//Declaracao da classe CalcularInput
		private class CalcularInput implements ActionListener{
			public void actionPerformed( ActionEvent event ) {
				String input1 = painelCampo.getInput1(),
					   input2 = painelCampo.getInput2(),
					   input3 = painelCampo.getInput3();
				
				if( isInputValido( input1 ) && isInputValido( input2 ) && isInputValido( input3 ) ) {
					double carga = Double.parseDouble( input1 );
					double distancia = Double.parseDouble( input2 );
					double prazo = Double.parseDouble( input3 );
					
					if( carga == 0 || distancia == 0 || prazo == 0 )
						JOptionPane.showMessageDialog(null, "Nenhum valor pode ser 0" , "Valores invalidos" , JOptionPane.WARNING_MESSAGE);
					
					else {
						if( painelSeletor.isVelocidade() || painelSeletor.isMenorCusto() || painelSeletor.isCustoBeneficio() ) {
							calcularFrete( carga , distancia , prazo , painelSeletor.isVelocidade() , painelSeletor.isMenorCusto() , painelSeletor.isCustoBeneficio() );
							ocultar();
							
							//Se existem dados a serem mostrados
							if( campoInferior.inserirDadosFretes() )
								campoInferior.exibir();
							
							//Se nao existem dados a serem mostrados
							else
								TelaNovoFrete.this.reiniciar();
						}
						else
							JOptionPane.showMessageDialog(null, "Selecione pelo menos um tipo de frete" , "Nada para calcular" , JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					alertarInputInvalido();
					painelCampo.limparCampos();
				}
				
			}
			
			private boolean isInputValido( String input ) {
				if( input.length() == 0 )
					return false;
				
				else {
					boolean isDecimal = false;
					for( int i = 0 ; i < input.length() ; i++ ) {
						char cur = input.charAt(i);
						
						if( i == 0 && !( cur == '1' || cur == '2' || cur == '3' || cur == '4' || cur == '5' || cur == '6' || cur == '7' ||
							cur == '8' || cur == '9' || cur == '0'  ) ) {
							return false;
						}
						
						else if( cur == '.' && isDecimal )
							return false;
						
						else if( cur == '.' && ! isDecimal ) {
							isDecimal = true;
						}
						
						else if( !( cur == '1' || cur == '2' || cur == '3' || cur == '4' || cur == '5' || cur == '6' || cur == '7' ||
							cur == '8' || cur == '9' || cur == '0'  ) ) {
							return false;
						}
					}
					
					return true;
				}
			}
			
			private void alertarInputInvalido() {
				JOptionPane.showMessageDialog( null , "Insira valores válidos utilizando ponto final!" , "Erro de escrita" , JOptionPane.ERROR_MESSAGE );
			}
		}//Fim da classe privada CalcularInput de NovoFrete
		
		public void travarPainel() {
			setEnabled(false);
		}
		
		public void destravarPainel() {
			setEnabled(true);
		}

		@Override
		public void exibir() {
			setVisible(true);
		}

		@Override
		public void ocultar() {
			setVisible(false);
		}

		@Override
		//Por default ela fica exibida
		public void reiniciar() {
			destravarPainel();
			painelCampo.limparCampos();
			painelSeletor.limparCampos();
			exibir();
		}

	}//Fim da classe NovoEntrega
	
	
	//Declaracao da classe VereditoNovaEntrega
	public class VereditoNovaEntrega extends JPanel implements Tela {
		
		JPanel painelMestre;
		PainelConfirmador_VNE painelConfirmador;
		PainelLista_VNE painelLista;
		
		
		public VereditoNovaEntrega( ) {
			
			setLayout( new GridLayout( 2 , 1 , 5 , 5 ) );
			iniciarCampos();
			setVisible(true);
		}
		
		public void iniciarCampos( ) {
			painelMestre = new JPanel();
			painelMestre.setLayout( new GridLayout( 2 , 1 , 5 , 5) );
			
			painelConfirmador = new PainelConfirmador_VNE( );
			
			String[] fretes = new String[] { "" , "" , "" };
			
			painelLista = new PainelLista_VNE( fretes );
			
			painelMestre.add( painelLista );
			painelMestre.add( painelConfirmador );
			add( painelMestre );
			add( new JPanel() );
			
		}
		
		@Override
		public void exibir() {
			setVisible(true);
		}

		@Override
		public void ocultar() {
			setVisible(false);
		}
		
		public boolean inserirDadosFretes( ) {
			String [] dados = new String[3];
			if( freteMaisRapido != null )
				dados[0] = freteMaisRapido.toString();
			else
				dados[0] = "";
			
			if( freteMaisBarato != null )
				dados[1] = freteMaisBarato.toString();
			else
				dados[1] = "";
			
			if( freteCustoBeneficio != null )
				dados[2] = freteCustoBeneficio.toString();
			else
				dados[2] = "";
			
			if( freteMaisRapido == null && freteMaisBarato == null && freteCustoBeneficio == null ) {
				JOptionPane.showMessageDialog(null, "Infelizmente não existe nenhum veículo livre na frota que\nconsiga realizar esta entrega :(", "Ação impossível", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			painelLista.setDadosLista( dados );
			return true;
		}

		//Por default fica oculto
		@Override
		public void reiniciar() {
			painelLista.limparCampos();
			ocultar();
		}
		
		//Declaracao da classe PainelConfirmador
		public class PainelConfirmador_VNE extends JPanel{
			
			private JButton confirmar;
			private JButton cancelar;
			
			public PainelConfirmador_VNE() {
				setLayout( new GridLayout( 1 , 5 , 5 ,5 ) );
				iniciarBotoes();
				setVisible(true);
			}
			
			public void iniciarBotoes() {
				
				confirmar = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/goicon.png") );
				confirmar.setToolTipText("Confirma o frete selecionado");
				confirmar.setRolloverIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/gorollovericon.png") );
				confirmar.setVisible(true);
				
				EscutadorBotao escutador = new EscutadorBotao();
				confirmar.addActionListener( escutador );
				
				cancelar = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/cancelaricon.png") );
				cancelar.setToolTipText("Cancela a adição de novo frete");
				cancelar.setVisible(true);
				cancelar.addActionListener( escutador );
				
				add( new JPanel() );
				add( confirmar );
				add( new JPanel() );
				add( cancelar );
				add( new JPanel() );
			}
			
			//Declaracao da classe Escutador Botao
			private class EscutadorBotao implements ActionListener{
				
				//Metodo que responde ao clique de botao
				public void actionPerformed( ActionEvent event ) {
					if( event.getSource() == confirmar ) {
						
						boolean sucesso = false;
						if( painelLista.getFreteSelecionado() == 0 && freteMaisRapido != null ) {
							new ArquivadorDadosFrete( perfilReferencia.getCaminhoDataBase()+".fretes" , freteMaisRapido ).salvarDadosAtuais();
							perfilReferencia.adicionarFrete( freteMaisRapido );	sucesso = true;
						}
						else if( painelLista.getFreteSelecionado() == 1 && freteMaisBarato != null ) {
							new ArquivadorDadosFrete( perfilReferencia.getCaminhoDataBase()+".fretes" , freteMaisBarato ).salvarDadosAtuais();
							perfilReferencia.adicionarFrete( freteMaisBarato );	sucesso = true;
						}
						else if( painelLista.getFreteSelecionado() == 2 && freteCustoBeneficio != null) {
							new ArquivadorDadosFrete( perfilReferencia.getCaminhoDataBase()+".fretes" , freteCustoBeneficio ).salvarDadosAtuais();
							perfilReferencia.adicionarFrete( freteCustoBeneficio );	sucesso = true;
						}
						else {
							JOptionPane.showMessageDialog(null, "Escolha um frete!" , "Nada escolhido" , JOptionPane.WARNING_MESSAGE);
						}
						if( sucesso ) {
							JOptionPane.showMessageDialog(null, "Frete adicionado com sucesso!" , "Tarefa realizada" , JOptionPane.INFORMATION_MESSAGE );
							TelaNovoFrete.this.reiniciar();
						}
					}
					else {
						if( JOptionPane.showConfirmDialog(null, "Deseja cancelar a escolha de frete?", "Tem certeza disso?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
								== JOptionPane.YES_OPTION )
							TelaNovoFrete.this.reiniciar();
					}
					
				}//Fim do metodo actionPerformed
				
			}//Fim da classe escutador botao
			
		}//Fim da classe Painel confirmador_VNE

		
	}//Fim da classe VereditoNovaEntrega
	
	public static void main(String[] args) {
		JFrame janela = new JFrame("Teste");
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.setSize( 600 , 400 );
		
		TelaNovoFrete telaNovoFrete = new TelaNovoFrete( new BancoDeDadosPerfil("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/").getPerfil() );
		//telaNovoFrete.reiniciar();
		//telaNovoFrete.exibir();
		janela.add( telaNovoFrete );
		
		janela.setVisible(true);
	}
	
}//Fim da classe NovoFrete
