package com.NKS.sockets1;

import java.io.*;
import java.net.*;

import com.NKS.application.Main;


//import org.json.JSONObject;

//import com.NKS.json.ReadJsonFile;
//import com.NKS.json.WriteJsonFile;

//Cliente de prueba

public class Client {
//private static Scanner scanner = new Scanner(System.in);
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket clientSocket = new Socket("localhost", 9000);
		Main interfac = new Main();
		interfac.main(null);
		while(interfac.notEnded()) {
//			if(!interfac.getName().equals(" ")) {
//				System.out.println("Enter your name: ");
//				String name = interfac.getName();
//
//				DataOutputStream dos;
//				dos = new DataOutputStream(clientSocket.getOutputStream());
//				DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
//				dos.writeUTF(name);
//				for(int i = 0; i < 10; i++) {
//					String serverMessage = dis.readUTF();
//					System.out.println(serverMessage + ", " + i);
//
//					dos.writeUTF("Recibido");
//					if(dis.readUTF().equals("OK")) {
//						dos.writeUTF("Adiós");
//						stopConn(clientSocket);
//					}
//				}
//			}
//			System.out.println("Si no if");
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