/* Classe Perfil, utilizada para representar um perfil(usuario) na memoria do programa
 * Ultima modificação: 22:45
 * Pendencias: NENHUMA
 */
package entidades;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import entidades.Veiculo.Status;
import entidades.Veiculo.Tipo;
import servicos.BancoDeDadosPerfil;

//inicio da declaração da classe
public class Perfil {
	
	boolean foiModificado;
	
	private String caminhoDataBase;
	private String nomeUsuario;
	private Double margemLucroPadrao;
	Set<Veiculo> frota;
	Queue<Frete> historicoFretes;
	
	//Construtor baseado em todas as informacoes 
	public Perfil( String nomeUsuario , double margemLucroPadrao , Set<Veiculo> frota , Queue<Frete> historicoFretes , String caminhoDataBase ) {
		
		foiModificado = false;
		this.nomeUsuario = nomeUsuario;
		this.margemLucroPadrao = margemLucroPadrao;
		this.frota = frota;
		this.historicoFretes = historicoFretes;
		this.caminhoDataBase = caminhoDataBase;
	}
	
	//Adiciona um novo veiculo na frota de acordo com as especificacoes passadas como argumento
	public void adicionarVeiculo( Tipo tipoVeiculo , String nomeVeiculo , String placaVeiculo ) {
		
		foiModificado = true;
		
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
	
	public void removerVeiculo( int veiculoID ) {
		foiModificado = true;
		frota.removeIf( x -> x.getVeiculoID() == veiculoID );
		
	}
	
	public void reservarVeiculo( int veiculoID ) {
		foiModificado = true;
		for( Veiculo v : getFrota() ) {
			if( v.getVeiculoID() == veiculoID )
				v.setEstado( Status.RESERVADO );
		}
	}
	
	public void liberarVeiculo( int veiculoID ) {
		foiModificado = true;
		for( Veiculo v : getFrota() ) {
			if( v.getVeiculoID() == veiculoID )
				v.setEstado( Status.LIVRE );
		}
	}
	
	public void setNomeUsuario( String nomeUsuario ) {
		foiModificado = true;
		this.nomeUsuario = nomeUsuario;
		
	}
	
	public String getNomeUsuario( ) {
		return nomeUsuario;
		
	}
	
	public void setMargemLucroPadrao( double margemLucroPadrao ) {
		foiModificado = true;
		this.margemLucroPadrao = margemLucroPadrao;
		
	}
	
	public double getMargemLucroPadrao() {
		return margemLucroPadrao;
		
	}
	
	public String getCaminhoDataBase() {
		return caminhoDataBase;
	}
	
	//Adiciona este Frete ao perfil descontando todos os veiculos utilizados
	public void adicionarFrete( Frete frete ) {
		
		foiModificado = true;
		historicoFretes.add( frete );
		Set<Veiculo> frotaUtilizada = frete.getFrotaUtilizada();
		for( Veiculo v : frotaUtilizada ) {
			reservarVeiculo( v.getVeiculoID() );
		}
		
	}
	
	public void limparFrota() {
		
		foiModificado = true;
		this.frota = new TreeSet<Veiculo>();
	}
	
	public void limparHistoricoFretes() {
		
		foiModificado = true;
		this.historicoFretes = new LinkedList<Frete>();
	}
	
	public Set<Veiculo> getFrota(){
		return frota;
	}
	
	public Queue<Frete> getHistoricoFretes(){
		return historicoFretes;
	}
	
	public boolean foiModificado() {
		return foiModificado;
	}
	
	@Override public String toString() {
		return getNomeUsuario();
	}
	
	//reserva os i-esimos veiculos da frota
	public void reservarVeiculos( int[] indices ) {
		foiModificado = true;
		
		int i = 0;
		int cont = 0;
		for( Veiculo v : getFrota() ) {
			if( indices[i] == cont ) {
				v.setEstado( Status.RESERVADO );
				i++;
			}
			cont++;
		}
	}
	
	public void liberarVeiculos( int[] indices ) {
		foiModificado = true;
		
		int i = 0;
		int cont = 0;
		for( Veiculo v : getFrota() ) {
			if( indices[i] == cont ) {
				v.setEstado( Status.LIVRE );
				i++;
			}
			cont++;
		}
	}
	
	//Main para testes
//	public static void main( String[] args ) {
//		
//		Perfil user = new BancoDeDadosPerfil( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/" ).getPerfil();
//		System.out.println( user + ":\n");
//		
//		for( Veiculo v : user.getFrota() ) {
//			System.out.println(v);
//		}
//		System.out.println("\n");
//		
//		for( Frete f : user.getHistoricoFretes() ) {
//			System.out.println(f);
//		}
//		System.out.println("\n");
//		
//		user.removerVeiculo(2);
//		for( Veiculo v : user.getFrota() ) {
//			System.out.println(v);
//		}
//		user.adicionarVeiculo( Tipo.MOTO , "Yamaha FZ6", "OJO-9321");
//		System.out.println("\n");
//		for( Veiculo v : user.getFrota() ) {
//			System.out.println(v);
//		}
//	}

}
