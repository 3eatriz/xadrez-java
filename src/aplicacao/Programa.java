package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {
	
	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				IU.limparTela();
				IU.imprimirTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.println();
				System.out.println("Mova a sua peça.");
				System.out.print("Escolha a peça: ");
				PosicaoXadrez pInicial = IU.lerPosicaoXadrez(leia);
				
				System.out.println();
				System.out.print("Mover para: ");
				PosicaoXadrez pFinal = IU.lerPosicaoXadrez(leia);
				
				PecaDeXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(pInicial, pFinal);
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				leia.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				leia.nextLine();
			}
			
		}
		
	}

}
