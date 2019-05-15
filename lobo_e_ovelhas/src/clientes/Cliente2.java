package clientes;

import java.io.IOException;
import java.rmi.RemoteException;

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
		serv2.conectaServidorRemoto();
		serv2.testRemoto();


	}





}





