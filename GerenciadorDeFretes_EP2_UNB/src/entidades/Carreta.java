/* Classe Carreta para representar na memória em tempo de execução os veículos do tipo Carreta do negócio.
 * Ultima modificação: 02/06/2018 - 18:13
 * Pendencias: Nenhuma
 */

package entidades;

public class Carreta extends Veiculo {
	public static final Combustivel combustivel = Combustivel.DIESEL;
	
	public static final double rendimentoCarreta = 8,			//Km/L
							   cargaMaximaCarreta = 30000, 		//Kg
							   velMediaCarreta = 60,			//Km/H
							   decaimentoCarreta = 0.0002;		//Km/L
	
	
	//Construtor para veiculos do tipo Carreta
	public Carreta( Status estado , int veiculoID, String nome, String placa ) {
		super( estado , Tipo.CARRETA , veiculoID , rendimentoCarreta , cargaMaximaCarreta, velMediaCarreta , decaimentoCarreta, nome, placa);
	
	}//Fim do construtor
	
	//Sobrescrita do método getCombustivel
	@Override
	public Combustivel getCombustivel( int qual ) {
		if( !( qual == 1 || qual == 2 ) )
			System.out.println("Aviso! Indice de combustivel invalido passado em public Combustivel getCombustivel( int qual  )");
		return combustivel;
		
	}//Fim do método getCombustivel
	
	public static void main(String[] args) {
		Carreta c = new Carreta( Status.LIVRE , 0, "Scannia 113h", "DCO-3421" );
		System.out.println(c.getCargaMaxima());
		System.out.println(c.getDecaimento());
		System.out.println(c.getVeiculoID());
		System.out.println(c.getVelMedia());
		System.out.println(c);
		
		Veiculo v = c;
		if( v.getCombustivel(2) == Combustivel.DIESEL )
			System.out.println("Diesel");
	}
}
