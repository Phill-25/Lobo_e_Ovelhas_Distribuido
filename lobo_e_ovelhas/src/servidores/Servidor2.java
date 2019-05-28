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
import servidorInterface.ServidorInterface;

@SuppressWarnings("serial")
public class Servidor2 extends UnicastRemoteObject implements ServidorInterface {

	private String nome;
	private static Servidor2 servidor2;
	private ServidorInterface servidorRemoto;// aqui vai estar a referencia da instancia do segundo servidor...

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

	public static Servidor2  startServidor(){

		try{
			servidor2 = new Servidor2("Servidor2");
			LocateRegistry.createRegistry(3696);
			Naming.rebind("//127.0.0.1:3696/Servidor2",(Remote)servidor2);
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
		
		
		} catch (MalformedURLException | NotBoundException e) {
			System.out.println("Erro no cliente: "+e.getMessage());
			e.printStackTrace();
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

//public int[][] tabuleiroInicial() {
//	
//	int[][] tabuleiro = {
//			{-2, 1, -2, 1, -2, 1, -2, 1}, // 8
//			{0, -2, 0, -2, 0, -2, 0, -2}, // 7
//			{-2, 0, -2, 0, -2, 0, -2, 0}, // 6
//			{0, -2, 0, -2, 0, -2, 0, -2}, // 5
//			{-2, 0, -2, 0, -2, 0, -2, 0}, // 4
//			{0, -2, 0, -2, 0, -2, 0, -2}, // 3
//			{-2, 0, -2, 0, -2, 0, -2, 0}, // 2
//			{0, -2, 0, -2, 8, -2, 0, -2} // 1
////			 a, b,  c, d,  e,  f,  g, h
//	};	 
//	
////	-2 são posições invalidas
////	0 são posições validas
////	1 são as ovelhinhas
////	8 é o Lobão
//	
//	return tabuleiro;
//	
//	}
//	
//	public void Jogo(int[][] tabuleiro, Cliente1 C1, Cliente2 C2) {
//		System.out.println("teste de commit Maycon");
//	}


}
