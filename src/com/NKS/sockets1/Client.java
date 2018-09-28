package com.NKS.sockets1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.NKS.application.Main;


//import org.json.JSONObject;

//import com.NKS.json.ReadJsonFile;
//import com.NKS.json.WriteJsonFile;

//Cliente de prueba

public class Client {
private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket clientSocket = new Socket("localhost", 9000);
		Main inter = new Main();
		inter.main(null);
		DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		
		System.out.println("Enter your name: ");
		String name = scanner.nextLine();
		dos.writeUTF(name);
		for(int i = 0; i < 10; i++) {
			String serverMessage = dis.readUTF();
			System.out.println(serverMessage + ", " + i);
		}
				
		dos.writeUTF("Recibido");
		if(dis.readUTF().equals("OK")) {
			dos.writeUTF("Adiós");
			stopConn(clientSocket);
		}
		
	}
	
	private static void stopConn(Socket s) {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}