package aplicacao;

import java.io.File;

import servicos.BancoDeDadosPerfil;
import visual.GUI;

public class GerenciadorDeFretes {
	
	public static void main( String[] args ) {
		
		GUI aplicacao = new GUI( new BancoDeDadosPerfil( new File("").getAbsolutePath() + "/db/" ).getPerfil() );
	}
}
