/* Classe Van para representar na memória em tempo de execução os veículos do tipo Van do negócio.
 * Ultima modificação: 02/06/2018 - 18:12
 * Pendencias: Nenhuma
 */

package entidades;

//Inicio da declaracao da classe Van
public class Van extends Veiculo {
	
	//Combustivel padrao para vans
	private static final Combustivel combustivel = Combustivel.DIESEL;
	
	//Variaveis padrao para a van
	private static final double rendimentoVan = 10,		//Rendimento de combustivel em KM/L
								cargaMaximaVan = 3500,	//Carga maxima em Kg
								velMediaVan = 80,		//Velocidade media em Km/H
								decaimentoVan = 0.001;	//Decaimento no rendimento do combustivel em (Km/L)/Kg
	
	public Van( Status estado , int veiculoID, String nome, String placa){
		super( estado , Tipo.VAN , veiculoID, cargaMaximaVan, velMediaVan, nome, placa );
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

	//Sobrescrita do método getDecaimento
	@Override
	public Double getDecaimento( Combustivel combustivel ){
		return decaimentoVan;

	}//Fim do metodo getDecaimento


	//Sobrescrita do metodo getRedniemtn0o
	@Override
	public Double getRendimento( Combustivel combustivel ){
		return rendimentoVan;

	}//Fim do metodo getRendimento
	
//	//Metodo main para testes unitarios
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Van m = new Van( Status.LIVRE , 1, "Mercedes Sprinter", "OVN-7399" );
//		
//		System.out.println(m);
//		System.out.println( m.getCargaMaxima() );
//		System.out.println( m.getCombustivel(2) );
//		System.out.println( m.getCombustivel(1) );
//		System.out.println( m.getCombustivel(3) );
//		System.out.println( m.getDecaimento( m.getCombustivel(1) ) );
//		System.out.println( m.getEstado() );
//		System.out.println( m.getRendimento( m.getCombustivel(1)) );
//		System.out.println( m.getTipo() );
//		System.out.println( m.getVeiculoID() );
//		System.out.println( m.getVelMedia() );
//		
//		Veiculo v = m;
//		v.setEstado( Status.RESERVADO );
//		System.out.println( v.getEstado() );
//
//	}//FIm do metodo main

}//Fim da classe Van
