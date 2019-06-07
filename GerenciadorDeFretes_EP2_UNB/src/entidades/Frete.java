/* Classe Frete que representará (Em dados formatados) um frete ocorrido
 * Ultima modificação: 04/06/2019 - 23:00
 * Pendendicas: Adicionar verificacoes nos setters e definir uma execao para ser lancada em casos de invalidade de dados
 */

package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Frete {
	
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

	public static void main(String[] args) throws ParseException {
		
		
	}

}
