package clientes;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

import servidores.Servidor1;

public class Cliente1 {

	public static void main(String[] args) throws RemoteException {



		Servidor1 serv1 = Servidor1.startServidor();
		try {
			System.in.read();// sï¿½ uma pausa para que o servidor 2 possa ser levantado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serv1.iAm();
		serv1.conectaServidorRemoto();
		try {
			System.in.read();// sï¿½ uma pausa para que o servidor 2 possa ser levantado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//serv1.servExecutaJogada(7, 1, 6, 0, 'O');

		Scanner ler = new Scanner(System.in);
		int iO,jO,iD,jD;
		serv1.servImprimeTabuleiro();
		System.out.println("Você joga com as Ovelhas!");
		System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");

		try {
			while(!serv1.servFimdeJogo()) {
				iO = ler.nextInt();
				jO = ler.nextInt();

				iD = ler.nextInt();
				jD = ler.nextInt();

				if(iO == -50) serv1.down();

				serv1.servExecutaJogada(iO-1, jO-1, iD-1, jD-1, 'O');
				System.out.println("Digite o iOrigem, jOrigem, iDestino, jDestino");

			}
			System.out.println("Fim de jogo!");
			ler.close();
		} catch (RemoteException Re) {


		}


	}




}
