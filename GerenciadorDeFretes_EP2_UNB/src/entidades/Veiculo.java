/* Classe abstrata Veiculo que será utilizada para representar todo tipo de veículo do negócio na memória em tempo de execução
 * Ultima modificação: 06/06/2019 - 13:31
 * Pendencias: NENHUMA
 */

package entidades;

//Inicio da declaração da classe abstrata Veiculo
public abstract class Veiculo implements Comparable<Veiculo>{
	
	public enum Status { RESERVADO , LIVRE };					//Enum que descreve o estado do veiculo no negócio
	public enum Tipo { CARRETA , CARRO , VAN , MOTO };			//Enum que descreve o tipo do veiculo no negócio
	
	private Status estado;
	private Tipo tipo;
	
	private Integer veiculoID;
	private Double cargaMaxima,
				   velMedia;
	
	private String nome,
				   placa;
	
	//Inicio do construtor da classe Veiculo
	public Veiculo(Status estado, Tipo tipo, int veiculoID, double cargaMaxima, double velMedia, String nome, String placa) {
		
		this.estado = estado;
		this.tipo = tipo;
		this.veiculoID = veiculoID;
		this.cargaMaxima = cargaMaxima;
		this.velMedia = velMedia;
		this.nome = nome;
		this.placa = placa;
		
	}//Fim do construtor da classe veiculo
	
	
	//Retorna o estado deste veiculo
	public final Status getEstado() {
		return estado;
	}
	
	//Retorna o tipo deste veiculo
	public final Tipo getTipo() {
		return tipo;
	}
	
	//Retorna o ID deste veiculo
	public final Integer getVeiculoID() {
		return veiculoID;
	}
	
	//Setta o estado deste veiculo
	public void setEstado( Status estado ) {
		this.estado = estado;
	}
	
	//Retorna a carga maxima suportada por este veiculo
	public final Double getCargaMaxima() {
		return cargaMaxima;
	}
	
	//Retorna a velocidade media deste Veiculo
	public final Double getVelMedia() {
		return velMedia;
	}
	
	//Retorna o nome deste Veiculo
	public final String getNome() {
		return nome;
	}
	
	//Retorna a placa deste Veiculo
	public final String getPlaca() {
		return placa;
	}
	
	//Metodo abstrato getCombustivel - Retorna o combustivel deste Veiculo, qual podendo variar entre 1 e 2.
	public abstract Combustivel getCombustivel( int qual );

	//Metodo abstrato getDecaimento - Retorna o decaimento do rendimento do combustivel
	public abstract Double getDecaimento( Combustivel combustivel );

	//Metodo abstrato getRendimento - Retorna o rendimento deste veiculo com base no combustivel referencia
	public abstract Double getRendimento( Combustivel combustivel );
	
	//Sobrescrita do metodo toString
	@Override
	public String toString() {
		
		return "Veiculo: " + getNome() + ". Placa: " + getPlaca() + ". Do tipo " + getTipo() + ". Status: " + getEstado() + ". ID: " + getVeiculoID();
	}//Fim do metodo toString 
	
	//Sobrescrita do metodo compareTo utilizando o rendimento como base
	@Override
	public int compareTo( Veiculo veiculo2 ) {
		if( getVeiculoID() == veiculo2.getVeiculoID() )
			return 0;
		else if( getVeiculoID() > veiculo2.getVeiculoID() )
			return 1;
		
		return -1;
		
	}//Fim do metodo compareTo

//	//Metodo main para teste unitario -> nerfado
//	public static void main(String[] args) {
//		
//	}//Fim do metodo main
//	
}//Fim da declaraçao da classe Veiculo
