package entidades;

public class Veiculo {
	
	public enum Status { RESERVADO , LIVRE };
	public enum Tipo { CARRETA , CARRO , VAN , MOTO };
	
	public static int tamanhoDaFrota = 0;
	
	private Status estado;
	private Tipo tipo;
	private Integer veiculoID;
	
	public Veiculo( Status estado , Tipo tipo ){
		this.estado = estado;
		this.tipo 	= tipo;
		this.veiculoID = tamanhoDaFrota + 1;
		
		//Resolver em que momento definir o ID do veiculo
		//veiculoID = GerencimentoDB.getUltimoID();
		tamanhoDaFrota++;
	}
	
	public Veiculo( Status estado , Tipo tipo , int veiculoID ) {
		this.estado = estado;
		this.tipo = tipo;
		this.veiculoID = veiculoID;
	}
	
	public Status getEstado() {
		return estado;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public Integer getVeiculoID() {
		return veiculoID;
	}
	
	public void setEstado( Status estado ) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		String sEstado = "";
		String sTipo = "";
		
		switch( estado ) {
		case RESERVADO:
			sEstado = "Reservado";
			break;
		case LIVRE:
			sEstado = "Livre";
			break;
		default:
			break;
		}
		
		switch( tipo ) {
		case CARRO:
			sTipo = "Carro";
			break;
		case CARRETA:
			sTipo = "Carreta";
			break;
		case VAN:
			sTipo = "Van";
			break;
		case MOTO:
			sTipo = "Moto";
			break;
		default:
			break;
		}
		
		return "Veiculo " + veiculoID + ", do tipo " + sTipo + ", de estado " + sEstado;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Veiculo v1 = new Veiculo( Veiculo.Status.LIVRE , Veiculo.Tipo.CARRETA );
		Veiculo v2 = new Veiculo( Veiculo.Status.RESERVADO , Veiculo.Tipo.CARRO );
		
		System.out.println(v1);
		System.out.println(v2);
		System.out.println("----------------------------------------");
		
		v1.setEstado( Status.RESERVADO );
		v2.setEstado( Status.LIVRE );
		System.out.println(v1);
		System.out.println(v2);

	}

}
