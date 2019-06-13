/* Classe ArquivadorDadosFrota, utilizada para armazenar na dataBase a atual frota descrita em memoria
 * Ultima modificacao: 06/06/2019 - 13:30
 * Pendencias: NENHUMA
 */
package servicos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import entidades.Carro;
import entidades.Veiculo;
import entidades.Veiculo.Status;

public class ArquivadorDadosFrota {
	
	private Set<Veiculo> frota;	//Referencia para a frota atual do negocio(execucao atual)
	private String caminho;		//Referencia para o caminho que contem o banco de dados
	
	//Construtor para um objeto SalvadorDeDadosFrota
	public ArquivadorDadosFrota( String caminho , Set<Veiculo> frota ) {
		setFrota( frota );
		setCaminho(caminho);
		
	}//Fim do construtor
	
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
	
	//Alterar a frota para qual este objeto aponta
	public void setFrota( Set<Veiculo> frota ) {
		this.frota = frota;
		
	}//Fim do metodo
	
	
	//Retorna o caminho para qual banco de dados este objeto aponta
	public String getCaminho() {
		return caminho;
		
	}//Fim do metodo
	
	
	//Altera o caminho do banco de dados para qual este objeto aponta
	public void setCaminho( String caminho ) {
		this.caminho = caminho;
		
	}//Fim do metodo
	
	//Metodo principal desta classe: Atualiza o banco de dados com base na frota em que este objeto aponta
	//Obs: o banco de dados passará a ter apenas informaçoes sobre esta frota
	public void salvarDadosAtuais() {
		//Bloco try with resources
		try( BufferedWriter dataBase = new BufferedWriter( new FileWriter(caminho) ) ){
			
			//Iterando sobre o conjunto de veiculos da frota
			for( Veiculo veiculo : frota ) {
				dataBase.write( gerarLinhaVeiculo( veiculo ) );		//Inserindo a linha montada acima no banco de dados
				dataBase.newLine();					//Quebra de linha para sinalizar fim da linha de dados
				
			}//Fim da iteracao
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		
	}//Fim do metodo

	
	//Metodo main para testes unitarios com banco de dados e salvamento de dados
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Extraindo a frota ja existente no arquivo
//		Set<Veiculo> kappa = new ExtratorDadosFrota( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/.frota" ).getFrota();
//		System.out.println(kappa.size());
//		
//		boolean k = kappa.add( new Carro( Status.LIVRE , kappa.size() , "BMW M5 V10 POWER 550 Hp" , "KJK-5522" ) );	//Adicionando um novo veiculo na frota
//		if(!k)
//			System.out.println("What?");
//		
//		System.out.println(kappa.size());
//		
//		for( Veiculo a : kappa )
//			System.out.println(a);
//		
//		//Salvando a nova frota no banco de dados
//		new ArquivadorDadosFrota( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/.frota" , kappa ).salvarDadosAtuais();
		
	}//Fim do metodo main

}
