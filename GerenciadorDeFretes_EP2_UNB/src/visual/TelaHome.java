/* Classe TelaHome, utilizada para exibir a tela de Home do aplicativo e resolver todas as interaçoes com ela
 * Ultima modificação: 
 * Pendencias:
 */


package visual;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaHome extends JPanel implements Tela{
	
	JLabel msgBemVindo;
	JLabel msgNovoFrete,
		   msgFrota,
		   msgHistorico,
		   msgAjustes;
	
	public TelaHome() {
		setLayout( new GridLayout( 5 , 1 , 5 , 5 ) );
		iniciarPaineis();
		exibir();
	}
	
	public void iniciarPaineis() {
		String path = new File("").getAbsolutePath() + "/db/";
		
		msgBemVindo = new JLabel( "Bem Vindo!" , new  ImageIcon( path +  "bemvindoicon.png") , SwingConstants.CENTER );
		msgBemVindo.setHorizontalAlignment( SwingConstants.CENTER );
		msgBemVindo.setHorizontalTextPosition( SwingConstants.CENTER );
		msgBemVindo.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		msgNovoFrete = new JLabel( "Faça fretes inteligentes!" , new ImageIcon( path + "msgnovofreteicon.png") , SwingConstants.LEFT );
		
		msgFrota = new JLabel("Gerencie sua frota de veículos" , new ImageIcon( path + "msgfrotaicon.png") , SwingConstants.LEFT );
		
		msgHistorico = new JLabel("Veja suas atividades passadas", new ImageIcon( path +  "msghistoricoicon.png"), SwingConstants.LEFT);
		
		msgAjustes = new JLabel("Ajuste tudo de acordo com suas necessidades!" , new ImageIcon( path +  "msgajustesicon.png") , SwingConstants.LEFT);
		
		add( msgBemVindo );
		add( msgNovoFrete );
		add( msgFrota );
		add( msgHistorico );
		add( msgAjustes );
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
		return;
	}
	
	public static void main( String[] args) {
		JFrame tela = new JFrame("Teste");
		tela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		tela.setSize( 400 , 300 );
		TelaHome telaHome = new TelaHome();
		tela.add( telaHome );
		tela.setVisible(true);
	}
	
}
