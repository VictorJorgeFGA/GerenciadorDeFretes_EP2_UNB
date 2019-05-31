/* Classe abstrata Veiculo que será utilizada para representar todo tipo de veículo do negócio na memória em tempo de execução
 * Ultima modificação: 31/05/2018 - 9:30
 * Pendencias: Nenhuma.
 */
package entidades;

//Inicio da declaração da classe abstrata Veiculo
public abstract class Veiculo {
	
	public enum Status { RESERVADO , LIVRE };					//Enum que descreve o estado do veiculo no negócio
	public enum Tipo { CARRETA , CARRO , VAN , MOTO };			//Enum que descreve o tipo do veiculo no negócio
	public enum Combustivel { ALCOOL , DIESEL , GASOLINA };		//Enum que descreve o tipo de combustivel utilizado pelo Veiculo
	
	public static int tamanhoDaFrota = 0;
	
	private Status estado;
	private Tipo tipo;
	
	private Integer veiculoID;
	private Double rendimento,
				   cargaMaxima,
				   velMedia,
				   decaimento;
	
	//Inicio do construtor da classe Veiculo
	public Veiculo( Status estado , Tipo tipo , int veiculoID, double rendimento , double cargaMaxima , double velMedia , double decaimento ) {
		
		this.estado = estado;
		this.tipo = tipo;
		this.veiculoID = veiculoID;
		this.rendimento = rendimento;
		this.cargaMaxima = cargaMaxima;
		this.velMedia = velMedia;
		this.decaimento = decaimento;
		
	}//Fim do construtor da classe veiculo
	
	
	//Retorna o estado deste veiculo
	public Status getEstado() {
		return estado;
	}
	
	//Retorna o tipo deste veiculo
	public Tipo getTipo() {
		return tipo;
	}
	
	//Retorna o ID deste veiculo
	public Integer getVeiculoID() {
		return veiculoID;
	}
	
	//Setta o estado deste veiculo
	public void setEstado( Status estado ) {
		this.estado = estado;
	}
	
	//Retorna o rendimento KM/L deste veiculo
	public Double getRendimento() {
		return rendimento;
	}
	
	//Retorna a carga maxima suportada por este veiculo
	public Double getCargaMaxima() {
		return cargaMaxima;
	}
	
	//Retorna a velocidade media deste Veiculo
	public Double getVelMedia() {
		return velMedia;
	}
	
	//Retorna o decaimento da eficiencia de consumo deste veiculo
	public Double getDecaimento() {
		return decaimento;
	}
	
	//Metodo abstrato getCombustivel - Retorna o combustivel deste Veiculo, qual podendo variar entre 1 e 2.
	public abstract Combustivel getCombustivel( int qual );
	
	//Sobrescrita do metodo toString
	@Override
	public String toString() {
		
		return "Veiculo " + veiculoID + ", do tipo " + tipo + ", de estado " + estado;
	}//Fim do metodo toString 

	//Metodo main para teste unitario -> nerfado
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Veiculo v1 = new Veiculo( Veiculo.Status.LIVRE , Veiculo.Tipo.CARRETA );
		Veiculo v2 = new Veiculo( Veiculo.Status.RESERVADO , Veiculo.Tipo.CARRO );
		
		System.out.println(v1);
		System.out.println(v2);
		System.out.println("----------------------------------------");
		
		v1.setEstado( Status.RESERVADO );
		v2.setEstado( Status.LIVRE );
		System.out.println(v1);
		System.out.println(v2);
		*/
		
	}//Fim do metodo main
	
}//Fim da declaraçao da classe Veiculo
