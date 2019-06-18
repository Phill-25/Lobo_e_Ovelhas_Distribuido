package jogo;

public interface Interface_Jogo {
	//interface de acesso ao recursos do jogo para o servidor
	public void executaJogada(int iOrigem, int jOrigem, int iDestino , int jDestino, char jogador);
	
	public boolean fimdeJogo();
	
	public void imprimeTabuleiro(); //deixei o retorno como void pois somente na implementação se tem certeza disso...
	
	
	
	
	

}
