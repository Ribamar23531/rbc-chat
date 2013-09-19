package engine;

import java.io.BufferedReader;
import java.io.IOException;

import database.BancoCaso;

public class Caso {

    private Entrada problema;
    private Saida solucao;
    private int avaliacao;

    public Caso(){}
    public Caso(Entrada a, Saida b) {
        this.problema = a;
        this.solucao = b;
    }

    public Caso(Entrada a) {
        this.problema = a;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Entrada getProblema() {
        return problema;
    }

    public void setProblema(Entrada problema) {
        this.problema = problema;
    }

    public Saida getSolucao() {
        return solucao;
    }

    public void setSolucao(Saida solucao) {
        this.solucao = solucao;
    }

    public String[] getEntrada_asList() {
        String entrada = this.problema.getTexto();
        return entrada.split(" ");
    }

    public String[] getSaida_asList() {
        String saida = this.solucao.getTexto();
        return saida.split(" ");
    }
    
    public void cadastrarCaso(BancoCaso banco, BufferedReader in) throws IOException{
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
}
