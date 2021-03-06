/* Classe Moto para representar na memória em tempo de execução os veículos do tipo Moto do negócio.
 * Ultima modificação: 02/06/2018 - 18:15
 * Pendencias: Nenhuma
 */
package entidades;

//Inicio da declaracao da classe Moto
public class Moto extends Veiculo {
	
	private static final Combustivel combustivel1 = Combustivel.GASOLINA,	//Combustivel 1 para moto
									 combustivel2 = Combustivel.ALCOOL;		//Combustivel 2 para moto
	
	private static final double rendimentoMotoGasolina = 50,	//Rendimento de combustivel em KM/L
								rendimentoMotoAlcool = 43,		//Rendimento de combustivel em KM/L
								cargaMaximaMoto = 50,	//Carga maxima em Kg
								velMediaMoto = 110,		//Velocidade media em Km/H
								decaimentoMotoGasolina = 0.3,	//Decaimento no rendimento do combustivel em (Km/L)/Kg
								decaimentoMotoAlcool = 0.4;
	
	//Inicio do construtor da classe Moto
	public Moto( Status estado , int veiculoID, String nome, String placa) {
		super( estado , Tipo.MOTO , veiculoID, cargaMaximaMoto, velMediaMoto, nome, placa );
		
	}//Fim do construtor da classe Moto 
	
	//Retorna o combustivel deste Veiculo, qual podendo variar entre 1 e 2.
	@Override
	public Combustivel getCombustivel( int qual ) {
		if( qual == 1 )
			return combustivel1;
		
		else if( qual == 2 )
			return combustivel2;
		
		if( !( qual == 1 || qual == 2 ) )
			System.out.println("Aviso! Combustivel invalido requisitado em public Combustivel Moto::getCombustivel( int qual ) : " + qual );
		
		return combustivel1;
		
	}//Fim do metodo getCombustivel

	//Sobrescrita do método getDecaimento
	@Override
	public Double getDecaimento( Combustivel combustivel ){
		if( combustivel == combustivel1 )
			return decaimentoMotoGasolina;
		else if( combustivel == combustivel2 )
			return decaimentoMotoAlcool;
		else{
			System.out.println("Aviso! Combustivel invalido fornecido em public Double Moto::getDecaimento( Combustivel combustivel )");
			return -1D;
		}
	}//Fim do método getDecaimento

	//Sobrescrita do metodo getRendimento
	@Override
	public Double getRendimento( Combustivel combustivel ){
		if( combustivel == combustivel1 ){
			return rendimentoMotoGasolina;
		}
		else if( combustivel == combustivel2 )
			return rendimentoMotoAlcool;
		else{
			System.out.println("Aviso! Combustivel invalido fornecido em public Double Moto::getRendimento( Combustivel combustivel )");
			return -1D;
		}
	}//Fim do método getRendimento
	
//	//Metodo main para teste unitario
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Moto m = new Moto( Status.LIVRE , 1, "CG 125", "ABC-1234" );
//		System.out.println(m);
//		System.out.println( m.getCargaMaxima() );
//		System.out.println( m.getCombustivel(2) );
//		System.out.println( m.getCombustivel(1) );
//		System.out.println( m.getCombustivel(3) );
//		System.out.println( m.getDecaimento( m.getCombustivel(1) ) );
//		System.out.println( m.getDecaimento( m.getCombustivel(2) ) );
//		System.out.println( m.getEstado() );
//		System.out.println( m.getRendimento( m.getCombustivel(1) ) );
//		System.out.println( m.getTipo() );
//		System.out.println( m.getVeiculoID() );
//		System.out.println( m.getVelMedia() );
//		
//		Veiculo v = m;
//		v.setEstado( Status.RESERVADO );
//		System.out.println( v.getEstado() );
//
//	}//Fim do metodo main

}//Fim da classe Moto
