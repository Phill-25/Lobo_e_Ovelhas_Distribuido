package servidorInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterface extends Remote {

//	public Object executaJogada(int c1, int c2)throws RemoteException;// c1 e c2 são as respectivas coordenadas
	
	public void conectaServidorRemoto() throws RemoteException;//essa função efetua a conexão da instancia servidor ataual com a remota que ele se comunica!
	
	public void testRemoto()throws RemoteException ;
	
	public String iAm()throws RemoteException;
	//interfaces de acesso aos recursos do jogo para o cliente
	
	public void servExecutaJogada(int iOrigem, int jOrigem, int iDestino , int jDestino, char jogador)throws RemoteException;
	
	public void servImprimeTabuleiro()throws RemoteException;
	
	public boolean servFimdeJogo()throws RemoteException;
	
	public void servSincJogada(int iOrigem, int jOrigem, int iDestino , int jDestino, char jogador)throws RemoteException;
	
	public void nextPayer() throws RemoteException;
}
