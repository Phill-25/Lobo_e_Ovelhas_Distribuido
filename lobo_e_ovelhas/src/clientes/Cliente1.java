package clientes;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

import servidores.Servidor1;

public class Cliente1 {
	
	public static void main(String[] args) throws RemoteException {
		
		
		
		Servidor1 serv1 = Servidor1.startServidor();
		try {
			System.in.read();// só uma pausa para que o servidor 2 possa ser levantado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serv1.iAm();
		serv1.conectaServidorRemoto();
		try {
			System.in.read();// só uma pausa para que o servidor 2 possa ser levantado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//serv1.servExecutaJogada(7, 1, 6, 0, 'O');
		
		Scanner ler = new Scanner(System.in);
		int iO,jO,iD,jD;
		serv1.servImprimeTabuleiro();
		System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");
		while(true) {
			iO = ler.nextInt();
			jO = ler.nextInt();
			
			iD = ler.nextInt();
			jD = ler.nextInt();
			
			serv1.servExecutaJogada(iO, jO, iD, jD, 'O');
			System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");
		}
		
		
	}
	
	
	
	
}
