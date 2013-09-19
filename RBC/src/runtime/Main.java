package runtime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import database.BancoCaso;

import engine.Caso;
import engine.Entrada;
import engine.Saida;
import engine.Xaat;

public class Main {

    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
        System.out.println("O que desejas fazer? \n 1 - Reconhecer novo caso \n 2 - Utilizar o programa");
        String opc = scan.next();
        System.out.println("Sua opcao foi: " + opc);

        BancoCaso banco = new BancoCaso();

        if (opc.equals("1")) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(args[0]));
                System.out.print("Cadastrando casos");
                while (in.ready()) {
                    Caso caso = new Caso();
                    caso.cadastrarCaso(banco, in);
                }
            } catch (Exception e) {
                System.out.println("Coloque o novo caso no mesmo diretório do programa e então re-execute, passando o arquivo como parâmetro");
                System.out.println(e);
            }
            System.out.println("\nTodos os casos cadastrados com sucesso!");

        } else if (opc.equals("2")) {
            Boolean condicao = true;
            Xaat bate_papo = new Xaat(condicao);
        }

    }
}
