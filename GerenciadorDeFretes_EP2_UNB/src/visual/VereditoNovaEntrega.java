package visual;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class VereditoNovaEntrega extends JPanel{
	
	boolean possuiVeredito;
	
	PainelConfirmador_VNE painelConfirmador;
	PainelLista_VNE painelLista;
	
	
	public VereditoNovaEntrega( String [] fretes ) {
		possuiVeredito = false;
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
	
	public boolean possuiVeredito() {
		return painelConfirmador.possuiVeredito();
	}
	
	public boolean isFreteConfirmado() {
		return painelConfirmador.isConfirmado();
	}
	
	public Integer getFreteSelecionado() {
		return painelLista.getFreteSelecionado();
	}
}
