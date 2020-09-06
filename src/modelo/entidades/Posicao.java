package modelo.entidades;

public class Posicao {
	private int linha;
	private int coluna;
	
	public Posicao() {}

	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	public void setValues(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
}
