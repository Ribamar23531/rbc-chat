package runtime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import engine.Caso;
import engine.Entrada;
import engine.Saida;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);   
		System.out.println("O que desejas fazer? \n 1 - Reconhecer novo caso \n 2 - Utilizar o programa");
		String opc = scan.next();
		System.out.println("Sua opção foi: "+opc);
		
		//Caso caso = new Caso();
		
//		try {
//			Connection con = Conecta.getConexao();
//			Statement statment = con.createStatement();
//			statment.execute("INSERT INTO tabe1 VALUES ");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		if (opc.equals("1")){
			try {
				BufferedReader in = new BufferedReader(new FileReader(args[0]));
				while (in.ready()) {
                    //Leia a linha
                    String str = in.readLine();
                    
                    if (str.startsWith("Descrição: ")){
                    	Caso caso = new Caso(new Entrada(str.substring(str.indexOf("Descrição: "))));
                    	//String[] entrada_list = caso.getEntrada_asList();
                    
                    	if (str.startsWith("Solução: ")){
                    		caso.setSolucao(new Saida(str.substring(str.indexOf("Solução: "))));
                    		//String[] saida_list = caso.getSaida_asList();
                    		
                    		if (str.startsWith("Avaliação: ")){
                    			caso.setAvaliacao(Integer.parseInt(str.substring(str.indexOf("Avaliação: ")))); // Verificar o valor da avaliação
                    			//Salvar caso no banco;
                    		}
                    	}
                    		
                	}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
				System.out.println("Coloque o novo caso no mesmo diretório do programa e então re-execute, passando o arquivo como parâmetro");
		}
		
		if (opc.equals("2")){
			Boolean condicao = true;
			System.out.println("Bem vindo ao RBC - Xaat, a partir de agora você pode conversar com o sistema. \nCaso deseje sair digite Sair.");
			while (condicao==true){
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
				try {
					String entrada = in.readLine();
					System.out.println("Você disse: " + entrada);
					if (entrada.equalsIgnoreCase("Sair")){
						condicao = false;
					}
					else{
						//caso = banco.casoSemelhante()
						//if (caso.getAvalicao() == True){caso.getSolucao();}
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Desculpe, ação não suportada!");} 
			}
		}
	}
}


