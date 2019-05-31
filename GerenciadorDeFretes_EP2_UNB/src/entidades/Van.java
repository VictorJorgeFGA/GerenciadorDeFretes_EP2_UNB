/* Classe Van para representar na memória em tempo de execução os veículos do tipo Van do negócio.
 * Ultima modificação: 31/05/2018 - 15:06
 * Pendencias: Nenhuma
 */

package entidades;

import entidades.Veiculo.Combustivel;
import entidades.Veiculo.Status;
import entidades.Veiculo.Tipo;

//Inicio da declaracao da classe Van
public class Van extends Veiculo {
	
	//Combustivel padrao para vans
	private static final Combustivel combustivel = Combustivel.DIESEL;
	
	
//	Combustível: Diesel
//
//	Rendimento: 10 Km/L
//
//	Carga máxima: 3,5 toneladas
//
//	Velocidade média: 80 Km/h
//	A cada Kg de carga, o rendimento é reduzido em 0.001 Km/L
	
	//Variaveis padrao para a van
	private static final double rendimentoVan = 10,		//Rendimento de combustivel em KM/L
								cargaMaximaVan = 3500,	//Carga maxima em Kg
								velMediaVan = 80,		//Velocidade media em Km/H
								decaimentoVan = 0.001;	//Decaimento no rendimento do combustivel em (Km/L)/Kg
	
	Van( Status estado , int veiculoID ){
		super( estado , Tipo.VAN , veiculoID, rendimentoVan, cargaMaximaVan, velMediaVan, decaimentoVan );
	}
	
	
	//Retorna o combustivel deste Veiculo, qual podendo variar entre 1 e 2.
	@Override
	public Combustivel getCombustivel( int qual ) {
		if( qual == 1 || qual == 2 )
			return combustivel;
		else
			System.out.println("Aviso! Combustivel invalido requisitado em public Combustivel Van::getCombustivel( int qual ) : " + qual );
		return combustivel;
		
	}//Fim do metodo getCombustivel
	
	//Metodo main para testes unitarios
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Van m = new Van( Status.LIVRE , 1 );
		
		System.out.println(m);
		System.out.println( m.getCargaMaxima() );
		System.out.println( m.getCombustivel(2) );
		System.out.println( m.getCombustivel(1) );
		System.out.println( m.getCombustivel(3) );
		System.out.println( m.getDecaimento() );
		System.out.println( m.getEstado() );
		System.out.println( m.getRendimento() );
		System.out.println( m.getTipo() );
		System.out.println( m.getVeiculoID() );
		System.out.println( m.getVelMedia() );
		
		Veiculo v = m;
		v.setEstado( Status.RESERVADO );
		System.out.println( v.getEstado() );

	}//FIm do metodo main

}//Fim da classe Van
