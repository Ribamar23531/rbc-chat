package runtime;
import java.sql.Connection;  
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import database.Conecta;

import engine.Caso;
import engine.Entrada;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);   
		System.out.println("O que desejas fazer? \n 1 - Reconhecer novo caso");
		String opc = scan.next();
		System.out.println (opc);
		Caso caso = new Caso();
		
		try {
			Connection con = Conecta.getConexao();
			Statement statment = con.createStatement();
			statment.execute("INSERT INTO tabe1 VALUES ");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (opc == "1"){
			try {
				BufferedReader in = new BufferedReader(new FileReader(args[0]));
				while (in.ready()) {
                    //Leia a linha
                    String str = in.readLine();
                    
                    if (str.startsWith("Descrição:")){
                    	caso.setProblema(new Entrada());
                    }
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
				System.out.println("Coloque o novo caso no mesmo diretório do programa e então re-execute, passando o arquivo como parâmetro");
		}
	}

}
