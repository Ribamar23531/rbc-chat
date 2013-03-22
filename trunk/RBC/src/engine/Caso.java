package engine;

public class Caso {
	
	private Entrada problema;
	private Saida solucao;
	private int avaliacao;
	
	public Caso(Entrada a, Saida b){
		this.problema = a;
		this.solucao = b;
	}
	
	public Caso(Entrada a){
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
	
	public String[] getEntrada_asList(){
		String entrada = this.problema.getTexto();
		return entrada.split(" ");
	}
	
	public String[] getSaida_asList(){
		String saida = this.solucao.getTexto();
		return saida.split(" ");
	}
	
}
