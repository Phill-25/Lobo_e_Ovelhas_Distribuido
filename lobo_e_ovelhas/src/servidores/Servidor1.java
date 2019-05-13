package servidores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import servidorInterface.ServidorInterface;

public class Servidor1 extends UnicastRemoteObject implements ServidorInterface {

	private Servidor1 servidor1;
	//private Servidor2 servidor2; aqui vai estar a referencia da instancia do segundo servidor...

	@Override
	public Object executaJogada(int c1, int c2) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Servidor1() throws RemoteException {
		
	}
	
	
	public void  startServidor(){

		try{
			servidor1 = new Servidor1();
			LocateRegistry.createRegistry(3694);
			Naming.rebind("//127.0.0.1:3694/Servidor1",(Remote)servidor1);
			System.out.println("Servidor remoto pronto.");
		}
		catch(RemoteException e){
			System.out.println("Exceção remota 1: " + e);
			e.printStackTrace();
		}
		catch(MalformedURLException e){
			System.out.println("Exceção remota 2: " + e);
		}
	}
	
	@Override
	public void conectaServidorRemoto() throws RemoteException {
		// a implementação é basicamente fazer a referênciação que um cliente 
		//faria para que o servidor1 se comunique com o outro servidor.
		
	}
	
	
}
