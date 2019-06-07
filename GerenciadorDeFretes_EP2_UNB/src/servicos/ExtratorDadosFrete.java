/* Classe ExtratorDadosFrete, utilizada para extrair informacoes de fretes armazenadas em arquivo.
 * Ultima modificação: 06/06/2019 - 13:30
 * Pendencias: NENHUMA
 */
package servicos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import entidades.Carreta;
import entidades.Carro;
import entidades.Frete;
import entidades.Moto;
import entidades.Van;
import entidades.Veiculo;
import entidades.Veiculo.Status;

//Inicio da declaração da classe
public class ExtratorDadosFrete {
	
	private Queue<Frete> extratoFretes;		//Fila de extratos extraida
	private String caminho;					//Caminho para a dataBase
	
	
	//Construtor padrao unico da classe
	public ExtratorDadosFrete( String caminho ) {
		setCaminho( caminho );
		extrairDados();
		
	}
	
	//Altera o caminho da dataBase
	private void setCaminho( String caminho ) {
		this.caminho = caminho;
		
	}
	
	//Metodo privado utilizado para extracao e conversao de dados dataBase para objetos de execucao
	private void extrairDados() {
		extratoFretes = new LinkedList<Frete>();
		
		//Bloco try with resources
		try( BufferedReader dataBase = new BufferedReader( new FileReader( caminho ) ) ){
			
			while( true ) {
				String dadosAtuais = dataBase.readLine();	//Recebe a proxima primeira linha de um bloco de dados
				
				//Se atingiu EOF, parar
				if( dadosAtuais == null )
					break;
				
				//Separacao da informacao
				String[] informacao = dadosAtuais.split("&");
				
				
				//Formatacoes
				Date data = new SimpleDateFormat( "dd/MM/yyyy - HH:mm:ss" ).parse( informacao[0] );
				Double carga = Double.parseDouble( informacao[1] );
				Double custo = Double.parseDouble( informacao[2] );
				Double lucro = Double.parseDouble( informacao[3] );
				Double tempoGasto = Double.parseDouble( informacao[4] );
		
				//Linha que recebera dados de veiculos da frota
				String linhaAtual = null;
				Set<Veiculo> frota = new TreeSet<Veiculo>();
				
				//Percorre as linhas referentes a frota utilizada
				while( true ) {
					
					linhaAtual = dataBase.readLine();
					
					//Se atingiu EOF, parar
					if( linhaAtual == null )
						break;
					//Se atingiu fim de bloco, parar
					if( linhaAtual.equals("#") )
						break;
					
					String[] informacoesLinha = linhaAtual.split(";");	//Separacao dos campos da linha em: ID ; TIPO ; STATUS ; NOME ; PLACA
					
					int atualID = Integer.parseInt(informacoesLinha[0]);
					
					//Transformando a informacao de STATUS do veiculo de STRING para enum Status
					Status estadoVeiculo = informacoesLinha[2].equals( "L" ) ? Status.LIVRE : Status.RESERVADO;
					
					//Instanciando um novo veiculo com base no TIPO
					switch( informacoesLinha[1] ) {
					case "T":
						frota.add( new Carreta( estadoVeiculo, atualID, informacoesLinha[3], informacoesLinha[4] ) );
						break;
					case "C":
						frota.add( new Carro( estadoVeiculo, atualID , informacoesLinha[3], informacoesLinha[4] ) );
						break;
					case "V":
						frota.add( new Van( estadoVeiculo, atualID , informacoesLinha[3], informacoesLinha[4] ) );
						break;
					case "M":
						frota.add( new Moto( estadoVeiculo, atualID , informacoesLinha[3], informacoesLinha[4] ) );
						break;
					}
					
				}
				
				//Adiciona o Frete formatado  na fila de extratos
				extratoFretes.add( new Frete( frota , carga , custo , lucro , tempoGasto , data ) );
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//Retorna a fila de fretes realizados
	public Queue<Frete> getExtratoFretes(){
		return extratoFretes;
		
	}
	
	//Teste unitario
	public static void main(String [] args) {
		
		ExtratorDadosFrete ext = new ExtratorDadosFrete( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/.fretes" );
		
		Queue<Frete> fretesRealizados = ext.getExtratoFretes();
		for( Frete f : fretesRealizados ) {
			System.out.println(f + "\n");
		}
	}
	
}//Fim da declaracao da classe
