package visual;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PainelCampo_NE extends JPanel{
	
	JLabel cargaImg;
	JLabel distanciaImg;
	JLabel prazoImg;
	JTextField carga;
	JTextField distancia;
	JTextField prazo;
	
	public PainelCampo_NE(  ) {
		setLayout( new GridLayout(2,3,5,5) );
		iniciarCampos();
		setVisible(true);
	}
	
	private void iniciarCampos() {
		
		cargaImg = new JLabel( );
		cargaImg.setHorizontalAlignment(SwingConstants.CENTER);
		cargaImg.setIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/cargaicon.png"));
		cargaImg.setHorizontalTextPosition(SwingConstants.CENTER);
		cargaImg.setVerticalTextPosition( SwingConstants.BOTTOM );
		cargaImg.setText("Carga (Kg)");
		
		distanciaImg = new JLabel();
		distanciaImg.setHorizontalAlignment(SwingConstants.CENTER);
		distanciaImg.setIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/distanciaicon.png") );
		distanciaImg.setHorizontalTextPosition(SwingConstants.CENTER);
		distanciaImg.setVerticalTextPosition( SwingConstants.BOTTOM );
		distanciaImg.setText("Distância (Km)");
		
		prazoImg = new JLabel();
		prazoImg.setHorizontalAlignment(SwingConstants.CENTER);
		prazoImg.setIcon( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/prazoicon.png") );
		prazoImg.setHorizontalTextPosition(SwingConstants.CENTER);
		prazoImg.setVerticalTextPosition( SwingConstants.BOTTOM );
		prazoImg.setText("Prazo (Hrs)");
		
		carga = new JTextField("0" , 10);
		carga.setToolTipText("Digite a carga em Quilos");
		
		distancia = new JTextField("0", 10);
		distancia.setToolTipText("Digite a distancia em Km");
		
		prazo = new JTextField("0", 10);
		prazo.setToolTipText("Digite o prazo em Horas");
		
		add( cargaImg );
		add( distanciaImg );
		add( prazoImg );
		
		add( carga );
		add( distancia );
		add( prazo );
		
	}
	
	public String getInput1() {
		return carga.getText();
	}
	
	public String getInput2() {
		return distancia.getText();
	}
	
	public String getInput3() {
		return prazo.getText();
	}
	
	public void limparCampos() {
		carga.setText("0");
		distancia.setText("0");
		prazo.setText("0");
	}
	
}
