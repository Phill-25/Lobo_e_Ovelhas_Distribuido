package servidores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import clientes.Cliente1;
import clientes.Cliente2;
import jogo.Jogo;
import servidorInterface.ServidorInterface;

@SuppressWarnings("serial")
public class Servidor1 extends UnicastRemoteObject implements ServidorInterface {

	private String nome;
	private static Servidor1 servidor1;
	private static Servidor1 bkServidor1;
	
	private  ServidorInterface servidorRemoto;// aqui vai estar a referencia da instancia do segundo servidor...
	private ServidorInterface bkPrimario;
	private Jogo jogoServ1 = new Jogo(); 


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
			
			bkServidor1 = new Servidor1("bkServidor1");
			LocateRegistry.createRegistry(3894);
			Naming.rebind("//127.0.0.1:3894/bkServidor1",(Remote)servidor1);
			
			System.out.println(servidor1.iAm()+" pronto.");
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

			bkPrimario = (ServidorInterface) Naming.lookup("//127.0.0.1:3796/bkSrevidor2");

		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			
			System.out.println("Servidor remoto não está ativo: "+e.getMessage());
			
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
	
	public void down() {
		servidorRemoto = null;
	}

	@Override
	public void servExecutaJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador)throws RemoteException  {
		
		try {
			jogoServ1.executaJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
			servidorRemoto.servSincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
			servidorRemoto.nextPayer();
			
		} catch (NullPointerException e) {
			
			bkPrimario.servSincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
			bkPrimario.nextPayer();
			
		}

	}
	
	@Override
	public void servBkExecutaJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador)throws RemoteException {
		
		jogoServ1.executaJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		bkPrimario.servSincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		bkPrimario.nextPayer();

	}
	
	@Override
	public void servSincJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador)
			throws RemoteException {
		jogoServ1.sincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		
	}
	
	@Override
	public boolean servFimdeJogo() throws RemoteException {
				return jogoServ1.fimDeJogo();
	}
	@Override
	public void servImprimeTabuleiro() throws RemoteException {
		jogoServ1.imprimeTabuleiro();

	}
	
	@Override
	public void nextPayer() throws RemoteException {
		jogoServ1.nextPayer();
		
	}

	


}
