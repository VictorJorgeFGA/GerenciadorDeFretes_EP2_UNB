/* Classe BancoDeDadosPerfil, que extrai da dataBase os dados e os formata em dados reconhecidos pelo programa
 * Ultima modificação: 06/06/2019 - 22:45
 * Pendencias: NENHUMA
 */
package servicos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.Set;

import entidades.Frete;
import entidades.Perfil;
import entidades.Veiculo;

//Declaracao da classe
public class BancoDeDadosPerfil {
	
	String caminhoDataBase;
			
	Set<Veiculo> frota;					//Frota a ser extraida do arquivo
	Queue<Frete> historicoFretes;		//Historico de fretes a ser extraido da database
	private Double margemLucroPadrao;	//Margem de lucro registrada pelo usuario
	private String nomeUsuario;			//Nome do perfil do usuario
	
	//Construtor que ja extrai todos os dados
	public BancoDeDadosPerfil( String caminhoDataBase ) {
		setCaminhoDataBase(caminhoDataBase);
		extrairDadosPerfil();
		extrairDadosFrota();
		extrairDadosFrete();
		
	}
	
	//Extrai as informacoes pessoais do perfil
	private void extrairDadosPerfil() {
		try( BufferedReader dadosPerfil = new BufferedReader( new FileReader( getCaminhoDataBase() + ".perfil" ) ) ){
			String info = dadosPerfil.readLine();
			
			if( info == null )
				return;
			
			setNomeUsuario( info );
			
			info = dadosPerfil.readLine();
			
			if( info == null )
				return;
			
			setMargemLucroPadrao( Double.parseDouble( info ) );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Extrai a frota deste perfil
	private void extrairDadosFrota() {
		setFrota( new ExtratorDadosFrota( caminhoDataBase + ".frota" ).getFrota() );
	}
	
	//Extrai todo o historico de fretes
	private void extrairDadosFrete() {
		setHistoricoFretes( new ExtratorDadosFrete( getCaminhoDataBase() + ".fretes" ).getExtratoFretes() );
	
	}
	
	private void setFrota( Set<Veiculo> frota ) {
		this.frota = frota;
	}
	
	private void setHistoricoFretes( Queue<Frete> historicoFretes ) {
		this.historicoFretes = historicoFretes;
	}
	
	private void setMargemLucroPadrao( double margemLucroPadrao ) {
		this.margemLucroPadrao = margemLucroPadrao;
	}
	
	private void setNomeUsuario( String nomeUsuario ) {
		this.nomeUsuario = nomeUsuario;
	}
	
	private void setCaminhoDataBase( String caminhoDataBase ) {
		this.caminhoDataBase = caminhoDataBase;
	}
	
	public String getCaminhoDataBase() {
		return caminhoDataBase;
	}
	
	public Set<Veiculo> getFrota(){
		return frota;
	}
	
	public Queue<Frete> getHistoricoFretes(){
		return historicoFretes;
	}
	
	public Double getMargemLucroPadrao() {
		return margemLucroPadrao;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	//Cria um novo perfil com base nas informacoes retiradas da database
	public Perfil getPerfil() {
		return new Perfil( nomeUsuario , margemLucroPadrao  , frota , historicoFretes );
	}
	
	public static void main(String []args) {
		BancoDeDadosPerfil db = new BancoDeDadosPerfil( "/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/" );
		Set<Veiculo> myfrota = db.getFrota();
		
		System.out.println(db.getNomeUsuario());
		System.out.println(db.getMargemLucroPadrao());
		
		for( Veiculo v : myfrota ) {
			System.out.println(v);
		}
		
		Queue<Frete> myfretes = db.getHistoricoFretes();
		for( Frete f :  myfretes )
			System.out.println(f);
		
	}

}//Fim da declaracao da classe
