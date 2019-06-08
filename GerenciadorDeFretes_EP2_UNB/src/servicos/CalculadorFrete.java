/* Classe CalculadorFrete, que calculara as melhores maneiras de se realizar um determinado frete
 * Ultima modificação: 08/06/2019 - 13:23
 * Pendencias: Definir os preços da gasolina para calcular o custo total da viagem
 */
package servicos;

import java.util.Date;

import entidades.Frete;
import entidades.Perfil;
import entidades.Veiculo;
import entidades.Veiculo.Status;

public class CalculadorFrete {

	private Perfil perfilReferencia;
	private Double distancia; // Em Km
	private Double carga; // Em Kg
	private Double tempoMaximo; // Em hrs
	private Double margemLucro;

	private Frete maisRapido;
	private Frete maisBarato;
	private Frete melhorCustoBeneficio;

	public CalculadorFrete(Perfil perfilReferencia, double distancia, double carga, double tempoMaximo,
			double margemLucro) {
		setPerfilReferencia(perfilReferencia);
		setDistancia(distancia);
		setCarga(carga);
		setTempoMaximo(tempoMaximo);
		setMargemLucro(margemLucro);

		calcularFretes();
	}

	public CalculadorFrete(Perfil perfilReferencia, double distancia, double carga, double tempoMaximo) {
		setPerfilReferencia(perfilReferencia);
		setDistancia(distancia);
		setCarga(carga);
		setTempoMaximo(tempoMaximo);
		setMargemLucro(perfilReferencia.getMargemLucroPadrao());

		calcularFretes();
	}

	private void setPerfilReferencia(Perfil perfilReferencia) {
		this.perfilReferencia = perfilReferencia;

	}

	private void setDistancia(double distancia) {
		this.distancia = distancia;

	}

	private void setCarga(double carga) {
		this.carga = carga;

	}

	private void setTempoMaximo(double tempoMaximo) {
		this.tempoMaximo = tempoMaximo;

	}

	private void setMargemLucro(double margemLucro) {
		this.margemLucro = margemLucro;

	}

	// Verifica se o veiculo fornecido possui condicoes (minimas que sejam) para
	// operar neste frete
	private boolean veiculoPodeOperar(Veiculo veiculo) {
		if (veiculo.getEstado() == Status.RESERVADO)
			return false;

		if (distancia / veiculo.getVelMedia() > tempoMaximo || veiculo.getCargaMaxima() < carga)
			return false;

		else
			return true;
	}

	// Descobre qual o melhor custo de viagem para este Veiculo, retornando o melhor
	// custo
	private Double calcularMelhorCustoViagem(Veiculo veiculo) {
		Double RendT1 = veiculo.getRendimento(veiculo.getCombustivel(1))
				- carga * veiculo.getDecaimento(veiculo.getCombustivel(1));
		Double RendT2 = veiculo.getRendimento(veiculo.getCombustivel(2))
				- carga * veiculo.getDecaimento(veiculo.getCombustivel(2));

		Double gastoL1 = distancia / RendT1;
		Double gastoL2 = distancia / RendT2;

		Double gastoT1 = gastoL1 * veiculo.getCombustivel(1).getPrecoPorLitro();
		Double gastoT2 = gastoL2 * veiculo.getCombustivel(2).getPrecoPorLitro();

		if (gastoT1 <= gastoT2) {
			return gastoT1;
		} else {
			return gastoT2;
		}

	}// Fim do método calcularMelhorCustoViagem()

	//Metodo interno da classe calcularFretes
	private void calcularFretes() {

		for (Veiculo veiculo : perfilReferencia.getFrota()) {

			//Calculo para a decisao que produzira o frete mais rapido
			if (maisRapido == null && veiculoPodeOperar(veiculo)) {
				double melhorCusto = calcularMelhorCustoViagem(veiculo);
				maisRapido = new Frete(veiculo, carga, melhorCusto, margemLucro * melhorCusto, distancia / veiculo.getVelMedia(), new Date());
			}

			else if( maisRapido != null && veiculoPodeOperar( veiculo ) ){
				if( maisRapido.getTempoGasto() > distancia / veiculo.getVelMedia() ){
					double melhorCusto = calcularMelhorCustoViagem(veiculo);
					maisRapido = new Frete(veiculo, carga, melhorCusto, margemLucro * melhorCusto, distancia / veiculo.getVelMedia(), new Date());
				} 
			}
			//Fim do calculo para decisao do mais rapidp


			//Calculo para a decisao que produzira o frete mais barato
			if( maisBarato == null && veiculoPodeOperar(veiculo) ){
				double melhorCusto = calcularMelhorCustoViagem(veiculo);
				maisBarato = new Frete( veiculo , carga , melhorCusto , margemLucro * melhorCusto , distancia / veiculo.getVelMedia() , new Date() );
			}
			else if( maisRapido != null &&  veiculoPodeOperar(veiculo) ){
				double melhorCusto = calcularMelhorCustoViagem(veiculo);
				if( maisBarato.getCusto() > melhorCusto ){
					maisBarato = new Frete( veiculo , carga , melhorCusto , margemLucro * melhorCusto , distancia / veiculo.getVelMedia() , new Date() );
				}
			}
			//Fim do calculo para decisao do mais barato


			//Calculo para a deicao que produzira o frete com melhor custo beneficio
			//Custo beneficio medido em custo / tempo
			//Ou seja, quanto custará cada hora de operacao
			if( melhorCustoBeneficio == null && veiculoPodeOperar(veiculo) ){
				double melhorCusto = calcularMelhorCustoViagem(veiculo);
				melhorCustoBeneficio = new Frete( veiculo , carga , melhorCusto , margemLucro * melhorCusto , distancia / veiculo.getVelMedia() , new Date() );
			}
			else if( melhorCustoBeneficio != null && veiculoPodeOperar(veiculo) ){
				double melhorCusto = calcularMelhorCustoViagem(veiculo);
				double tempoGasto = distancia / veiculo.getVelMedia();

				if( melhorCustoBeneficio.getCusto() / melhorCustoBeneficio.getTempoGasto() > melhorCusto / tempoGasto ){
					melhorCustoBeneficio = new Frete( veiculo , carga , melhorCusto , margemLucro * melhorCusto , distancia / veiculo.getVelMedia() , new Date() );
				}
			}
			//Fim do calculo para decisao do melhor custo beneficio.

		}//Fim do metodo calcularFretes
	}

	public Frete getMaisRapido( ){
		return maisRapido;
	}

	public Frete getMaisBarato(){
		return maisBarato;
	}

	public Frete getMelhorCustoBeneficio(){
		return melhorCustoBeneficio;
	}

	public void confirmarMaisRapido(){
		perfilReferencia.adicionarFrete( maisRapido );

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CalculadorFrete sugestaoFrete = new CalculadorFrete( new BancoDeDadosPerfil("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/").getPerfil() , 300 , 500 , 30 );
		System.out.println( "Mais rapido\n" + sugestaoFrete.getMaisRapido() );
		System.out.println("\nMais barato\n" + sugestaoFrete.getMaisBarato());
		System.out.println("\nMelhor custo beneficio\n" + sugestaoFrete.getMelhorCustoBeneficio());
	}

}
