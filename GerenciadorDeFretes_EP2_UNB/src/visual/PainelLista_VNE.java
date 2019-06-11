package visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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
		this.fretes = fretes;
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
		painelNomes.setLayout( new GridLayout( 1 , 4 , 5 , 5 ) );
		custo = new JLabel("Custo (R$)");
		custo.setHorizontalAlignment( SwingConstants.CENTER );
		lucro = new JLabel("Lucro (R$)");
		lucro.setHorizontalAlignment( SwingConstants.CENTER );
		tempoGasto = new JLabel( "Tempo gasto (Hrs)" );
		tempoGasto.setHorizontalAlignment( SwingConstants.CENTER );
		painelNomes.add( new JPanel() );
		painelNomes.add(custo);
		painelNomes.add(lucro);
		painelNomes.add(tempoGasto);
		painelNomes.setVisible(true);
		
		add(painelNomes , BorderLayout.NORTH);
		
		painelCategorias = new JPanel();
		painelCategorias.setLayout( new GridLayout(3,1,5,5) );
		maisRapido = new JLabel("Mais rápido");
		maisRapido.setHorizontalAlignment( SwingConstants.CENTER );
		maisBarato = new JLabel("Mais barato");
		maisBarato.setHorizontalAlignment( SwingConstants.CENTER );
		custoBeneficio = new JLabel("Custo beneficio");
		custoBeneficio.setHorizontalAlignment( SwingConstants.CENTER );
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
	
	public void setDadosLista( String[] dados ) {
		listaFretes.setListData( dados );
	}
	
	public void limparCampos() {
		listaFretes.setListData( new String[] { "","","" } );
	}
}
