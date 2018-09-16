package com.NKS.sockets1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.NKS.Puntos.Linea;
import com.NKS.lists.List;

public class Server {


	ServerSocket server;
	Socket socket;
	int puerto=9000;
	DataOutputStream salida;
	BufferedReader entrada;
	@SuppressWarnings("rawtypes")
	static List Lineas;
	
	
	
	
	public void iniciar() {
		try {
			
			server=new ServerSocket(puerto);
			socket=new Socket();
			socket=server.accept();
			
			entrada= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String mensaje=entrada.readLine();
			System.out.println(mensaje);
			salida= new DataOutputStream(socket.getOutputStream());
			salida.writeUTF("Holant mundo");
			socket.close();
		}catch(Exception e) {};
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void MakeLine(List Punto1,List Punto2) {
		Punto1.printL();
		Linea jugada=new Linea(Punto1,Punto2);
		jugada.figure(Lineas,0,jugada, null);
		Lineas.add(jugada);
	}
}
