package servidorInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterface extends Remote {

	public Object executaJogada(int c1, int c2)throws RemoteException;// c1 e c2 s�o as respectivas coordenadas
	public void conectaServidorRemoto() throws RemoteException;//essa fun��o efetua a conex�o da instancia servidor ataual com a remota que ele se comunica!
	
}
