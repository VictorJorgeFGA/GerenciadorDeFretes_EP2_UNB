/* Classe carro, utilizada para representar os veículos, que são carros, na memória em tempo de execução
 * Ultima modificação: 02/06/2018 - 18:14
 * Pendencias: NENHUMA
 */

package entidades;

//Inicio da declaração da classe Carro, que herda da classe Veiculo
public class Carro extends Veiculo {
	
	public static final Combustivel combustivel1 = Combustivel.GASOLINA,
									combustivel2 = Combustivel.ALCOOL;
	
	public static final double rendimentoCarroGasolina = 14,				//Km/L
							   rendimentoCarroAlcool = 12,
							   cargaMaximaCarro = 360, 				//Kg
							   velMediaCarro = 100,					//Km/H
							   decaimentoCarroGasolina = 0.025,		//Km/L
							   decaimentoCarroAlcool = 0.0231;
	
	//Inicio do construtor da classe Carro
	public Carro( Status estado , int veiculoID, String nome, String placa ) {
		super( estado , Tipo.CARRO , veiculoID , cargaMaximaCarro, velMediaCarro , nome, placa );
		
	}//Fim do construtor da classe carro
	
	@Override
	public Combustivel getCombustivel( int qual ) {
		if( qual == 1 )
			return combustivel1;
		
		else if( qual == 2 )
			return combustivel2;
		
		else if( !( qual == 1 || qual == 2 ) )
			System.out.println("Aviso! Indice de combustivel invalido passado em public Combustivel Carro::getCombustivel( int qual  )");
		
		return combustivel1;
	}

	//Sobrescrita do método getDecaimento
	@Override
	public Double getDecaimento( Combustivel combustivel ){
		if( combustivel == combustivel1 )
			return decaimentoCarroGasolina;
		else if( combustivel == combustivel2 )
			return decaimentoCarroAlcool;

		else{
			System.out.println("Aviso! Combustivel invalido fornecido em public Double Carro::getDecaimento( Combustivel combustivel )");
			return -1D;
		} 

	}//Fim do método getDecaimento

	//Sobrescrita do método getRendimento
	@Override
	public Double getRendimento( Combustivel combustivel ){
		if( combustivel == combustivel1 )
			return rendimentoCarroGasolina;
		else if( combustivel == combustivel2 )
			return rendimentoCarroAlcool;
		else{
			System.out.println("Aviso! Combustivel invalido fornecido em public Double Carro::getRendimento( Combustivel combustivel )");
			return -1D;
		} 
		
	}//Fim do método getRendimento


	//Inicio da main de teste unitário.
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Carro c = new Carro( Status.LIVRE , 0, "Fiat Uno Mille com escada", "KJP-1351" );
		System.out.println(c);
		System.out.println( c.getCargaMaxima() );
		System.out.println( c.getCombustivel(1) );
		System.out.println( c.getDecaimento( c.getCombustivel(1) ) );
		System.out.println( c.getDecaimento( c.getCombustivel(2) ) );
		System.out.println( c.getDecaimento( c.getCombustivel(3) ) );
		System.out.println( c.getVeiculoID() );
		System.out.println( c.getVelMedia() );
		
		Veiculo v = c;
		v.setEstado( Status.RESERVADO );
		System.out.println( v );
		
	}//Fim do método main

}//Fim da declaração da classe carro
