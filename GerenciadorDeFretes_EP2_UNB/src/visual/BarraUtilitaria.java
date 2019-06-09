package visual;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class BarraUtilitaria extends JPanel{
	
	JComponent[] telas;
	
	JButton home,
			novoFrete,
			minhaFrota,
			historico,
			ajustes;
	
	public BarraUtilitaria( JComponent [] telas ) {
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
			for( int i = 0 ; i < 5 ; i++) {
				telas[i].setVisible(false);
			}
			
			if( event.getSource() == home ) 
				telas[0].setVisible(true);
			
			else if( event.getSource() == novoFrete ) 
				telas[1].setVisible(true);
			
			else if( event.getSource() == minhaFrota )
				telas[2].setVisible(true);
			
			else if( event.getSource() == historico )
				telas[3].setVisible(true);
			
			else if( event.getSource() == ajustes )
				telas[4].setVisible(true);
		}
	}
}
