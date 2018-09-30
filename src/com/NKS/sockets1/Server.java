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
import com.NKS.json.WriteJsonFile;
import com.NKS.lists.List;


public class Server {
	private int port = 9000;
	private Socket socketP1 = null;
	private Socket socketP2 = null;
	private String p1;
	private String p2;
	private int p1Pts;
	private int p2Pts;
	private Queue<Socket> sockets = new Queue<Socket>();
	@SuppressWarnings("unused")
	private String name = null;
	private static List<Linea> lineas = new List<Linea>();

	@SuppressWarnings("resource")
	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				Socket clientSocket = serverSocket.accept();
				sockets.enqueue(clientSocket);
				if(sockets.getLenght() == 1) {
					socketP1 = sockets.peek();
					send(socketP1, "wait");
				}else if(sockets.getLenght()>1) {
					socketP1 = sockets.dequeue();
					socketP2 = sockets.dequeue();
					send(socketP1, "start");
					send(socketP2, "start");
					
				}
				
//				Thread t1 = new Thread() {
//					public void run() {
//						try {
//							if(sockets != null && socketP1 == null && socketP2 == null) {
//								socketP1 = sockets.dequeue();
//								handleClientSocket(socketP1);
//							}
//						} catch (IOException | InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				};
//
//				@SuppressWarnings("unused")
//				Thread t2 = new Thread() {
//					public void run() {
//						try {
//							if(sockets != null) {
//								socketP2 = sockets.dequeue();
//								System.out.println("Entra 2");
//								handleClientSocket(socketP2);
//							}
//						} catch (IOException | InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				};
//				if(sockets.getLenght() > 0) {
//					t1.start();
////					t2.start();
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(Socket clientSocket, String message) throws IOException {
		Thread t = new Thread() {
			public void run() {
				try {
					DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
					WriteJsonFile out = new WriteJsonFile(clientSocket == socketP1 ? p2 : p1, lineas.getElement(lineas.getLenght()-1), clientSocket == socketP1 ? p2Pts : p1Pts, clientSocket == socketP1 ? p1Pts : p2Pts, message);
					dos.writeUTF(out.getJson());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		t.start();
	}
	
	public void recieve(Socket clientSocket) throws IOException {
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		ReadJsonFile in = new ReadJsonFile(dis.readUTF());
		if(clientSocket == socketP1) {
			p1 = in.getName();
		}else {
			p2 = in.getName();
		}
		lineas.add(in.getLine());
	}
	
//	private void handleClientSocket(Socket clientSocket) throws IOException, InterruptedException {
//
//		DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
//		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
//		Thread t = new Thread() {
//			public void run() {
//				ReadJsonFile json;
//				try {
//					json = new ReadJsonFile(dis.readUTF());
//					name = json.getName();
//					System.out.println(name);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//			}
//		};
//		t.start();
////		
////		System.out.println("Entra");
////		name = dis.readUTF();
////		System.out.println(name);
//		Thread t2 = new Thread() {
//			public void run() {
//				for(int i = 0; i < 10; i++) {
//					try {
//						dos.writeUTF("holi, " + name + ", " + i);
////						Thread.sleep(1000);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		t2.start();
////		t2.join();
////		t.start();
//		
//		if(dis.readUTF().equals("Recibido")) {
//			dos.writeUTF("OK");
//		}
//		if(dis.readUTF().equals("Adiós") && socketP1 != null) {
//			clientSocket.close();
//			socketP1 = null;
//		}
////		else if(dis.readUTF().equals("Adiós") && socketP1 == null) {
////			clientSocket.close();
//////			socketP1 = null;
////			socketP2 = null;
////		}
//	}

	
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
