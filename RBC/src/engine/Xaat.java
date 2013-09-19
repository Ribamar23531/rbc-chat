package engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import database.BancoCaso;


public class Xaat {

	public Xaat(boolean condicao){
		Scanner scan = new Scanner(System.in);
		BancoCaso banco = new BancoCaso();
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
