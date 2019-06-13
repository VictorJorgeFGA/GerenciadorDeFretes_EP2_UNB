package visual;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PainelNovoVeiculo extends JPanel implements Tela{
	
	private final String path = new File("").getAbsolutePath() + "/db/";
	
	private JLabel titulo;
	
	private JPanel painelCentral;
	
	private JLabel nome;
	private JLabel placa;
	
	private JTextField campoNome;
	private JTextField campoPlaca;
	
	private JPanel painelRadio;
	private JRadioButton carro;
	private JRadioButton carreta;
	private JRadioButton moto;
	private JRadioButton van;
	private ButtonGroup grupoBotoes;
	
	public PainelNovoVeiculo() {
		setLayout( new GridLayout(3,1,10,10) );
		iniciarCampos();
		setVisible(true);
	}
	
	public void iniciarCampos() {
		titulo = new JLabel("Novo veiculo", new ImageIcon( path + "novoveiculoicon.png"), SwingConstants.LEFT);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment(SwingConstants.CENTER);
		
		painelCentral = new JPanel( );
		painelCentral.setLayout(new GridLayout(2,2,10,10));
		
		nome = new JLabel("Nome do veículo");
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setVerticalAlignment(SwingConstants.BOTTOM);
		
		placa = new JLabel("Placa do veículo (ABC-1234)");
		placa.setHorizontalAlignment(SwingConstants.CENTER);
		placa.setVerticalAlignment(SwingConstants.BOTTOM);
		
		painelCentral.add( nome );
		painelCentral.add(placa);
		
		campoNome = new JTextField( "" , 15 );
		campoNome.setHorizontalAlignment(SwingConstants.CENTER);
		campoNome.setToolTipText("Digite o nome do novo veiculo");
		
		campoPlaca = new JTextField( "" , 8 );
		campoPlaca.setHorizontalAlignment( SwingConstants.CENTER );
		campoPlaca.setToolTipText("Digite a placa do veículo no formato ABC-1234");
		
		painelCentral.add(campoNome);
		painelCentral.add(campoPlaca);
		
		painelRadio = new JPanel();
		painelRadio.setLayout( new FlowLayout() );
		
		carro = new JRadioButton( "Carro" , true );
		carreta = new JRadioButton( "Carreta" , false);
		moto = new JRadioButton( "Moto" , false);
		van = new JRadioButton("Van" , false);
		grupoBotoes = new ButtonGroup( );
		grupoBotoes.add( carro );
		grupoBotoes.add( carreta );
		grupoBotoes.add( moto );
		grupoBotoes.add( van );
		
		painelRadio.add( carro );
		painelRadio.add( carreta );
		painelRadio.add( moto );
		painelRadio.add( van );
		
		add(titulo);
		add( painelCentral );
		add( painelRadio );
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
		campoNome.setText("");
		campoPlaca.setText("");
		carro.setSelected(true);
		
	}
	
	public boolean isCarro() {
		return carro.isSelected();
	}
	
	public boolean isCarreta() {
		return carreta.isSelected();
	}
	
	public boolean isMoto() {
		return moto.isSelected();
	}
	
	public boolean isVan() {
		return van.isSelected();
	}
	
	public String getNomeVeiculo() {
		return campoNome.getText();
	}
	
	public String getPlacaVeiculo() {
		return campoPlaca.getText();
	}
	
//	public static void main(String[] args) {
//		JFrame janela = new JFrame("teste");
//		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		janela.setSize( 600 , 700 );
//		janela.add( new PainelNovoVeiculo() );
//		janela.setVisible(true);
//	}

}
