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
public class Servidor2 extends UnicastRemoteObject implements ServidorInterface {

	private String nome;
	private static Servidor2 servidor2;
	private static Servidor2 bkServidor2;
	
	private ServidorInterface servidorRemoto;// aqui vai estar a referencia da instancia do segundo servidor...
	private ServidorInterface bkPrimario;
	private Jogo jogoServ2 = new Jogo();


	public Servidor2(String nome) throws RemoteException {
		this.nome = nome;
	}


	@Override
	public String iAm() {

		return nome;
	}

	public static Servidor2  startServidor(){

		try{
			servidor2 = new Servidor2("Servidor2");
			LocateRegistry.createRegistry(3696);
			Naming.rebind("//127.0.0.1:3696/Servidor2",(Remote)servidor2);
			
			bkServidor2 = new Servidor2("bkSrevidor2");
			LocateRegistry.createRegistry(3796);
			Naming.rebind("//127.0.0.1:3796/bkSrevidor2",(Remote)servidor2);
			
			System.out.println(servidor2.iAm()+" pronto.");
			return servidor2;
		}
		catch(RemoteException e){
			System.out.println("Exceção remota 1: " + e);
			e.printStackTrace();
		}
		catch(MalformedURLException e){
			System.out.println("Exceção remota 2: " + e);
		}
		return servidor2;
	}

	@Override
	public void conectaServidorRemoto() throws RemoteException {
		try {
			
			servidorRemoto = (ServidorInterface) Naming.lookup("//127.0.0.1:3694/Servidor1");
			
			bkPrimario = (ServidorInterface) Naming.lookup("//127.0.0.1:3894/bkServidor1");

		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			System.out.println("Servidor remoto não está ativo: "+e.getMessage());
			//e.printStackTrace();
		}

	}

	@Override
	public void testRemoto() {


		try {
			System.out.println("Esse é: "+iAm());
			System.out.println("conectado com: "+servidorRemoto.iAm());

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void servExecutaJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador)throws RemoteException {
		
		jogoServ2.executaJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		servidorRemoto.servSincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		servidorRemoto.nextPayer();

	}
	
	@Override
	public void servBkExecutaJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador)throws RemoteException {
		
		jogoServ2.executaJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		bkPrimario.servSincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		bkPrimario.nextPayer();

	}
	
	@Override
	public void servSincJogada(int iOrigem, int jOrigem, int iDestino, int jDestino, char jogador)
			throws RemoteException {
		jogoServ2.sincJogada(iOrigem, jOrigem, iDestino, jDestino, jogador);
		
	}
	
	@Override
	public boolean servFimdeJogo() throws RemoteException {
				return jogoServ2.fimDeJogo();
	}
	@Override
	public void servImprimeTabuleiro() throws RemoteException {
		jogoServ2.imprimeTabuleiro();

	}
	
	@Override
	public void nextPayer() throws RemoteException {
		jogoServ2.nextPayer();
		
	}




}
