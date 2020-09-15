package aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	
	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		
		while (true) {
			IU.imprimirTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Inicial: ");
			PosicaoXadrez pInicial = IU.lerPosicaoXadrez(leia);
			
			System.out.println();
			System.out.print("Final: ");
			PosicaoXadrez pFinal = IU.lerPosicaoXadrez(leia);
			
			PecaDeXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(pInicial, pFinal);
		}
		
	}

}
