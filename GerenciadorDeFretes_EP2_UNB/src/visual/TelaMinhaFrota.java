package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entidades.Perfil;
import entidades.Veiculo;
import entidades.Veiculo.Tipo;
import servicos.BancoDeDadosPerfil;

public class TelaMinhaFrota extends JPanel implements Tela{

	private final String path = new File("").getAbsolutePath() + "/db/";
	
	private Perfil perfilReferencia;
	
	private JLabel titulo;
	
	private JPanel painelCentral;
	
	private PainelNovoVeiculo painelNovoVeiculo;
	
	private JPanel painelBarra;
	private JPanel painelLista;
	private JList<String> listaVeiculos;
	private JLabel dica;
	private JButton helpBusca;
	private JTextField barraBuscas;
	
	private JPanel painelSul;
	private JButton apagarVeiculo;
	private JButton help;
	private JButton adicionarVeiculo;
	private JButton reservarVeiculo;
	private JButton liberarVeiculo;
	
	private Vector<String> listaTrue;
	private Vector<String> listaFiltrada;
	
	
	public TelaMinhaFrota( Perfil perfilReferencia ) {
		this.perfilReferencia = perfilReferencia;
		setLayout( new BorderLayout(5,5) );
		iniciarCampos();
		exibir();
	}
	
	public void iniciarCampos() {
		
		titulo = new JLabel( "Minha Frota" , new ImageIcon(path + "minhafrotaicon.png") , SwingConstants.LEFT );
		titulo.setHorizontalAlignment( SwingConstants.CENTER );
		
		listaTrue = new Vector<String>();
		listaFiltrada = new Vector<String>();
		
		for( Veiculo v : perfilReferencia.getFrota() ) {
			listaTrue.add( v.toString() );
		}
		
		painelCentral = new JPanel();
		painelCentral.setLayout( new GridLayout( 2 , 1 , 10 , 10 ) );
		
		listaVeiculos = new JList<String>( listaTrue );
		listaVeiculos.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		listaVeiculos.setToolTipText("Matenha pressionado CTRL e clique nos veículos para seleciona-los");
		
		painelNovoVeiculo = new PainelNovoVeiculo();
		
		iniciarBarraPesquisa();
		
		painelCentral.add( painelLista );
		painelCentral.add( painelNovoVeiculo );
		
		painelSul = new JPanel();
		painelSul.setLayout( new FlowLayout( ) );
		apagarVeiculo = new JButton( new ImageIcon(path + "removericon.png") );
		apagarVeiculo.addActionListener( new AcaoDelete() );
		apagarVeiculo.setToolTipText("Remove da frota os veiculos selecionados");
		apagarVeiculo.setHorizontalAlignment( SwingConstants.CENTER );
		apagarVeiculo.setVerticalAlignment( SwingConstants.CENTER );
		
		
		help = new JButton( new ImageIcon(path + "helpicon.png") );
		help.setToolTipText("Guia");
		help.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog(null, "Remova veículos da sua frota os selecionando na lista e\nclicando no botao remover (Lixeira)\n\n"
						+ "Adicione novos veículos preenchendo os campos de informaçoes\ne depois clicando em adicionar (Mais)\n\n"
						+ "Indique que veículos estão ocupados os selecionando na lista\ne clicando no botao reservar (Cadeado fechado)\n\n" 
						+ "Indique que veículos estão livres os selecionando na lista\ne clicando no botao liberar (Cadeado aberto)", "Help", JOptionPane.QUESTION_MESSAGE);
			}
		});
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setVerticalAlignment(SwingConstants.CENTER);
		
		adicionarVeiculo = new JButton( new ImageIcon(path + "msgnovofreteicon.png") );
		adicionarVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		adicionarVeiculo.setVerticalAlignment( SwingConstants.CENTER );
		adicionarVeiculo.setToolTipText("Adiciona um novo veículo na frota com as características descritas acima");
		adicionarVeiculo.addActionListener( new AcaoAdicao() );
		
		reservarVeiculo = new JButton( new ImageIcon(path + "reservaricon.png") );
		reservarVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		reservarVeiculo.setVerticalAlignment( SwingConstants.CENTER );
		reservarVeiculo.setToolTipText("Reservar veículos selecionados");
		reservarVeiculo.addActionListener( new AcaoReservar() );
		
		liberarVeiculo = new JButton( new ImageIcon(path + "liberaricon.png") );
		liberarVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		liberarVeiculo.setVerticalAlignment( SwingConstants.CENTER );
		liberarVeiculo.setToolTipText("Liberar veículos selecionados");
		liberarVeiculo.addActionListener( new AcaoLiberar() );
		
		
		painelSul.add( help );
		painelSul.add( apagarVeiculo );
		painelSul.add( adicionarVeiculo );
		painelSul.add(reservarVeiculo);
		painelSul.add(liberarVeiculo);
		
		add( new JPanel() , BorderLayout.WEST );
		add( new JPanel() , BorderLayout.EAST );
		add( painelSul , BorderLayout.SOUTH );
		add( painelCentral , BorderLayout.CENTER );
		add( titulo , BorderLayout.NORTH );
	}
	
	public void iniciarBarraPesquisa() {
		
		painelLista = new JPanel();
		painelLista.setLayout( new BorderLayout(10,10) );
		painelLista.add( new JScrollPane( listaVeiculos ) , BorderLayout.CENTER );
		
		painelBarra = new JPanel();
		painelBarra.setLayout( new BorderLayout(10,10) );
		
		dica = new JLabel( new ImageIcon(path + "searchicon.png"));
		dica.setToolTipText("Filtre sua busca!");
		
		helpBusca = new JButton( new ImageIcon(path + "helpicon.png") );
		helpBusca.setToolTipText("Ajuda");
		helpBusca.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog(null, "Você pode filtrar os resultados da sua busca digitando:\nA placa do veículo, o nome do veículo, o tipo do veículo"
						+ "\nou você pode filtrar pelo estado na transportadora: reservado ou livre." , "Guia",  JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		barraBuscas = new JTextField("");
		barraBuscas.setToolTipText("Filtre digitando aqui!");
		barraBuscas.getDocument().addDocumentListener( new AcaoPesquisa() );
		
		painelBarra.add( barraBuscas , BorderLayout.CENTER );
		painelBarra.add( dica , BorderLayout.WEST );
		painelBarra.add( helpBusca , BorderLayout.EAST );
		
		painelLista.add( painelBarra , BorderLayout.NORTH );
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
				TelaMinhaFrota.this.reiniciar();
		}
		
		private void reiniciar() {
			listaVeiculos.setListData( listaFiltrada );
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
		for( Veiculo v : perfilReferencia.getFrota() ) {
			listaTrue.add( v.toString() );
		}
		listaVeiculos.setListData( listaTrue );
		barraBuscas.setText("");
	}
	
	//Inicio da classe AcaoDelete
	private class AcaoDelete implements ActionListener{
		
		public void actionPerformed( ActionEvent event ) {
			
			List<String> selecionados = listaVeiculos.getSelectedValuesList();
			if( selecionados.size() == 0 )
				JOptionPane.showMessageDialog(null, "Selecione ao menos um veículo!" , "Erro" , JOptionPane.ERROR_MESSAGE );
			
			else {
				int result = 0;
				
				if( selecionados.size() == 1 )
					result = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar este veiculo?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE );
				else
					result = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar estes veiculos?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE );
				
				//O usuario decidiu por remover os veiculos
				if( result == JOptionPane.YES_OPTION ) {
					Vector<Integer> idsRemovidos = new Vector<Integer>();	//Contera os IDS dos veiculos a serem removidos
					
					//Descobre quais veiculos serao removidos
					for( String s : selecionados ) {
						for( Veiculo v : perfilReferencia.getFrota() ) {
							if( v.toString().equals( s ) )
								idsRemovidos.add( v.getVeiculoID() );
						}
					}
					//Remove os veiculos
					for( int i = 0 ; i < idsRemovidos.size() ; i++ ) {
						perfilReferencia.removerVeiculo( idsRemovidos.elementAt(i) );
					}
					
					reiniciar();
				}
			}
		}
		
	}//Fim da classe AcaoDelete
	
	private class AcaoAdicao implements ActionListener{
		public void actionPerformed( ActionEvent event ) {
			
			String nome = painelNovoVeiculo.getNomeVeiculo();
			String placa = painelNovoVeiculo.getPlacaVeiculo();
			if( nome.length() > 30 || nome.length() == 0)
				JOptionPane.showMessageDialog(null, "O nome do veículo deve conter no máximo 30 caracteres\ne no mínimo 1 caractere", "Tamanho limite", JOptionPane.WARNING_MESSAGE);
			else if( ! segueNormaPlaca( placa ) )
				JOptionPane.showMessageDialog(null, "Digite a placa no formato ABC-1234", "Formato de placa inválido", JOptionPane.WARNING_MESSAGE);
			else {
				boolean sucesso = false;
				if( painelNovoVeiculo.isCarro() ) {
					perfilReferencia.adicionarVeiculo( Tipo.CARRO , nome, placa);	sucesso = true;
				}
				else if( painelNovoVeiculo.isCarreta() ) {
					perfilReferencia.adicionarVeiculo( Tipo.CARRETA , nome, placa);	sucesso = true;
				}
				else if( painelNovoVeiculo.isMoto() ) {
					perfilReferencia.adicionarVeiculo( Tipo.MOTO , nome, placa);	sucesso = true;
				}
				else if( painelNovoVeiculo.isVan() ) {
					perfilReferencia.adicionarVeiculo( Tipo.VAN , nome, placa);	sucesso = true;
				}
				if( sucesso )
					JOptionPane.showMessageDialog(null, "Veículo adicionado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
				reiniciar();
				painelNovoVeiculo.reiniciar();
			}
		}
		
		private boolean segueNormaPlaca( String placa ) {
			if( placa.length() != 8 )
				return false;
			else {
				if( !( (placa.charAt(0) >= 65 && placa.charAt(0) <= 90) && (placa.charAt(1) >= 65 && placa.charAt(1) <= 90) &&
					   (placa.charAt(2) >= 65 && placa.charAt(2) <= 90) ) )
					return false;
				else if( placa.charAt(3) != '-' )
					return false;
				else if( !( (placa.charAt(4) >= 48 && placa.charAt(4) <= 57) && (placa.charAt(5) >= 48 && placa.charAt(5) <= 57) &&
						(placa.charAt(6) >= 48 && placa.charAt(6) <= 57) && (placa.charAt(7) >= 48 && placa.charAt(7) <= 57) ) )
					return false;
				else
					return true;
			}
		}
	}
	
	//Declaracao da classe AcaoReservar
	private class AcaoReservar implements ActionListener{
		public void actionPerformed( ActionEvent event ) {
			
			List<String> selecionados = listaVeiculos.getSelectedValuesList();
			
			if( selecionados.size() == 0 ) {
				JOptionPane.showMessageDialog( null , "Selecione ao menos 1 veículo para reservar" , "Erro!" , JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			boolean pode = false;
			
			if( selecionados.size() == 1 )
				pode = JOptionPane.showConfirmDialog(null, "Deseja realmente reservar esse veículo?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE)
					== JOptionPane.YES_OPTION ? true : false;
			
			else if( selecionados.size() > 1 )
				pode = JOptionPane.showConfirmDialog(null, "Deseja realmente reservar esses veículos?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE)
				== JOptionPane.YES_OPTION ? true : false;
			
			if( pode ) {
				for( String s : selecionados ) {
					for( Veiculo v : perfilReferencia.getFrota() ) {
						if( v.toString().equals( s ) )
							perfilReferencia.reservarVeiculo( v.getVeiculoID() );
					}
				}
				TelaMinhaFrota.this.reiniciar();
			}
		}
		
	}//FIm da classe AcaoReservar
	
	private class AcaoLiberar implements ActionListener{
		public void actionPerformed( ActionEvent event ) {
			
			List<String> selecionados = listaVeiculos.getSelectedValuesList();
			
			if( selecionados.size() == 0 ) {
				JOptionPane.showMessageDialog( null , "Selecione ao menos 1 veículo para liberar" , "Erro!" , JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			boolean pode = false;
			
			if( selecionados.size() == 1 )
				pode = JOptionPane.showConfirmDialog(null, "Deseja realmente liberar esse veículo?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE)
					== JOptionPane.YES_OPTION ? true : false;
			
			else if( selecionados.size() > 1 )
				pode = JOptionPane.showConfirmDialog(null, "Deseja realmente liberar esses veículos?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE)
				== JOptionPane.YES_OPTION ? true : false;
			
			if( pode ) {
				for( String s : selecionados ) {
					for( Veiculo v : perfilReferencia.getFrota() ) {
						if( v.toString().equals( s ) )
							perfilReferencia.liberarVeiculo( v.getVeiculoID() );
					}
				}
				TelaMinhaFrota.this.reiniciar();
			}
		}
	}
//	
//	public static void main(String[] args) {
////		JFrame janela = new JFrame("Teste");
////		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		janela.setSize( 600 , 700 );
////		janela.add( new TelaMinhaFrota( new BancoDeDadosPerfil(path + "").getPerfil() ) );
////		
////		janela.setVisible(true);
//	}

}
