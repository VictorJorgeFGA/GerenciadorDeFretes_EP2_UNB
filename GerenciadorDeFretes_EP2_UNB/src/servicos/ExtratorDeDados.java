/* Classe ExtratorDeDados: utilizada para extrair dados do banco de dados e converte-los em objetos tangiveis para o programa
 * Ultima modificação: 02/06/2018 - 19:15
 * Pendencias: Terminar a funcao de extracao de dados, testar a classe e comentar os recursos.
 */

package servicos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeSet;

import entidades.Carreta;
import entidades.Carro;
import entidades.Moto;
import entidades.Van;
import entidades.Veiculo;
import entidades.Veiculo.Status;

public class ExtratorDeDados {
	private TreeSet<Veiculo> frota;
	private static final long bytesBloco = 42;
	
	private void extrairDados( RandomAccessFile dataBase ) {
		try {
			dataBase.seek(0);
			long tamanhoArq = dataBase.length();
			int atualID = 0;
			boolean continuarLendo = false;
			
			//Se o arquivo possuir pelo menos uma linha de informacao
			if( tamanhoArq >= bytesBloco )
				continuarLendo = true;

			while( continuarLendo ) {
				char tipo =  (char) dataBase.readByte();
				
				//Caso Este campo nao possua um tipo de Veiculo definido significa que eh um campo vazio
				if( tipo == '#' ) {
					
					//Caso avancar uma linha exceda o tamanho do arquivo
					if( (dataBase.getFilePointer() + bytesBloco -1) > tamanhoArq )
						continuarLendo = false;
					
					else {
						dataBase.seek( dataBase.getFilePointer() + bytesBloco -1  );
					}
				}
				else {
					char estadoVeiculo = (char) dataBase.readByte();
					String[] restanteDaLinha = dataBase.readLine().split(";");
					String nomeVeiculo = restanteDaLinha[0];
					String placaVeiculo = restanteDaLinha[1];
					
					switch( tipo ) {
					case 'T':
						frota.add( new Carreta( estadoVeiculo == 'L' ? Status.LIVRE : Status.RESERVADO, atualID, nomeVeiculo, placaVeiculo ) );
						break;
					case 'C':
						frota.add( new Carro( estadoVeiculo == 'L' ? Status.LIVRE : Status.RESERVADO, atualID, nomeVeiculo, placaVeiculo ) );
						break;
					case 'M':
						frota.add( new Moto( estadoVeiculo == 'L' ? Status.LIVRE : Status.RESERVADO, atualID, nomeVeiculo, placaVeiculo ) );
						break;
					case 'V':
						frota.add( new Van( estadoVeiculo == 'L' ? Status.LIVRE : Status.RESERVADO, atualID, nomeVeiculo, placaVeiculo ) );
						break;
					}
					
					atualID++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ExtratorDeDados( String caminho ){
		try( RandomAccessFile dataBase = new RandomAccessFile( new File(caminho) , "r" ) ){
			extrairDados( dataBase );
		}
		catch( IOException e ) {
			System.out.println("Erro na leitura/abertura da dataBase: " + e.getMessage());
		}
	}
	
	//Retorna um TreeSet que contem todos os veiculos existentes na frota representada na dataBase
	public final TreeSet<Veiculo> getFrota(){
		return frota;
	
	}//Fim do metodo getFrota

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
