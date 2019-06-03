/* Classe ExtratorDeDados: utilizada para extrair dados do banco de dados e converte-los em objetos tangiveis para o programa
 * Ultima modificação: 03/06/2018 - 12:32
 * Pendencias: NENHUMA
 */

package servicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import entidades.Carreta;
import entidades.Carro;
import entidades.Moto;
import entidades.Van;
import entidades.Veiculo;
import entidades.Veiculo.Status;

//Inicio da declaracao da classe
public class ExtratorDeDados {
	private TreeSet<Veiculo> frota;	//Conjunto que guardara a frota de veiculos a ser extraida do arquivo
	
	//Construtor baseado em arquivo de texto
	public ExtratorDeDados( String caminho ){
		//Bloco try with resources
		try( BufferedReader dataBase = new BufferedReader(new FileReader(caminho)) ){
			//Instanciacao do conjunto que guardara a frota de veiculos
			frota = new TreeSet<Veiculo>();
			String linhaAtual = null;	//Guarda a ultima linha lida
			int atualID = 0;			//Id para cada veiculo (em ordem crescente e sequencial)
			
			//Le o arquivo ate EOF ( Assumindo que este nao esteja corrompido )
			while( (linhaAtual = dataBase.readLine()) != null ) {
				String[] informacoesLinha = linhaAtual.split(";");	//Separacao dos campos da linha em: TIPO ; STATUS ; NOME ; PLACA
				
				//Transformando a informacao de STATUS do veiculo de STRING para enum Status
				Status estadoVeiculo = informacoesLinha[1].equals( "L" ) ? Status.LIVRE : Status.RESERVADO;
				
				//Instanciando um novo veiculo com base no TIPO
				switch( informacoesLinha[0] ) {
				case "T":
					frota.add( new Carreta( estadoVeiculo, atualID, informacoesLinha[2], informacoesLinha[3] ) );
					break;
				case "C":
					frota.add( new Carro( estadoVeiculo, atualID , informacoesLinha[2], informacoesLinha[3] ) );
					break;
				case "V":
					frota.add( new Van( estadoVeiculo, atualID , informacoesLinha[2], informacoesLinha[3] ) );
					break;
				case "M":
					frota.add( new Moto( estadoVeiculo, atualID , informacoesLinha[2], informacoesLinha[3] ) );
					break;
				}
				atualID++;	//Incrementando o ID para um possivel proximo veiculo a ser alocado
			}
		}
		catch ( IOException e ) {
			System.out.println("Erro na leitura/abertura da dataBAse " + e.getMessage());
		}
		
	}//Fim do construtor
	
	//Retorna um TreeSet que contem todos os veiculos existentes na frota representada na dataBase
	public final TreeSet<Veiculo> getFrota(){
		return frota;
	
	}//Fim do metodo getFrota
	
	//Main para teste unitario
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Veiculo> frota = new ExtratorDeDados( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/.frota" ).getFrota();

		for( Veiculo v : frota )
			System.out.println( v );
		
	}//Fim da Main

}//Fim da declaracao da classe
