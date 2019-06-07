/* Classe Perfil, utilizada para representar um perfil(usuario) na memoria do programa
 * Ultima modificação: 22:45
 * Pendencias: NENHUMA
 */
package entidades;

import java.util.Queue;
import java.util.Set;

import entidades.Veiculo.Status;
import entidades.Veiculo.Tipo;
import servicos.BancoDeDadosPerfil;

//inicio da declaração da classe
public class Perfil {
	
	private String nomeUsuario;
	private Double margemLucroPadrao;
	Set<Veiculo> frota;
	Queue<Frete> historicoFretes;
	
	//Construtor baseado em todas as informacoes 
	public Perfil( String nomeUsuario , double margemLucroPadrao , Set<Veiculo> frota , Queue<Frete> historicoFretes ) {
		
		setNomeUsuario( nomeUsuario );
		setMargemLucroPadrao( margemLucroPadrao );
		setFrota( frota );
		setHistoricoFretes( historicoFretes );
		
	}
	
	//Adiciona um novo veiculo na frota de acordo com as especificacoes passadas como argumento
	public void adicionarVeiculo( Tipo tipoVeiculo , String nomeVeiculo , String placaVeiculo ) {
		
		int menorIDAberto = 0;//Variavel que contera o id deste novo veiculo
		
		//Percorre a frota para descobrir qual o menor id livre para armazenar o novo veiculo
		for( Veiculo v : frota ) {
			if( v.getVeiculoID() != menorIDAberto ) {
				break;
			}
			else
				menorIDAberto++;
		}
		
		//Decidindo qual tipo de veiculo construir
		switch( tipoVeiculo ) {
		case CARRO:
			frota.add( new Carro( Status.LIVRE , menorIDAberto , nomeVeiculo , placaVeiculo ) );
			break;
		case MOTO:
			frota.add( new Moto( Status.LIVRE , menorIDAberto , nomeVeiculo , placaVeiculo ) );
			break;
		case CARRETA:
			frota.add( new Carreta( Status.LIVRE , menorIDAberto , nomeVeiculo , placaVeiculo ) );
			break;
		case VAN:
			frota.add( new Van( Status.LIVRE , menorIDAberto , nomeVeiculo , placaVeiculo ) );
			break;
		}
		
	}
	
	private void setFrota( Set<Veiculo> frota ) {
		this.frota = frota;
	}
	
	private void setHistoricoFretes( Queue<Frete> historicoFretes ) {
		this.historicoFretes = historicoFretes;
		
	}
	
	public void removerVeiculo( int veiculoID ) {
		frota.removeIf( x -> x.getVeiculoID() == veiculoID );
		
	}
	
	public void setNomeUsuario( String nomeUsuario ) {
		this.nomeUsuario = nomeUsuario;
		
	}
	
	public String getNomeUsuario( ) {
		return nomeUsuario;
		
	}
	
	public void setMargemLucroPadrao( double margemLucroPadrao ) {
		this.margemLucroPadrao = margemLucroPadrao;
		
	}
	
	public double getMargemLucroPadrao() {
		return margemLucroPadrao;
		
	}
	
	public void adicionarFrete( Frete frete ) {
		historicoFretes.add( frete );
		
	}
	
	public Set<Veiculo> getFrota(){
		return frota;
	}
	
	public Queue<Frete> getHistoricoFretes(){
		return historicoFretes;
	}
	
	@Override public String toString() {
		return getNomeUsuario();
	}
	
	//Main para testes
	public static void main( String[] args ) {
		
		Perfil user = new BancoDeDadosPerfil( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/" ).getPerfil();
		System.out.println( user + ":\n");
		
		for( Veiculo v : user.getFrota() ) {
			System.out.println(v);
		}
		System.out.println("\n");
		
		for( Frete f : user.getHistoricoFretes() ) {
			System.out.println(f);
		}
		System.out.println("\n");
		
		user.removerVeiculo(2);
		for( Veiculo v : user.getFrota() ) {
			System.out.println(v);
		}
		user.adicionarVeiculo( Tipo.MOTO , "Yamaha FZ6", "OJO-9321");
		System.out.println("\n");
		for( Veiculo v : user.getFrota() ) {
			System.out.println(v);
		}
	}

}
