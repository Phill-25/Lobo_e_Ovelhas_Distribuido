package servidores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import servidorInterface.ServidorInterface;

public class Servidor2 extends UnicastRemoteObject implements ServidorInterface {

	private String nome;
	private Servidor2 servidor2;
	private Servidor1 servidorRemoto;// aqui vai estar a referencia da instancia do segundo servidor...

	@Override
	public Object executaJogada(int c1, int c2) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Servidor2(String nome) throws RemoteException {
		this.nome = nome;
	}


	@Override
	public String iAm() {

		return nome;
	}

	public void  startServidor(){

		try{
			servidor2 = new Servidor2("Servidor2");
			LocateRegistry.createRegistry(3696);
			Naming.rebind("//127.0.0.1:3696/Servidor1",(Remote)servidor2);
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
	
@Override
	public void testRemoto() {
		
		System.out.println("Esse é: "+iAm());
		System.out.println("chamando: "+servidorRemoto.iAm());
		
	}


}
