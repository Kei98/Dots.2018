package com.NKS.sockets1;

//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
//import java.nio.charset.StandardCharsets;

import com.NKS.lists.Queue;
import com.NKS.Puntos.Linea;
import com.NKS.json.ReadJsonFile;
import com.NKS.lists.List;


public class Server {
	private int port = 9000;
	private Socket socketP1 = null;
	private Socket socketP2 = null;
	private Queue<Socket> sockets = new Queue<Socket>();
	private String name = null;
	private static List<Linea> lineas = new List<Linea>();

	@SuppressWarnings("resource")
	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				Socket clientSocket = serverSocket.accept();
				sockets.enqueue(clientSocket);
				Thread t1 = new Thread() {
					public void run() {
						try {
							if(sockets != null && socketP1 == null && socketP2 == null) {
								socketP1 = sockets.dequeue();
								handleClientSocket(socketP1);
							}
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
				};

				Thread t2 = new Thread() {
					public void run() {
						try {
							if(sockets != null) {
								socketP2 = sockets.dequeue();
								System.out.println("Entra 2");
								handleClientSocket(socketP2);
							}
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				if(sockets.getLenght() > 1) {
					t1.start();
					t2.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleClientSocket(Socket clientSocket) throws IOException, InterruptedException {

		DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		Thread t = new Thread() {
			public void run() {
				ReadJsonFile json;
				try {
					json = new ReadJsonFile(dis.readUTF());
					name = json.getName();
					System.out.println(name);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
//		t.start();
		Thread t2 = new Thread() {
			public void run() {
				for(int i = 0; i < 10; i++) {
					try {
						dos.writeUTF("holi, " + name + ", " + i);
//						Thread.sleep(1000);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		t2.start();
		
		if(dis.readUTF().equals("Recibido")) {
			dos.writeUTF("OK");
		}
		if(dis.readUTF().equals("Adiós") && socketP1 != null) {
			clientSocket.close();
			socketP1 = null;
		}
		else if(dis.readUTF().equals("Adiós") && socketP1 == null) {
			clientSocket.close();
//			socketP1 = null;
			socketP2 = null;
		}
	}

	
	@SuppressWarnings({ "rawtypes" })
	public static void MakeLine(List Punto1,List Punto2) {
		Punto1.printL();
		Linea jugada=new Linea(Punto1,Punto2);
		jugada.figure(lineas,0,jugada, null);
		lineas.add(jugada);
	}
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
}
