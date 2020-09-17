package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {
	
	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		PartidaDeXadrez partidaXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();
 		
		while (true) {
			try {
				IU.limparTela();
				IU.imprimirPartida(partidaXadrez,capturada);
				System.out.println();
				System.out.println();
				System.out.println("Mova a sua peca.");
				System.out.print("Escolha a peca: ");
				PosicaoXadrez pInicial = IU.lerPosicaoXadrez(leia);
				
				boolean[][] possiveisMovimentos = partidaXadrez.possiveisMovimentos(pInicial);
				IU.limparTela();
				IU.imprimirTabuleiro(partidaXadrez.getPecas(), possiveisMovimentos);
				System.out.println();
				System.out.print("Mover para: ");
				PosicaoXadrez pFinal = IU.lerPosicaoXadrez(leia);
				
				PecaDeXadrez pecaCapturada = partidaXadrez.executarMovimentoXadrez(pInicial, pFinal);
				
				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
				
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
