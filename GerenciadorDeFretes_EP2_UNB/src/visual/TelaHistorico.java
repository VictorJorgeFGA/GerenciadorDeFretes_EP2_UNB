package visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entidades.Frete;
import entidades.Perfil;
import servicos.BancoDeDadosPerfil;

public class TelaHistorico extends JPanel implements Tela{

	private final String path = new File("").getAbsolutePath() + "/db/";
	
	private Perfil perfilReferencia;
	
	private JLabel titulo;
	private JList<String> listaFretes;
	
	private JPanel painelFiltro;
	private JLabel dica;
	private JTextField barraBuscas;
	private JButton help;
	
	private JPanel painelCentral;
	
	private Vector<String> listaFiltrada;
	private Vector<String> listaTrue;
	
	public TelaHistorico( Perfil perfilReferencia ) {
		this.perfilReferencia = perfilReferencia;
		setLayout( new BorderLayout( 10 , 10 ) );
		iniciarCampos();
		exibir();
	}
	
	private void iniciarCampos() {
		
		titulo = new JLabel("Historico", new ImageIcon(path + "historicoicon.png") , SwingConstants.LEFT);
		titulo.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		
		listaFiltrada = new Vector<String>();
		listaTrue = new Vector<String>();
		for( Frete f : perfilReferencia.getHistoricoFretes() ) {
			listaTrue.add( f.format() );
		}
		
		listaFretes = new JList<String>( listaTrue );	
		
		painelFiltro = new JPanel();
		painelFiltro.setLayout( new BorderLayout(10,10) );
		
		dica = new JLabel( new ImageIcon(path + "searchicon.png") );
		dica.setToolTipText("Filtre sua busca!");
		
		help = new JButton( new ImageIcon(path + "helpicon.png") );
		help.setToolTipText("Ajuda");
		help.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog(null, "Você pode filtrar os resultados da sua busca digitando:\nA data do frete, a placa do veículo, o nome do veículo"
						+ "\n ou até mesmo o custo e o lucro do frete!" , "Guia",  JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		barraBuscas = new JTextField("");
		barraBuscas.setToolTipText("Digite sua busca aqui!");
		barraBuscas.getDocument().addDocumentListener( new AcaoPesquisa() );
		
		painelFiltro.add( dica , BorderLayout.WEST );
		painelFiltro.add( help , BorderLayout.EAST );
		painelFiltro.add( barraBuscas , BorderLayout.CENTER );
		
		painelCentral = new JPanel();
		painelCentral.setLayout( new BorderLayout( 10 , 10 ) );
		
		painelCentral.add( new JScrollPane(listaFretes) , BorderLayout.CENTER );
		painelCentral.add( painelFiltro , BorderLayout.NORTH );
		
		add( titulo , BorderLayout.NORTH );
		add( painelCentral , BorderLayout.CENTER );
		add( new JPanel() , BorderLayout.WEST );
		add( new JPanel() , BorderLayout.EAST );
		add( new JPanel() , BorderLayout.SOUTH );
	}
	
	private class AcaoPesquisa implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			filtrar();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			filtrar();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			filtrar();
		}
		
		public void filtrar() {
			String filtro = barraBuscas.getText();
			if( filtro != "" ) {
				filtro = filtro.toLowerCase();
				
				listaFiltrada.clear();
				for( int i = 0 ; i < listaTrue.size() ; i++) {
					if( listaTrue.elementAt(i).toLowerCase().contains( filtro ) )
						listaFiltrada.add( listaTrue.elementAt(i) );
				}
				reiniciar();
			}
			else
				TelaHistorico.this.reiniciar();
		}
		
		private void reiniciar() {
			listaFretes.setListData( listaFiltrada );
		}
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
		listaTrue.clear();
		for( Frete f : perfilReferencia.getHistoricoFretes() ) {
			listaTrue.add( f.format() );
		}
		listaFretes.setListData( listaTrue );
		barraBuscas.setText("");
		return;
	}
	
	
	
//	public static void main( String[] args ) {
////		JFrame janela = new JFrame("Teste");
////		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
////		janela.setSize( 600 , 700 );
////		janela.add( new TelaHistorico( new BancoDeDadosPerfil(path + "").getPerfil()) );
////		
////		janela.setVisible(true);
//		
//	}
}
