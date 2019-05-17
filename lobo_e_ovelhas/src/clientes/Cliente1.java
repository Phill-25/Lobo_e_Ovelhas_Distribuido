package clientes;

import java.io.IOException;
import java.rmi.RemoteException;

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
		
		serv1.conectaServidorRemoto();
		serv1.testRemoto();
		
		
	}
	
	
	
	
}
