package com.NKS.sockets1;

import java.io.*;
import java.net.*;

public class Client {
	
	Socket cliente;
	int puerto=9000;
	String ip="127.0.0.1";
	PrintStream salida;
	BufferedReader entrada, teclado ;
	
	public void inicio() {
		try {
				
				cliente =new Socket(ip,puerto);
				
				entrada= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				teclado= new BufferedReader(new InputStreamReader(System.in));
				String tec= teclado.readLine();
				
				salida= new PrintStream(cliente.getOutputStream());
				salida.println(tec);
				
				String msg=entrada.readLine();
				System.out.println(msg);
				
				entrada.close();
				salida.close();
				teclado.close();
				cliente.close();
			}catch(Exception e) {};
	}
}