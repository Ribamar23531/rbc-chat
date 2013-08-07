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
                    //Leia a linha
                    String str = in.readLine();
                    if (str.startsWith("Descricao: ")) {
                        Caso caso = new Caso(new Entrada(str.substring(str.indexOf("Descricao:") + "Descricao: ".length(), str.indexOf("Solucao:"))));
                        //System.out.println("DESCRICAO:" + caso.getProblema().getTexto());
                        if (str.contains("Solucao: ")) {
                            caso.setSolucao(new Saida(str.substring(str.indexOf("Solucao:") + "Solucao: ".length(), str.indexOf("Avaliacao:"))));
                            //System.out.println("SOLUCAO:" + caso.getSolucao().getTexto());

                            if (str.contains("Avaliacao: ")) {
                                caso.setAvaliacao(Integer.parseInt(str.substring(str.indexOf("Avaliacao:") + "Avaliacao: ".length())));
                                if (caso.getAvaliacao() == 1) {
                                    //System.out.println("AVALICAO:Boa");
                                }
                                //System.out.println(caso);
                                banco.cadastrarCaso(caso);
                                System.out.print(".");
                            }
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Coloque o novo caso no mesmo diretório do programa e então re-execute, passando o arquivo como parâmetro");
                System.out.println(e);
            }
            System.out.println("\nTodos os casos cadastrados com sucesso!");

        } else if (opc.equals("2")) {
            Boolean condicao = true;

            System.out.println("Bem vindo ao RBC - Xaat, a partir de agora você pode conversar com o sistema. \nPara cadastrar um novo caso, digite Cadastrar. Caso deseje sair digite Sair.");
            while (condicao == true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String entrada = in.readLine();
                    if (entrada.equalsIgnoreCase("Sair")) {
                        condicao = false;
                    } 
                    else if(entrada.equalsIgnoreCase("Cadastrar")){
                    	System.out.println("Descreva o caso");
                    	BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                    	String str = buff.readLine();
						Caso caso = new Caso(new Entrada(str));
						System.out.println("Proponha uma solucao/resposta");
						String str_solucao = buff.readLine();
						
						caso.setSolucao(new Saida(str_solucao));
						System.out.println("Como voce avalia sua solucao? Boa ou Ruim");
						String confirm_avaliacao = buff.readLine();
						if (confirm_avaliacao.equalsIgnoreCase("Boa") || confirm_avaliacao.equalsIgnoreCase("B") ){
							caso.setAvaliacao(1);
							}
						else {
							caso.setAvaliacao(0);
							}
						System.out.println("Descricao: "+caso.getProblema().getTexto());
						System.out.println("Solucao: "+ caso.getSolucao().getTexto());
						System.out.println("Avaliacao: "+ confirm_avaliacao);
						System.out.println("Tudo certo? (Responda Sim ou S, para confirmar.)");
						String scan_confirm = buff.readLine();
						
						if (scan_confirm.equalsIgnoreCase("Sim") || scan_confirm.equalsIgnoreCase("S")) {
							banco.cadastrarCaso(caso);
							System.out.println("Caso adicionado ao banco!");}
						
						
	                    }
                    
                    else {
                        Caso caso = new Caso(new Entrada(entrada));
                        Caso caso_recuperado = banco.casoSemelhante(caso);
                        //System.out.println("Match: " + banco.casoSemelhanteMatch(caso));
                        if (caso_recuperado != null) {
                            if (caso_recuperado.getAvaliacao() == 1) {
                                caso.setSolucao(caso_recuperado.getSolucao());
                                System.out.println("Xaat disse: " + caso.getSolucao().getTexto());
                            } else if (caso_recuperado.getAvaliacao() == 0) {
                                caso.setSolucao(caso_recuperado.getSolucao());
                                System.out.println("Xaat disse para voce nao fazer: " + caso.getSolucao().getTexto());
                            }

                            System.out.println("Por favor, avalie minha resposta. Responda Sim ou Nao");
                            String esc = scan.next();
                            if (esc.equalsIgnoreCase("Sim") || esc.equalsIgnoreCase("S")) {
                                System.out.println("A solucao oferecia por mim, foi boa? Se sim, responda Sim ou s. Ou Nao ou n.");
                                String a = scan.next();
                                if (a.equalsIgnoreCase("Sim") || a.equalsIgnoreCase("S")) {
                                    caso.setAvaliacao(1);
                                    if (banco.casoSemelhanteMatch(caso) <= 0.6) {
                                        banco.cadastrarCaso(caso);
                                        System.out.println("Novo caso adicionado ao banco!");
                                    }

                                } else if(a.equalsIgnoreCase("Nao") || a.equalsIgnoreCase("N")) {
                                    caso.setAvaliacao(0);
                                    if (banco.casoSemelhanteMatch(caso) <= 0.6) {
                                        banco.cadastrarCaso(caso);
                                        System.out.println("Novo caso adicionado ao banco!");
                                    }
                                }
                                
                            }

                        } else {
                            System.out.println("Xaat diz: \"Ahhh lelek lek lek lek lek...Oi? Eu estava cantando...\"");
                        }

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Desculpe, ação nao suportada!");
                }
            }
        }

    }
}
