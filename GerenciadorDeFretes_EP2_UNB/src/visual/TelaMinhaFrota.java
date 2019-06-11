package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import entidades.Perfil;
import entidades.Veiculo;
import entidades.Veiculo.Tipo;
import servicos.BancoDeDadosPerfil;

public class TelaMinhaFrota extends JPanel implements Tela{

	Perfil perfilReferencia;
	
	JLabel titulo;
	
	JPanel painelCentral;
	JList<String> listaVeiculos;
	PainelNovoVeiculo painelNovoVeiculo;
	
	JPanel painelSul;
	JButton apagarVeiculo;
	JButton help;
	JButton adicionarVeiculo;
	
	
	public TelaMinhaFrota( Perfil perfilReferencia ) {
		this.perfilReferencia = perfilReferencia;
		setLayout( new BorderLayout(5,5) );
		iniciarCampos();
		exibir();
	}
	
	public void iniciarCampos() {
		
		titulo = new JLabel( "Minha Frota" , new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/minhafrotaicon.png") , SwingConstants.LEFT );
		titulo.setHorizontalAlignment( SwingConstants.CENTER );
		
		Vector<String> veiculos = new Vector<String>();
		for( Veiculo v : perfilReferencia.getFrota() ) {
			veiculos.add( v.toString() );
		}
		
		painelCentral = new JPanel();
		painelCentral.setLayout( new GridLayout( 2 , 1 , 10 , 10 ) );
		
		listaVeiculos = new JList<String>( veiculos );
		listaVeiculos.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		listaVeiculos.setToolTipText("Matenha pressionado CTRL e clique nos veículos para seleciona-los");
		
		painelNovoVeiculo = new PainelNovoVeiculo();
		
		painelCentral.add( new JScrollPane( listaVeiculos ) );
		painelCentral.add( painelNovoVeiculo );
		
		painelSul = new JPanel();
		painelSul.setLayout( new FlowLayout( ) );
		apagarVeiculo = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/removericon.png") );
		apagarVeiculo.addActionListener( new AcaoDelete() );
		apagarVeiculo.setToolTipText("Remove da frota os veiculos selecionados");
		apagarVeiculo.setHorizontalAlignment( SwingConstants.CENTER );
		apagarVeiculo.setVerticalAlignment( SwingConstants.CENTER );
		
		
		help = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/helpicon.png") );
		help.setToolTipText("Guia");
		help.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog(null, "Remova veículos da sua frota os selecionando na lista e\nclicando no botao remover (Lixeira)\n\n"
						+ "Adicione novos veículos preenchendo os campos de informaçoes\ne depois clicando em adicionar (Mais)", "Help", JOptionPane.QUESTION_MESSAGE);
			}
		});
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setVerticalAlignment(SwingConstants.CENTER);
		
		adicionarVeiculo = new JButton( new ImageIcon("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/msgnovofreteicon.png") );
		adicionarVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		adicionarVeiculo.setVerticalAlignment( SwingConstants.CENTER );
		adicionarVeiculo.setToolTipText("Adiciona um novo veículo na frota com as características descritas acima");
		adicionarVeiculo.addActionListener( new AcaoAdicao() );
		
		
		painelSul.add( help );
		painelSul.add( apagarVeiculo );
		painelSul.add( adicionarVeiculo );
		
		add( new JPanel() , BorderLayout.WEST );
		add( new JPanel() , BorderLayout.EAST );
		add( painelSul , BorderLayout.SOUTH );
		add( painelCentral , BorderLayout.CENTER );
		add( titulo , BorderLayout.NORTH );
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

		Vector<String> veiculos = new Vector<String>();
		for( Veiculo v : perfilReferencia.getFrota() ) {
			veiculos.add( v.toString() );
		}
		listaVeiculos.setListData( veiculos );
	}
	
	//Inicio da classe AcaoDelete
	private class AcaoDelete implements ActionListener{
		
		public void actionPerformed( ActionEvent event ) {
			
			int[] selecionados = listaVeiculos.getSelectedIndices();
			if( selecionados.length == 0 )
				JOptionPane.showMessageDialog(null, "Selecione ao menos um veículo!" , "Erro" , JOptionPane.ERROR_MESSAGE );
			
			else {
				int result = 0;
				
				if( selecionados.length == 1 )
					result = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar este veiculo?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE );
				else
					result = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar estes veiculos?" , "Tem certeza?" , JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE );
				
				//O usuario decidiu por remover os veiculos
				if( result == JOptionPane.YES_OPTION ) {
					int cont = 0,
						i = 0;
					Vector<Integer> idsRemovidos = new Vector<Integer>();	//Contera os IDS dos veiculos a serem removidos
					//Descobre quais veiculos serao removidos
					for( Veiculo v : perfilReferencia.getFrota() ) {
						System.out.println("iteracao");
						if( cont == selecionados[i] ) {
							idsRemovidos.add( v.getVeiculoID() );
							i++;
							if( i >= selecionados.length )
								break;
						}
						cont++;
					}
					
					//Remove os veiculos
					for( i = 0 ; i < idsRemovidos.size() ; i++ ) {
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
				JOptionPane.showMessageDialog(null, "O nome do veículo deve conter no máximo 30 caracteres\ne no mínimo 1 caractere", "Tamnho limite", JOptionPane.WARNING_MESSAGE);
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
	
	public static void main(String[] args) {
		JFrame janela = new JFrame("Teste");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize( 600 , 700 );
		janela.add( new TelaMinhaFrota( new BancoDeDadosPerfil("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/").getPerfil() ) );
		
		janela.setVisible(true);
	}

}
