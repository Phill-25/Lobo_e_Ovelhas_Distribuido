package jogo;


public class Jogo implements Interface_Jogo {

	private int[][] tabuleiro = new int[8][8];


	@Override
	public void executaJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador) {

		try {
			if (iDestino >= 0 && iDestino <= tabuleiro.length && jDestino >= 0 && jDestino <= tabuleiro.length ) {
				// essa verificação pode ser mais potente, gerando um conjunto de jogadas possíveis similar ao de linguagens
				// e assim verificar se a jogada destino está na lista de jogadas possíveis....																							

				tabuleiro[iOrigem][jOrigem] = -1;

				if(jogador == 'L') {
					tabuleiro[iDestino][jDestino] = 8;

				}else if (jogador == 'O') {

					tabuleiro[iDestino][jDestino] = 0;

				}
			}else {
				System.out.println("Jogada Inválida");
			}


			imprimeTabuleiro();

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Jogada Inválida");
		}
	}
	
	@Override
	public void sincJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador) {
		
		executaJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		
	}

	@Override
	public boolean fimdeJogoLobo() {
		
		for (int i = 0; i < tabuleiro.length; i+=2) {
			if(tabuleiro[7][i] == 8) {
				return true;
			}

		}
		return false;
	}
	
	
	public boolean fimdeJogoOvelha() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if(tabuleiro[i][j]==8) {
					
					if(j+1 >= tabuleiro.length) {
						if(tabuleiro[i-1][j-1] == 0 && tabuleiro[i+1][j-1] == 0)
							return true;
						
						break;
						
					}if(j-1 < 0) {
						if(tabuleiro[i-1][j+1] == 0 && tabuleiro[i+1][j+1] == 0)
							return true;
						
						break;
					}
					
					if(i-1 < 0) {
						if(tabuleiro[i+1][j-1] == 0 && tabuleiro[i+1][j+1] == 0)
							return true;
						
						break;
					}
					
					if(i+1 >= tabuleiro.length) {
						if(tabuleiro[i-1][j-1] == 0 && tabuleiro[i+1][j-1] == 0 && tabuleiro[i-1][j+1] == 0 && tabuleiro[i+1][j+1] == 0)
							return true;
						
						break;
					}
					
					
				}
			}
			
		}
		
		return true;
	}

	@Override
	public void imprimeTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			System.out.print(i+1 + " |");
			for (int j = 0; j < tabuleiro.length; j++) {

				if(tabuleiro[i][j] == -1) {
					System.out.print(" |");
				}else if (tabuleiro[i][j] == 8) {
					System.out.print("L|");

				}else {
					System.out.print("O|");

				}

			}

			System.out.println("");

		}
		System.out.print("   ");
		for (int i = 0; i < tabuleiro.length; i++) {
			System.out.print(i+1 +" ");
		}
		
		System.out.println("\n");	
	}
	
	@Override
	public boolean fimDeJogo() {
		
		return fimdeJogoLobo() || fimdeJogoOvelha();
	}

	public Jogo() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				tabuleiro[i][j] = -1;
			}
		}

		//posicionamento inicial das ovelhas!
		tabuleiro[7][1] = 0;
		tabuleiro[7][3] = 0;
		tabuleiro[7][5] = 0;
		tabuleiro[7][7] = 0;
		//posicionamento inicial do lobão!
		tabuleiro[0][3] = 8;

	}

	
	@Override
	public void nextPayer() {
		
		System.out.println("Sua vez!");
		
	}
	

}
