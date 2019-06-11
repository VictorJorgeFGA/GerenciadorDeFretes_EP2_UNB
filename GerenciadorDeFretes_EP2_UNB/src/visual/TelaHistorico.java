package visual;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import entidades.Frete;
import entidades.Perfil;
import servicos.BancoDeDadosPerfil;

public class TelaHistorico extends JPanel implements Tela{

	Perfil perfilReferencia;
	
	JLabel titulo;
	JList<String> listaFretes;
	
	public TelaHistorico( Perfil perfilReferencia ) {
		this.perfilReferencia = perfilReferencia;
		setLayout( new BorderLayout( 10 , 10 ) );
		iniciarCampos();
		exibir();
	}
	
	private void iniciarCampos() {
		
		titulo = new JLabel("Historico", new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/historicoicon.png") , SwingConstants.LEFT);
		titulo.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		
		Vector<String> fretes = new Vector<String>();
		for( Frete f : perfilReferencia.getHistoricoFretes() ) {
			fretes.add( f.format() );
		}
		
		listaFretes = new JList<String>( fretes );
		
		add( titulo , BorderLayout.NORTH );
		add( new JScrollPane(listaFretes) , BorderLayout.CENTER );
		add( new JPanel() , BorderLayout.WEST );
		add( new JPanel() , BorderLayout.EAST );
		add( new JPanel() , BorderLayout.SOUTH );
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
		
		Vector<String> fretes = new Vector<String>();
		for( Frete f : perfilReferencia.getHistoricoFretes() ) {
			fretes.add( f.format() );
		}
		listaFretes.setListData( fretes );
		return;
	}
	
	
	public static void main( String[] args ) {
		JFrame janela = new JFrame("Teste");
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.setSize( 600 , 700 );
		janela.add( new TelaHistorico( new BancoDeDadosPerfil("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/").getPerfil()) );
		
		janela.setVisible(true);
		
	}
}
