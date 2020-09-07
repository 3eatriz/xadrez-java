package aplicacao;

import xadrez.PartidaDeXadrez;

public class Programa {
	
	public static void main(String[] args) {
		
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		IU.imprimirTabuleiro(partidaXadrez.getPecas());
		
	}

}
