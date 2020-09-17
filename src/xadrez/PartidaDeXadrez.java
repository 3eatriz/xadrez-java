package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual(){
		return jogadorAtual;
	}
	
	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoInicial){
		Posicao posicao = posicaoInicial.toPosicao();
		validarPosicaoInicial(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaDeXadrez executarMovimentoXadrez(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoFinal) {
		Posicao pInicial = posicaoInicial.toPosicao();
		Posicao pFinal = posicaoFinal.toPosicao();
		validarPosicaoInicial(pInicial);
		validarPosicaoFinal(pInicial, pFinal);
		Peca pecaCapturada = fazerMovimento(pInicial, pFinal);
		proximoTurno();
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private void validarPosicaoInicial(Posicao posicao) {
		if(!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezException("Nao ha peca na posicao inicial");
		}
		
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao eh sua");
		}
		
		if(!tabuleiro.peca(posicao).existeUmMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validarPosicaoFinal(Posicao pInicial, Posicao pFinal) {
		if(!tabuleiro.peca(pInicial).podeMover(pFinal)) {
			throw new XadrezException("A peca escolhida nao pode se mover para posicao de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		this.jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Peca fazerMovimento(Posicao pInicial, Posicao pFinal) {
		Peca p = tabuleiro.removerPeca(pInicial);
		Peca pecaCapturada = tabuleiro.removerPeca(pFinal);
		tabuleiro.colocarPeca(p, pFinal);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	public void configInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
