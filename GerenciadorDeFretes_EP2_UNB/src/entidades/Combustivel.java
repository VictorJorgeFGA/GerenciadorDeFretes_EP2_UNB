/* Enum type que define cada tipo de combustivel
 * Ultima modificacao 07/06/2019 - 9:50
 * Pendencias: NENHUMA
 */

package entidades;

public enum Combustivel {
	
	ALCOOL( 3.499 ),
	DIESEL( 3.869 ),
	GASOLINA( 4.449 );
	
	Double precoPorLitro;
	
	private Combustivel( double precoPorLitro ) {
		this.precoPorLitro = precoPorLitro;
	}
	
	public Double getPrecoPorLitro() {
		return precoPorLitro;
	}
}
