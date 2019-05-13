package servidorInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterface extends Remote {

	public Object executaJogada(int c1, int c2)throws RemoteException;// c1 e c2 são as respectivas coordenadas
	public void conectaServidorRemoto() throws RemoteException;//essa função efetua a conexão da instancia servidor ataual com a remota que ele se comunica!
	
}
