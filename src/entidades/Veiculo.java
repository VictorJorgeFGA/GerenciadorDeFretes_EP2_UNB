package entidades;

public class Veiculo {
	
	public enum Status { RESERVADO , LIVRE };
	public enum Tipo { CARRETA , CARRO , VAN , MOTO };
	
	public static final int tamanhoDaFrota = 0;
	
	private Status estado;
	private Tipo tipo;
	private Integer veiculoID;
	
	public Veiculo( Status estado , Tipo tipo ){
		this.estado = estado;
		this.tipo 	= tipo;
		this.veiculoID = tamanhoDaFrota + 1;
		
		//Resolver em que momento definir o ID do veiculo
		//Resolver tambem utilizar ou nao a herança
		//veiculoID = GerencimentoDB.getUltimoID();
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
		System.out.println(v1);

	}

}
