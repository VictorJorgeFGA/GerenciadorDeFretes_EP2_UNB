/* Classe GUI_Final, utilizada como estrutura do programa e como interface grafica.
 * Ultima modificacao: 10/06/2019 - 00:06
 * Pendencias: Transferir as classes PainelCampo_NE e a classe PainelSeletor_NE para classes privates da classe NovaEntrega,
 * fazendo as devidas comunicações diretas e compartilhamento de dados e açoes. Implementar o metodo reiniciar() em todas os paineis
 * para suportar a mudança de tela.
 * Implementar a tela de administração de frota, visualização de fretes passados, home e a tela de Ajustes.
 */

package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entidades.Frete;
import entidades.Perfil;
import servicos.ArquivadorDadosFrete;
import servicos.CalculadorFrete;

public class GUI_Final extends JFrame{
	
	Perfil perfilReferencia;
	
	private BarraUtilitaria barraUtilitaria;
	
	private JPanel areaDeExibicao;
	
	private JPanel telaHome;
	
	private JPanel telaNovoFrete;
		NovaEntrega novaEntrega;
		VereditoNovaEntrega vereditoNovaEntrega;
		Queue<Frete> listaFretesTemp;
		
	private JPanel telaMinhaFrota;
	
	private JPanel telaHistorico;
	
	private JPanel telaAjustes;
	
 	public GUI_Final( Perfil perfilReferencia ) {
		super("Gerenciador de fretes");
		
		this.perfilReferencia = perfilReferencia;
		setLayout( new BorderLayout( ) );
		preparaGUI();
		setVisible(true);
	}
	
	public void preparaGUI() {
		areaDeExibicao = new JPanel();
		areaDeExibicao.setLayout( new CardLayout() );
		
		
		
		areaDeExibicao.add( telaHome );
		areaDeExibicao.add( telaNovoFrete );
		areaDeExibicao.setVisible(true);
		
	}
	
	private void preparaTelaNovoFrete() {
		listaFretesTemp = new LinkedList<Frete>();
		
		telaNovoFrete = new JPanel();
		telaNovoFrete.setLayout( new GridLayout( 2 , 1 , 5 , 5 ) );
		novaEntrega = new NovaEntrega();
		vereditoNovaEntrega = new VereditoNovaEntrega( new String[3] );
		vereditoNovaEntrega.setVisible(false);
		
		telaNovoFrete.add( novaEntrega );
		telaNovoFrete.add( vereditoNovaEntrega );
	}
	
	private void reiniciarTelaNovoFrete() {
		novaEntrega.reiniciar();
	}
	
	private class BarraUtilitaria{
		JButton home,
		novoFrete,
		minhaFrota,
		historico,
		ajustes;

		public BarraUtilitaria( ) {
			setLayout( new GridLayout( 5 , 1 , 5 , 5 ) );
			iniciarBotoes();
			setVisible(true);
		}
		public void iniciarBotoes() {
			EscutadorBotao listener = new EscutadorBotao();
			
			home = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/homeicon.png") );
			home.setToolTipText("Home");
			home.addActionListener(listener);
			
			novoFrete = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/novofreteicon.png") );
			novoFrete.setToolTipText("Novo Frete");
			novoFrete.addActionListener(listener);
			
			minhaFrota = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/minhafrotaicon.png") );
			minhaFrota.setToolTipText( "Minha Frota" );
			minhaFrota.addActionListener(listener);
			
			historico = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/historicoicon.png") );
			historico.setToolTipText("Historico de fretes");
			historico.addActionListener(listener);
			
			ajustes = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/ajustesicon.png") );
			ajustes.setToolTipText("Ajustes");
			ajustes.addActionListener(listener);
			
			add( home );
			add( novoFrete );
			add( minhaFrota );
			add( historico );
			add( ajustes );
		}
		
		private class EscutadorBotao implements ActionListener{
			public void actionPerformed( ActionEvent event ) {
				telaHome.setVisible(false);
				telaNovoFrete.setVisible(false);
				telaHistorico.setVisible(false);
				telaAjustes.setVisible(false);
				
				if( event.getSource() == home ) 
					telaHome.setVisible(true);
				
				else if( event.getSource() == novoFrete ) 
					telaNovoFrete.setVisible(true);
				
				else if( event.getSource() == minhaFrota )
					telaMinhaFrota.setVisible(true);
				
				else if( event.getSource() == historico )
					telaHistorico.setVisible(true);
				
				else if( event.getSource() == ajustes )
					telaAjustes.setVisible(true);
			}
		}
	}
	
	private class NovaEntrega extends JPanel{
		boolean possuiDados;
		
		private JLabel titulo;
		
		private JPanel painelBaixo;
		private JButton go;
		
		private PainelCampo_NE painelCampo;
		private PainelSeletor_NE painelSeletor;
		
		private Double carga,
			   distancia,
			   prazo;
		
		public NovaEntrega( ) {
			possuiDados = false;
			setLayout( new GridLayout( 4 , 1 , 5 , 5 ) );
			iniciarCampos();
			setVisible(true);
		}
		
		private void iniciarCampos() {
			FlowLayout layout = new FlowLayout();
			painelCampo = new PainelCampo_NE( );
			painelSeletor = new PainelSeletor_NE( layout );
			
			titulo = new JLabel("Nova Entrega Inteligente");
			titulo.setFont( new Font( "Courier" , Font.PLAIN , 14 ) );
			titulo.setIcon( new ImageIcon( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/freteicon.png" ) );
			titulo.setHorizontalAlignment( SwingConstants.CENTER );
			titulo.setVerticalAlignment( SwingConstants.CENTER );
			titulo.setVisible(true);
			
			
			painelBaixo = new JPanel();
			painelBaixo.setLayout( new GridLayout(1,3,10,10) );
			painelBaixo.add( new JPanel() );
			
			go = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/goicon.png"));
			go.setRolloverIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/gorollovericon.png") );
			go.addActionListener( new CalcularInput() );
			go.setToolTipText("Calcula o frete");
			go.setVisible(true);
			go.setMaximumSize( new Dimension( 100,100 ));
			painelBaixo.add( go );
			painelBaixo.add( new JPanel() );
			
			
			painelCampo.setVisible(true);
			painelSeletor.setVisible(true);
			
			add( titulo );
			add( painelCampo );
			add( painelSeletor );
			add( painelBaixo );
		}
		
		private void reiniciar() {
			painelCampo.reiniciar();
			painelSeletor.reiniciar();
		}
		
		private class CalcularInput implements ActionListener{
			public void actionPerformed( ActionEvent event ) {
				String input1 = painelCampo.getInput1(),
					   input2 = painelCampo.getInput2(),
					   input3 = painelCampo.getInput3();
				
				if( isInputValido( input1 ) && isInputValido( input2 ) && isInputValido( input3 ) ) {
					carga = Double.parseDouble( input1 );
					distancia = Double.parseDouble( input2 );
					prazo = Double.parseDouble( input3 );
					
					possuiDados = true;
					
					CalculadorFrete freteCalculado = new CalculadorFrete( perfilReferencia , distancia , carga , prazo );
					listaFretesTemp = new LinkedList<Frete>();
					listaFretesTemp.add( freteCalculado.getMaisRapido() );
					listaFretesTemp.add( freteCalculado.getMaisBarato() );
					listaFretesTemp.add( freteCalculado.getMelhorCustoBeneficio() );
					
					String[] fretes = new String[3];
					fretes[0] = freteCalculado.getMaisRapido().toString();
					fretes[1] = freteCalculado.getMaisBarato().toString();
					fretes[2] = freteCalculado.getMelhorCustoBeneficio().toString();
					
					vereditoNovaEntrega.setConteudoLista( fretes );
					vereditoNovaEntrega.setVisible(true);
					travarPainel();
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
		}

		public void travarPainel() {
			setEnabled(false);
		}
		
		public void destravarPainel() {
			setEnabled(true);
		}
	}
	
	private class VereditoNovaEntrega extends JPanel{
		
		PainelConfirmador_VNE painelConfirmador;
		PainelLista_VNE painelLista;
		
		
		public VereditoNovaEntrega( String [] fretes ) {

			setLayout( new GridLayout( 2 , 1 , 5 , 5 ) );
			iniciarCampos( fretes );
			setVisible(true);
		}
		
		public void iniciarCampos( String[] fretes ) {
			painelConfirmador = new PainelConfirmador_VNE( );
			painelLista = new PainelLista_VNE( fretes );
			
			add( painelLista );
			add( painelConfirmador );
			
		}
		
		public void setConteudoLista( String[] fretes ) {
			painelLista.setConteudoLista(fretes);
		}
		
		public class PainelLista_VNE extends JPanel{
			
			private Integer freteSelecionado;
			
			private String[] fretes;
			private JList<String> listaFretes;
			
			private JPanel painelNomes;
			private JLabel custo,
				   lucro,
				   tempoGasto;
			
			private JPanel painelCategorias;
			private JLabel maisRapido,
				   maisBarato,
				   custoBeneficio;
			
			public PainelLista_VNE( String [] fretes ) {
				setLayout( new BorderLayout() );
				iniciarCampos();
				setVisible(true);
			}
			
			private void iniciarCampos() {
				listaFretes = new JList<String>( fretes );
				listaFretes.setVisibleRowCount(3);
				listaFretes.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
				
				listaFretes.addListSelectionListener( new ListSelectionListener() {
					public void valueChanged( ListSelectionEvent event ) {
						painelConfirmador.destravarConfirmar();
						freteSelecionado = listaFretes.getSelectedIndex();
					}
				});
				
				add( new JScrollPane( listaFretes ) , BorderLayout.CENTER );
				
				painelNomes = new JPanel();
				painelNomes.setLayout( new GridLayout( 1 , 3 , 5 , 5 ) );
				custo = new JLabel("Custo (R$)");
				lucro = new JLabel("Lucro (R$)");
				tempoGasto = new JLabel( "Tempo gasto (Hrs)" );
				painelNomes.add(custo);
				painelNomes.add(lucro);
				painelNomes.add(tempoGasto);
				painelNomes.setVisible(true);
				
				add(painelNomes , BorderLayout.NORTH);
				
				painelCategorias = new JPanel();
				painelCategorias.setLayout( new GridLayout(5,1,5,5) );
				maisRapido = new JLabel("Mais rápido");
				maisBarato = new JLabel("Mais barato");
				custoBeneficio = new JLabel("Custo beneficio");
				painelCategorias.add( new JPanel() );
				painelCategorias.add( new JPanel() );
				painelCategorias.add( maisRapido );
				painelCategorias.add( maisBarato );
				painelCategorias.add( custoBeneficio );
				painelCategorias.setVisible(true);
				
				add( painelCategorias , BorderLayout.WEST );
			}
			
			public void setConteudoLista( String[] fretes ) {
				listaFretes.setListData( fretes );
			}
			
			public int getFreteSelecionado() {
				if( freteSelecionado == null )
					return -1;
				else
					return freteSelecionado;
			}
		}
		
		private class PainelConfirmador_VNE extends JPanel{
			private boolean possuiVeredito;
			private Boolean resultado;	//True = confirmar
			
			private JButton confirmar;
			private JButton cancelar;
			
			public PainelConfirmador_VNE() {
				possuiVeredito = false;
				setLayout( new GridLayout( 1 , 5 , 5 ,5 ) );
				iniciarBotoes();
				setVisible(true);
			}
			
			public void iniciarBotoes() {
				
				confirmar = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/goicon.png") );
				confirmar.setToolTipText("Confirma o frete selecionado");
				confirmar.setRolloverIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/gorollovericon.png") );
				confirmar.setEnabled(false);
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
			
			private class EscutadorBotao implements ActionListener{
				public void actionPerformed( ActionEvent event ) {
					if( event.getSource() == confirmar ) {
						
						Frete escolhido = null;
						int cont = 0;
						for( Frete f : listaFretesTemp ) {
							if( cont == painelLista.getFreteSelecionado() )
								escolhido = f;
							cont++;
						}
						
						perfilReferencia.adicionarFrete( escolhido );
						new ArquivadorDadosFrete( perfilReferencia.getCaminhoDataBase() + ".fretes" , escolhido ).salvarDadosAtuais();
						
						JOptionPane.showMessageDialog(null, "Frete confirmado com sucesso!" , "Confirmação Frete" , JOptionPane.INFORMATION_MESSAGE );
						
						
						resultado = true;
					}
					else
						resultado = false;
					
					possuiVeredito = true;
				}
			}
			
			public boolean possuiVeredito() {
				return possuiVeredito;
			}
			
			public Boolean isConfirmado() {
				return resultado;
			}
			
			public void destravarConfirmar() {
				confirmar.setEnabled(true);
			}
			
		}

	}

}
