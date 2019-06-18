package jogo;

//import java.util.Scanner;

public class mainTeste {

	public static void main(String[] args) {
		//Scanner rd = new Scanner(System.in);
		Jogo game1 = new Jogo();

		game1.imprimeTabuleiro();

		game1.executaJogada(7, 1, 6, 0, 'O');

		game1.executaJogada(0, 3, 1, 2, 'L');


	}

}
