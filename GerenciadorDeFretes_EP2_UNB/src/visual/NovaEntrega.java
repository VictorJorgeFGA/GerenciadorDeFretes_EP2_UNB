package visual;

import java.awt.BorderLayout;
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

public class NovaEntrega extends JPanel{
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
	
	public boolean possuiDados() {
		return possuiDados;
	}
	
	public Double getCarga() {
		return carga;
	}
	
	public Double getDistancia() {
		return distancia;
	}
	
	public Double getPrazo() {
		return prazo;
	}
	
	public boolean deveCalcularMaisRapido() {
		return painelSeletor.isVelocidade();
	}
	
	public boolean deveCalcularMaisBarato() {
		return painelSeletor.isMenorCusto();
	}
	
	public boolean deveCalcularCustoBeneficio() {
		return painelSeletor.isCustoBeneficio();
	}

	public void travarPainel() {
		setEnabled(false);
	}
	
	public void destravarPainel() {
		setEnabled(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame janela = new JFrame("Teste");
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.setSize(600, 400);
		janela.setLayout( new BorderLayout(10 , 10) );
		NovaEntrega ne = new NovaEntrega();
		janela.add(ne , BorderLayout.CENTER );
		JLabel k = new JLabel("???????????");
		janela.add(k, BorderLayout.WEST);
		ne.setVisible(true);
		janela.setVisible(true);
		System.out.println("?");
		
		
	}

}
