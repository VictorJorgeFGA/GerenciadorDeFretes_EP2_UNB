package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entidades.Perfil;
import servicos.ArquivadorDadosFrota;
import servicos.BancoDeDadosPerfil;

public class GUI extends JFrame {

	private final String path = new File("").getAbsolutePath() + "/db/";
	
	Perfil perfilReferencia;
	
	JPanel areaExibicao;
	BarraUtilitaria barraUtilitaria;
	TelaHome telaHome;
	TelaNovoFrete telaNovoFrete;
	TelaMinhaFrota telaMinhaFrota;
	TelaHistorico telaHistorico;
	TelaAjustes telaAjustes;
	
	public GUI( Perfil perfilReferencia ) {
		super("Gerenciador de Fretes v0.0");
		
		this.perfilReferencia = perfilReferencia;
		ImageIcon img = new ImageIcon( path + "msgfrotaicon.png" );
		this.setIconImage( img.getImage() );
		
		setLayout( new BorderLayout( 10, 10 ) );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( 700 , 700 );
		this.setMinimumSize( new Dimension( 600 , 600 ) );
		this.setMaximumSize( new Dimension( 800 , 800 ) );
		iniciarGUI();
		
		this.setLocationRelativeTo(null);
		this.addWindowListener( new ListenerJanela() );
		setVisible(true);
	}
	
	public void iniciarGUI() {
		barraUtilitaria = new BarraUtilitaria();
		
		areaExibicao = new JPanel();
		areaExibicao.setLayout( new CardLayout(10,10) );
		
		telaHome = new TelaHome( );
		telaHome.exibir();
		telaNovoFrete = new TelaNovoFrete( perfilReferencia );
		telaNovoFrete.ocultar();
		telaMinhaFrota = new TelaMinhaFrota( perfilReferencia );
		telaMinhaFrota.ocultar();
		telaHistorico = new TelaHistorico( perfilReferencia );
		telaHistorico.ocultar();
		telaAjustes = new TelaAjustes( perfilReferencia );
		telaAjustes.ocultar();
		
		areaExibicao.add(telaHome);
		areaExibicao.add(telaNovoFrete);
		areaExibicao.add(telaMinhaFrota);
		areaExibicao.add(telaHistorico);
		areaExibicao.add(telaAjustes);
		
		add( barraUtilitaria , BorderLayout.WEST );
		add( new JPanel() , BorderLayout.EAST );
		add( areaExibicao );
	}
	
	private void salvarAlteracoes() {
		new ArquivadorDadosFrota( perfilReferencia.getCaminhoDataBase()+".frota" , perfilReferencia.getFrota() ).salvarDadosAtuais();
	}
	
	private class ListenerJanela implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {
			if( perfilReferencia.foiModificado() ) {
				if( JOptionPane.showConfirmDialog(GUI.this, "Modificações foram feitas neste perfil, deseja salva-las?", "", JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE)
						== JOptionPane.YES_OPTION )
					salvarAlteracoes();	
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowDeactivated(WindowEvent e) {}
	}
	
	private class BarraUtilitaria extends JPanel{
		
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
			
			home = new JButton( new ImageIcon( path + "homeicon.png") );
			home.setToolTipText("Home");
			home.addActionListener(listener);
			
			novoFrete = new JButton( new ImageIcon( path +  "novofreteicon.png") );
			novoFrete.setToolTipText("Novo Frete");
			novoFrete.addActionListener(listener);
			
			minhaFrota = new JButton( new ImageIcon( path +  "minhafrotaicon.png") );
			minhaFrota.setToolTipText( "Minha Frota" );
			minhaFrota.addActionListener(listener);
			
			historico = new JButton( new ImageIcon( path +  "historicoicon.png") );
			historico.setToolTipText("Historico de fretes");
			historico.addActionListener(listener);
			
			ajustes = new JButton( new ImageIcon( path + "ajustesicon.png") );
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
				telaHome.ocultar();
				telaNovoFrete.ocultar();
				telaMinhaFrota.ocultar();
				telaHistorico.ocultar();
				telaAjustes.ocultar();
				
				if( event.getSource() == home ) {
					telaHome.reiniciar();
					telaHome.exibir();
				}
				
				else if( event.getSource() == novoFrete ) {
					telaNovoFrete.reiniciar();
					telaNovoFrete.exibir();
				}
				
				else if( event.getSource() == minhaFrota ) {
					telaMinhaFrota.reiniciar();
					telaMinhaFrota.exibir();
				}
				
				else if( event.getSource() == historico ) {
					telaHistorico.reiniciar();
					telaHistorico.exibir();
				}
				
				else if( event.getSource() == ajustes ) {
					telaAjustes.reiniciar();
					telaAjustes.exibir();
				}
			}
		}
	}
	
//	public static void main(String[] args) {
//		//GUI interfaceGrafica = new GUI( new BancoDeDadosPerfil( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/" ).getPerfil() );
//	}

}
