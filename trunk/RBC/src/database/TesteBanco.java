package database;

import engine.Caso;
import engine.Entrada;
import engine.Saida;

public class TesteBanco {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		Entrada entrada = new Entrada("Olá, tudo bem?");
		Saida saida = new Saida("Oi, tudo sim. E vc?");
		
		Caso caso = new Caso(entrada, saida);
		
		BancoDeDados bancoDeDados = new BancoDeDados();
		bancoDeDados.conectarLocal();
		bancoDeDados.criarBanco();
                bancoDeDados.conectarBanco();
                bancoDeDados.criarTabelas();
	}

}
