/* Classe ArquivadorDadosFrete, utilizada para arquivar fretes em arquivos.
 * Ultima modificacao: 13:30
 * Pendencias: NENHUMA
 */
package servicos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import entidades.Carro;
import entidades.Frete;
import entidades.Veiculo;
import entidades.Veiculo.Status;

//Inicio da declaracao da classe
public class ArquivadorDadosFrete {
	Queue<Frete> listaFretes;	//Fila de fretes fornecidos
	String caminho;				//Caminho da dataBase (arquivo onde serao armazenadas as informacoes)
	
	//Construtor que recebe a fila de fretes já pronta
	public ArquivadorDadosFrete( String caminho , Queue<Frete> listaFretes ) {
		this.listaFretes = new LinkedList<Frete>();
		setCaminho( caminho );
		setListaFretes( listaFretes );
		
	}
	
	//Construtor que recebe um frete unico realizado
	public ArquivadorDadosFrete( String caminho , Frete freteUnico ) {
		this.listaFretes = new LinkedList<Frete>();
		adicionarFrete( freteUnico );
		setCaminho( caminho );
	}
	
	//Metodo para alterar o caminho da dataBase
	private void setCaminho( String caminho ) {
		this.caminho = caminho;
		
	}
	
	//Metodo para alterar a fila de fretes
	private void setListaFretes( Queue<Frete> listaFretes ) {
		this.listaFretes = listaFretes;
		
	}
	
	//Metodo interno utilizado para gerar uma linha de informacoes sobre um veiculo no formato dataBase 
	private String gerarLinhaVeiculo( Veiculo veiculo ) {
		
		String linhaDeDados = new String("");	//Linha que sera inserida no banco de dados
		
		linhaDeDados += veiculo.getVeiculoID();
		
		//Conversao de Enum Tipo para Char
		switch(veiculo.getTipo()) {
		case CARRETA:
			linhaDeDados += ";T";
			break;
		case CARRO:
			linhaDeDados += ";C";
			break;
		case MOTO:
			linhaDeDados += ";M";
			break;
		case VAN:
			linhaDeDados += ";V";
			break;
		}
		
		//Conversao de Enum Status para Char
		switch( veiculo.getEstado() ) {
		case LIVRE:
			linhaDeDados += ";L";
			break;
		case RESERVADO:
			linhaDeDados += ";R";
			break;
		}
		
		linhaDeDados += ";" + veiculo.getNome();		//Adicionando o nome do veiculo na linha de dados
		linhaDeDados += ";" + veiculo.getPlaca();		//Adicionando a placa do veiculo na linha de dados
		
		return linhaDeDados;
		
	}
	
	//Insere na dataBase toda a fila de fretes já armazenada aqui.
	//A medida que insere em arquivo, a fila aqui é podada.
	public void salvarDadosAtuais() {
		
		//Bloco try with resources
		try( BufferedWriter dataBase = new BufferedWriter( new FileWriter( caminho , true ) ) ){
			
			SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			Frete toRegistrar = null;
	
			//Enquanto existirem fretes na fila, inserir em arquivo
			while( (toRegistrar = listaFretes.poll()) != null ) {
				
				String linha = formatadorData.format( toRegistrar.getData() ) + "&" 
													 + toRegistrar.getCarga() + "&"
													 + toRegistrar.getCusto() + "&"
													 + toRegistrar.getLucro() + "&"
													 + toRegistrar.getTempoGasto();
				dataBase.write(linha);
				dataBase.newLine();
				//System.out.println(linha);
				
				Set<Veiculo> frotaUtilizada = toRegistrar.getFrotaUtilizada();
				
				//Percorre toda a frota utilizada no frete gerando uma linha unica (no formato dataBase) para o veiculo utilizado
				for( Veiculo veiculo : frotaUtilizada ) {	
					dataBase.write( gerarLinhaVeiculo( veiculo ) );
					dataBase.newLine();
					
				}
				dataBase.write("#");
				dataBase.newLine();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	//Metodo para adicionar um novo frete realizado na fila para armazenamento
	public void adicionarFrete( Frete frete ) {
		listaFretes.add(frete);
		
	}

	public static void main(String[] args) {
//		
//		Set<Veiculo> frotaUtilizada = new TreeSet<>();
//		frotaUtilizada.add( new Carro( Status.RESERVADO , 1 , "Subaru WRX STI 2011 Hatch" , "JGP-1972" ) );
//		frotaUtilizada.add( new Carro( Status.RESERVADO , 2 , "BMW M5 V10 Sport" , "OVP-2343" ) );
//		
//		Frete f1 = new Frete( frotaUtilizada , 300 , 40 , 30 , 20 , new Date() );
//		Queue<Frete> fretes = new LinkedList<Frete>();
//		fretes.add(f1);
//		fretes.add(f1);
//		
//		
//		new ArquivadorDadosFrete( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/.fretes" , fretes ).salvarDadosAtuais();;
	}

}
