/* Classe Frete que representará (Em dados formatados) um frete ocorrido
 * Ultima modificação: 04/06/2019 - 23:00
 * Pendendicas: Adicionar verificacoes nos setters e definir uma execao para ser lancada em casos de invalidade de dados
 */

package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Frete implements Comparable<Frete>{
	
	private Set<Veiculo> frotaUtilizada;	//Veiculos utilizados no frete
	private Double carga;					//Carga transportada em Kg
	private Double custo;					//Custo(gastos) do frete em R$
	private Double lucro;					//Lucro do frete em R$
	private Double tempoGasto;				//Tempo de viagem em Horas
	private Date data;						//Data da solicitação do frete
	
	public Frete( Set<Veiculo> frotaUtilizada, double carga, double custo, double lucro, double tempoGasto, Date data ) {
		setFrotaUtilizada( frotaUtilizada );
		setCarga( carga );
		setCusto( custo );
		setLucro( lucro );
		setTempoGasto( tempoGasto );
		setData( data );
	}
	
	public Frete( Veiculo veiculoUtilizado , double carga, double custo, double lucro, double tempoGasto, Date data ) {
		frotaUtilizada = new TreeSet<Veiculo>();
		frotaUtilizada.add( veiculoUtilizado );
		
		setCarga( carga );
		setCusto( custo );
		setLucro( lucro );
		setTempoGasto( tempoGasto );
		setData( data );
	}
	
	private void setFrotaUtilizada( Set<Veiculo> frotaUtilizada ) {
		this.frotaUtilizada = frotaUtilizada;
	}
	
	private void setCarga( double carga ) {
		this.carga = carga;
	}
	
	private void setCusto( double custo ) {
		this.custo = custo;
	}
	
	private void setLucro( double lucro ) {
		this.lucro = lucro;
	}
	
	private void setTempoGasto( double tempoGasto ) {
		this.tempoGasto = tempoGasto;
	}
	
	private void setData( Date data ) {
		this.data = data;
	}

	public Set<Veiculo> getFrotaUtilizada() {
		return frotaUtilizada;
	}

	public Double getCarga() {
		return carga;
	}

	public Double getCusto() {
		return custo;
	}

	public Double getLucro() {
		return lucro;
	}

	public Double getTempoGasto() {
		return tempoGasto;
	}

	public Date getData() {
		return data;
	}
	
	public void atualizarData() {
		setData( new Date() );
	}
	
	//Sobrescrita do metodo toString para melhor debug
	@Override
	public String toString() {
		String temp = new String("");
		temp = "Frete realizado em " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format( getData() ) +
				". Com um gasto total de R$ " + getCusto() + ", lucro total de R$ " + getLucro() + ".\nTempo gasto total: " +
				getTempoGasto() + "horas.\nCarga transportada: " + getCarga() + " Kg.\nFrota utilizada:";
		
		for( Veiculo v : getFrotaUtilizada() ) {
			temp += "\n" + v.toString();
		}
		return temp;
	}
	
	@Override
	//Maior = Melhor(Beneficios)
	public int compareTo( Frete frete2 ) {
		
		//Se o lucro deste frete for maior, entao maior
		if( getLucro() > frete2.getLucro() )
			return 1;
		
		//Se o lucro deste frete for menor, entao menor
		else if( getLucro() < frete2.getLucro() )
			return -1;
		
		//Se o lucro dos dois eh igual entao, comparar outros atributos
		else {
			//Se o tempo gasto for menor, entao maior
			if( getTempoGasto() < frete2.getTempoGasto() )
				return 1;
			
			//Se o tempo gasto for maior, entao menor
			else if( getTempoGasto() > frete2.getTempoGasto() )
				return -1;
			
			//Se o tempo gasto for igual
			else {
				
				//Se a quantidade de veiculos for menor, entao maior
				if( getFrotaUtilizada().size() < frete2.getFrotaUtilizada().size() )
					return 1;
				
				//Se a quantidade de veiculos for maior, entao menor
				else if( getFrotaUtilizada().size() > frete2.getFrotaUtilizada().size() )
					return -1;
				
				//Se a quantidade de veiculos for igual, entao igual
				else {
					return 0;
				}
			}
		}
	}

	public static void main(String[] args) throws ParseException {
		
		
	}

}
