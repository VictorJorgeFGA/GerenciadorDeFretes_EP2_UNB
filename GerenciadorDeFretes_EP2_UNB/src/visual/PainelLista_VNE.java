package visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	
	public int getFreteSelecionado() {
		if( freteSelecionado == null )
			return -1;
		else
			return freteSelecionado;
	}
}
