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
	private static List<Linea> lineas = new List<Linea>();
	private boolean line = false;
	private int count = 0;
	private String message = null;
	
	

	@SuppressWarnings({ "resource", "static-access" })
	public void start() throws InterruptedException {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			int n = 0;
			int n2 = 0;
			while(true) {
				Thread t = new Thread() {
					public void run() {
						Socket clientSocket;
						try {
							System.out.println(sockets.getLenght());
							clientSocket = serverSocket.accept();
							sockets.enqueue(clientSocket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				t.start();
				t.sleep(1000);
				
				if(sockets.getLenght() == 1) {
					if(socketP1 == null) {
						socketP1 = sockets.peek();
						send(socketP1, "1", "h");
					}else if(socketP1 != null && socketP2 == null){
						System.out.println("Entra para recibir");
						this.recieve(socketP1);
						System.out.println("Recibi� el mensaje");
						if(this.message.equals("1")) {
							System.out.println("Entra al if de que recibi� el mensaje");
							send(socketP1, "wait", p1);
							this.message = null;
						}
					}
				}else if(sockets.getLenght() > 1 && socketP2 == null){
					System.out.println("Entra al else if");
					socketP1 = sockets.dequeue();
					socketP2 = sockets.dequeue();
					System.out.println("env�a a ambos clientes");
					send(socketP1, " ", p1);
					System.out.println("A 2");
					send(socketP2, "2", "h");
					Thread.sleep(100);
					this.recieve(socketP2);
					send(socketP2, " ", "h");
					n++;
//					n2++;
				}
				
				if(socketP1 != null && n>0) {
					if(n == 1 || n == 2 || n == 4 || n == 5) {
						System.out.println("Recibe de 1");
						this.recieve(socketP1);
						if(n2 == 0) {
							this.send(socketP1, "", p1);
						}
						n++;
						if(n == 2 && n2 > 0) {
							this.send(socketP1, "yourTurn", p1);
						}
						if(n == 5) {
							this.send(socketP2, "", p2);
						}
						
					}
				}
				if(socketP2 != null) {
					if(n == 1 || n == 3 || n == 6) {
						if(n == 3) {
							this.send(socketP2, "yourTurn", p2);
						}else {
							this.send(socketP2, "", p2);
							System.out.println("Recibe de 2");
							this.recieve(socketP2);
							if(n == 7) {
								this.recieve(socketP2);
								n = 2;
								n2++;
								this.send(socketP1, "", p1);
								return;
							}
						}
						System.out.println("Incrementa el n");
						n++;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void send(Socket clientSocket, String message, String name1) throws IOException {
		Thread t = new Thread() {
			public void run() {
				try {
					DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
					if(lineas.getLenght() >= 1) {
						System.out.println("Est� cuando es != null" + lineas.getLenght());
//						lineas.getElement(lineas.getLenght() -1).printLn();
						WriteJsonFile out = new WriteJsonFile(name1, lineas.getElement(lineas.getLenght()-1), clientSocket == socketP1 ? p2Pts : p1Pts, clientSocket == socketP1 ? p1Pts : p2Pts, message);
						dos.writeUTF(out.getJson());
//						System.out.println(out.getJson());
					}else {
						WriteJsonFile out = new WriteJsonFile(name1, null, clientSocket == socketP1 ? p2Pts : p1Pts, clientSocket == socketP1 ? p1Pts : p2Pts, message);
						dos.writeUTF(out.getJson());
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
		t.start();
	}
	
	public synchronized void recieve(Socket clientSocket) throws IOException {
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		String disStr = dis.readUTF(); 
		System.out.println("Entra a recieve" + disStr);
		if(disStr != null) {
			System.out.println("Entra if readUTF");
			ReadJsonFile in = new ReadJsonFile(disStr);
			System.out.println("A�n no pasa algo");
			if(in.getName() != null && count < 2) {
				if(clientSocket == socketP1 && count == 0) {
					p1 = in.getName();
					count++;
				}else if(clientSocket == socketP2 && count == 1) {
					p2 = in.getName();
					count++;
				}
			}
			if(clientSocket == socketP1 && in.getLine() != null) {
				System.out.println("Entra al if");
				p1 = in.getName();
				this.makeLine(in.getLine().punto1, in.getLine().punto2);
				if(this.line) {
					this.send(socketP2, "advFigure", p2);
					this.send(socketP1, "figure", p1);
					return;
				}
				this.send(socketP2, "", p2);
			}else if(clientSocket == socketP2 && in.getLine() != null){
				System.out.println("Entra al else if");
				p2 = in.getName();
				this.makeLine(in.getLine().punto1, in.getLine().punto2);
				if(this.line) {
					this.send(socketP1, "advFigure", p1);
					this.send(socketP2, "figure", p2);
					return;
				}
				this.send(socketP1, "", p1);
			}else if(in.getMessage().equals("1")) {
				this.message = "1";
				if(clientSocket == socketP1) {
					System.out.println(in.getName());
					p1 = in.getName();
				}
			}else if(in.getMessage().equals("")) {
				if(count == 0 && clientSocket == socketP2) {
					System.out.println(in.getName());
					p2 = in.getName();
				}
			}
		}
		System.out.println("Sale con �xito");
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	public void makeLine(List Punto1,List Punto2) {
		Punto1.printL();
		Linea jugada=new Linea(Punto1,Punto2);
		if(jugada.figure(lineas,0,jugada, null)) {
			this.line = true;
		}
		lineas.add(jugada);
	}
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
