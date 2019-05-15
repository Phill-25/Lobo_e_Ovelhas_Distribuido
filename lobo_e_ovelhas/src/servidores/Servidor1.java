package servidores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import servidorInterface.ServidorInterface;

@SuppressWarnings("serial")
public class Servidor1 extends UnicastRemoteObject implements ServidorInterface {

	private String nome;
	private static Servidor1 servidor1;
	private  ServidorInterface servidorRemoto;// aqui vai estar a referencia da instancia do segundo servidor...

	@Override
	public Object executaJogada(int c1, int c2) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Servidor1(String nome) throws RemoteException {
		super();
		this.nome = nome;
		
	}

	@Override
	public String iAm() {

		return nome;
	}

	public static Servidor1  startServidor(){

		try{
			servidor1 = new Servidor1("Servidor1");
			LocateRegistry.createRegistry(3694);
			Naming.rebind("//127.0.0.1:3694/Servidor1",(Remote)servidor1);
			System.out.println("Servidor remoto pronto.");
			return servidor1;
		}
		catch(RemoteException e){
			System.out.println("Exceção remota 1: " + e);
			e.printStackTrace();
		}
		catch(MalformedURLException e){
			System.out.println("Exceção remota 2: " + e);
		}
		return servidor1;
	}
	
	@Override
	public void conectaServidorRemoto() throws RemoteException {
		try {
			servidorRemoto = (ServidorInterface) Naming.lookup("//127.0.0.1:3696/Servidor2");
		
		
		} catch (MalformedURLException | NotBoundException e) {
			System.out.println("Erro no cliente: "+e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void testRemoto() {

		
		try {
			System.out.println("Esse é: "+iAm());
			System.out.println("Conectado com: "+servidorRemoto.iAm());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
