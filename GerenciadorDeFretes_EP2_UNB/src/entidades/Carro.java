package entidades;

import entidades.Veiculo.Combustivel;
import entidades.Veiculo.Status;
import entidades.Veiculo.Tipo;

public class Carro extends Veiculo {
	public static final Combustivel combustivel1 = Combustivel.GASOLINA,
									combustivel2 = Combustivel.ALCOOL;
	public static final double rendimentoCarro = 14,		//Km/L
							   cargaMaximaCarro = 360, 		//Toneladas
							   velMediaCarro = 100,			//Km/H
							   decaimentoCarro = 0.025;		//Km/L
	
	
	public Carro( Status estado , int veiculoID ) {
		super( estado , Tipo.CARRO , veiculoID , rendimentoCarro , cargaMaximaCarro, velMediaCarro , decaimentoCarro );
	}
	
	@Override
	public Combustivel getCombustivel( int qual ) {
		if( qual == 1 )
			return combustivel1;
		
		else if( qual == 2 )
			return combustivel2;
		
		else if( !( qual == 1 || qual == 2 ) )
			System.out.println("Aviso! Indice de combustivel invalido passado em public Combustivel getCombustivel( int qual  )");
		
		return combustivel1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carro c = new Carro( Status.LIVRE , 0 );
		System.out.println(c);
		System.out.println( c.getCargaMaxima() );
		System.out.println( c.getCombustivel(1) );
		System.out.println( c.getDecaimento() );
		System.out.println( c.getVeiculoID() );
		System.out.println( c.getVelMedia() );
		
		Veiculo v = c;
		v.setEstado( Status.RESERVADO );
		System.out.println( v );
	}

}
