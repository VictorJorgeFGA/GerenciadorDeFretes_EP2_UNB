package visual;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelConfirmador_VNE extends JPanel{
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
	
}
