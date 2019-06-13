package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidades.Perfil;
import servicos.BancoDeDadosPerfil;

public class TelaAjustes extends JPanel implements Tela{
	
	private final String path = new File("").getAbsolutePath() + "/db/";

	private Perfil perfilReferencia;
	
	private JLabel titulo;
	private JPanel painelCentral;
	
	private JPanel painelCampos;
	private JLabel nomePerfil;
	private JLabel margemLucro;
	private JTextField campoNomePerfil;
	private JTextField campoMargemLucro;
	
	private JPanel painelBotoes;
	private JButton apagarTudo;
	private JButton salvar;
	private JButton help;
	
	public TelaAjustes( Perfil perfilReferencia ) {
		this.perfilReferencia = perfilReferencia;
		setLayout( new BorderLayout(10,10) );
		iniciarCampos();
		exibir();
	}
	
	public void iniciarCampos() {
		titulo = new JLabel( "Ajustes", new ImageIcon( path + "ajustesicon.png") , SwingConstants.LEFT );
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment( SwingConstants.CENTER );
		
		painelCentral = new JPanel();
		painelCentral.setLayout( new GridLayout( 2 , 1 , 10 , 10 ) );
		
		nomePerfil = new JLabel("Nome de Perfil");
		nomePerfil.setHorizontalAlignment(SwingConstants.CENTER);
		nomePerfil.setVerticalAlignment( SwingConstants.BOTTOM );
		margemLucro = new JLabel("Margem de Lucro (%)");
		margemLucro.setHorizontalAlignment( SwingConstants.CENTER );
		margemLucro.setVerticalAlignment( SwingConstants.BOTTOM );
		
		campoNomePerfil = new JTextField(perfilReferencia.getNomeUsuario() , 30);
		campoNomePerfil.setToolTipText("Digite aqui o nome do administrador/empresa");
		
		campoMargemLucro = new JTextField( "" + (perfilReferencia.getMargemLucroPadrao()*100) , 15);
		campoMargemLucro.setToolTipText("Digite aqui a margem de lucro desejada em porcentagem");
		
		painelCampos = new JPanel();
		painelCampos.setLayout( new GridLayout( 2,2,10,10)) ;
		painelCampos.add(nomePerfil);
		painelCampos.add(margemLucro);
		painelCampos.add(campoNomePerfil);
		painelCampos.add(campoMargemLucro);
		
		painelBotoes = new JPanel();
		painelBotoes.setLayout( new FlowLayout() );
		
		apagarTudo = new JButton( new ImageIcon( path + "removericon.png") );
		apagarTudo.setToolTipText("Apaga todas as informaçoes deste Perfil");
		apagarTudo.setHorizontalAlignment( SwingConstants.CENTER );
		apagarTudo.setVerticalAlignment( SwingConstants.CENTER );
		apagarTudo.addActionListener( new AcaoApagar() );
		
		salvar = new JButton( new ImageIcon( path + "salvaricon.png") );
		salvar.setToolTipText("Salva as informações inseridas acima");
		salvar.setHorizontalAlignment( SwingConstants.CENTER );
		salvar.setVerticalAlignment(SwingConstants.CENTER);
		salvar.addActionListener( new AcaoSalvar() );
		
		help = new JButton( new ImageIcon( path + "helpicon.png") );
		help.setToolTipText("Guia");
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setVerticalAlignment(SwingConstants.CENTER);
		help.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog(null, "Reinicie todos os dados do seu Perfil clicando em apagar (Lixeira)\n"
						+ "Salve as informações de Perfil descritas acima clicando em salvar (Disquete)");
			}
		});
		
		painelBotoes.add(apagarTudo);
		painelBotoes.add(salvar);
		painelBotoes.add(help);
		
		
		painelCentral.add( painelCampos );
		painelCentral.add(painelBotoes);
		
		add( titulo , BorderLayout.NORTH );
		add( painelCentral , BorderLayout.CENTER );
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
		campoNomePerfil.setText(perfilReferencia.getNomeUsuario());
		campoMargemLucro.setText(""+ (perfilReferencia.getMargemLucroPadrao()*100));
	}
	
	private class AcaoApagar implements ActionListener{
		public void actionPerformed( ActionEvent event ) {
			if( JOptionPane.showConfirmDialog(TelaAjustes.this, "Realmente deseja apagar todos os dados deste perfil?\n"
					+ "Essa ação não poderá ser desfeita", "Tem certeza disso?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				
				try( BufferedWriter perfilDB = new BufferedWriter( new FileWriter( path + ".perfil") ) ){
					
					perfilDB.write("Gerenciador de Fretes");
					perfilDB.newLine();
					perfilDB.write( "0.1" );
					
					perfilReferencia.limparFrota();
					try( BufferedWriter frotaDB = new BufferedWriter( new FileWriter( path + ".fretes") ) ){
						perfilReferencia.limparHistoricoFretes();
					}
					JOptionPane.showMessageDialog(null, "Dados apagados com sucesso!" , "Sucesso!" , JOptionPane.INFORMATION_MESSAGE);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ocorreu um erro interno durante a remoção dos dados." , "Erro" , JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		}
	}
	
	private class AcaoSalvar implements ActionListener{
		public void actionPerformed( ActionEvent event ) {
			String nome = campoNomePerfil.getText();
			String margemLucro = campoMargemLucro.getText();
			
			if( ! isInputValido( margemLucro ) ) {
				JOptionPane.showMessageDialog(null, "Digite apenas números válidos para margem de lucro" , "Erro de escrita" , JOptionPane.ERROR_MESSAGE);
			}
			else {
				try( BufferedWriter perfilDB = new BufferedWriter( new FileWriter( path + ".perfil") ) ){
					
					double lucroDecimal = Double.parseDouble(margemLucro) / 100;
					perfilDB.write(nome);
					perfilDB.newLine();
					perfilDB.write(""+lucroDecimal);
					
					
					
					perfilReferencia.setMargemLucroPadrao( lucroDecimal );
					perfilReferencia.setNomeUsuario( nome );
					
					JOptionPane.showMessageDialog(null, "Dados Salvos com sucesso!", "Sucesso!" , JOptionPane.INFORMATION_MESSAGE);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ocorreu um erro interno durante o salvamento dos dados." , "Erro" , JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		}
		
		private boolean isInputValido( String input ) {
			if( input.length() == 0 )
				return false;
			
			else {
				boolean isDecimal = false;
				for( int i = 0 ; i < input.length() ; i++ ) {
					char cur = input.charAt(i);
					
					if( i == 0 && !( cur == '1' || cur == '2' || cur == '3' || cur == '4' || cur == '5' || cur == '6' || cur == '7' ||
						cur == '8' || cur == '9' || cur == '0'  ) ) {
						return false;
					}
					
					else if( cur == '.' && isDecimal )
						return false;
					
					else if( cur == '.' && ! isDecimal ) {
						isDecimal = true;
					}
					
					else if( !( cur == '1' || cur == '2' || cur == '3' || cur == '4' || cur == '5' || cur == '6' || cur == '7' ||
						cur == '8' || cur == '9' || cur == '0'  ) ) {
						return false;
					}
				}
				
				return true;
			}
		}
	}
	
//	public static void main( String[]args ) {
////		JFrame jan= new JFrame("Treste");
////		jan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		jan.setSize(600,500);
////		jan.add( new TelaAjustes( new BancoDeDadosPerfil("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/").getPerfil() ) );
////		jan.setVisible(true);
//	}
}
