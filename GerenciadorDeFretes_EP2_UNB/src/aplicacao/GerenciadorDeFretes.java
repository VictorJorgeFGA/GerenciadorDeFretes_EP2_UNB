package aplicacao;

import servicos.BancoDeDadosPerfil;
import visual.GUI;

public class GerenciadorDeFretes {
	
	public static void main( String[] args ) {
		GUI aplicacao = new GUI( new BancoDeDadosPerfil("/Users/victor/Repositorios/oo/ep2/GerenciadorDeFretes_EP2_UNB/db/").getPerfil() );
	}
}
