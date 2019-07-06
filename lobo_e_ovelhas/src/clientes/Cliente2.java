package clientes;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

import servidores.Servidor2;

public class Cliente2 {



	public static void main(String[] args) throws RemoteException {


		Servidor2 serv2 = Servidor2.startServidor();
		try {
			System.in.read(); // só uma pausa para que o servidor 1 possa ser levantado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serv2.iAm();
		serv2.conectaServidorRemoto();
		//serv2.testRemoto();
		try {
			System.in.read(); // só uma pausa para que o servidor 1 possa ser levantado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner ler = new Scanner(System.in);
		int iO,jO,iD,jD;
		System.out.println("Você joga com o Lobo!");
		System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");
		serv2.servImprimeTabuleiro();
		
		try {
			while(serv2.servFimdeJogo()) {
				iO = ler.nextInt();
				jO = ler.nextInt();
				
				iD = ler.nextInt();
				jD = ler.nextInt();
				
				serv2.servExecutaJogada(iO-1, jO-1, iD-1, jD-1, 'L');
				System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");
			}
		} catch (RemoteException Re) {
			
			while(true) {
				iO = ler.nextInt();
				jO = ler.nextInt();
				
				iD = ler.nextInt();
				jD = ler.nextInt();
				
				serv2.servBkExecutaJogada(iO-1, jO-1, iD-1, jD-1, 'L');
				System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");
			}
		}



	}

}





