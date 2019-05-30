package entidades;

import entidades.Veiculo.Status;
import entidades.Veiculo.Tipo;

public class Carreta extends Veiculo {
	public static final Combustivel combustivel = Combustivel.DIESEL;
	public static final double rendimentoCarreta = 8,	//Km/L
							   cargaMaximaCarreta = 30, //Toneladas
							   velMediaCarreta = 60,			//Km/H
							   decaimentoCarreta = 0.0002;	//Km/L
	
	
	public Carreta( Status estado , int veiculoID ) {
		super( estado , Tipo.CARRETA , veiculoID , rendimentoCarreta , cargaMaximaCarreta, velMediaCarreta , decaimentoCarreta );
	}
	
	@Override
	public Combustivel getCombustivel( int qual ) {
		if( !( qual == 1 || qual == 2 ) )
			System.out.println("Aviso! Indice de combustivel invalido passado em public Combustivel getCombustivel( int qual  )");
		return combustivel;
	}
	
	public static void main(String[] args) {
		Carreta c = new Carreta( Status.LIVRE , 0 );
		System.out.println(c.getCargaMaxima());
		System.out.println(c.getDecaimento());
		System.out.println(c.getVeiculoID());
		System.out.println(c.getVelMedia());
		
		Veiculo v = c;
		if( v.getCombustivel(2) == Combustivel.DIESEL )
			System.out.println("Diesel");
	}
}
