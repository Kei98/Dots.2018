package com.NKS.application;
	
import java.awt.MouseInfo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.NKS.Puntos.Linea;
import com.NKS.json.ReadJsonFile;
import com.NKS.json.WriteJsonFile;
import com.NKS.lists.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
//import javafx.scene.shape.Polygon;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
	public Scene scene,scene2;
	public Stage window,wind_game;
	public Button btn1,btn2;
	private TextField name;
	
	public String getName1() {
//		System.out.println(name.getText());
		return name.getText();
	}

	public Label name_game,onTurn_label,point,opPoint;
	public Label dot;
	public Pane game,intro;
	public List<Integer> dotsx,dotsy;
	public Integer num_click;
	public List<Integer> temp_dot,temp_dot2;
	public Circle circ,temp_circ,onTurn;
	public Line line;
	
	static List<Linea> Lineas;
	@SuppressWarnings("rawtypes")
	public List elements;
	static int figure;
	public int Turn = 0;
	private int points = 0;
	
//	Sockets
	
	private Socket clientSocket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
//	Escritura y lectura de Json
	
	private WriteJsonFile out;
	private ReadJsonFile in;
	
//	
	private Linea adversaryMove;
	@SuppressWarnings("unused")
	private int adversaryPts;
	@SuppressWarnings("unused")
	private int myPoints;
	private int playerNumber;
	
	private int count;
	@SuppressWarnings("unused")
	private int countt = 0;
	private String message = null;
	

	public void start(Stage primaryStage) throws UnknownHostException, IOException, InterruptedException {
		window = primaryStage;
		wind_game = new Stage();
		
//  	Images
		ImageView iv = new ImageView(new Image("/Multimedia/Dots.png"));
		ImageView iv2 = new ImageView(new Image("/Multimedia/dots.jpg"));
		ImageView play_btn = new ImageView(new Image("/Multimedia/play.png"));
		ImageView logo = new ImageView(new Image("/Multimedia/Logo.png"));
		iv2.setPreserveRatio(true);
		
//		Panes
		intro = new Pane();
		game = new Pane();
		
//		Labels and TextFields
		point = new Label("Your Points: 0");
		opPoint = new Label("Opponent Points: 0");
		onTurn_label = new Label("Turn:");
		onTurn_label.setStyle("-fx-font-size: 20");
		name = new TextField();
		Label player_name = new Label("Player Name");
		name_game = new Label("Player: ");
		point.setStyle("-fx-font-size: 20");
		opPoint.setStyle("-fx-font-size: 20");
		name_game.setStyle("-fx-font-size: 20");
		VBox player = new VBox();
		player.getChildren().addAll(player_name,name);
		player.setSpacing(15);

//		Scenes
		scene2 = new Scene(game,700,600);
		scene = new Scene(intro,500,500);
		
		
//		Inicialización del socket
		clientSocket = new Socket("localhost", 9000);
		
// 		Button Config	
		btn1 = new Button("Play",play_btn);
		btn1.setOnAction(e -> {
			try {
//				System.out.println(Thread.currentThread());
				this.verify();
				while(true) {
//					System.out.println(Thread.currentThread());
					if(this.message.equals("begin")) {
						System.out.println("Entra al equals de begin");
						createcircles();
						break;
					}
				}
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}			
		});
		btn1.setContentDisplay(ContentDisplay.CENTER);
		btn1.setStyle("-fx-background-color: transparent; -fx-font-size: 30; -fx-font-color: white");
		
		btn2 = new Button("Menu");
		btn2.setOnAction(e -> {
			wind_game.hide();
			try {
				start(primaryStage);
				window.show();
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		});
//		Others
		num_click = 1;
//		Turn = 0;
		onTurn = new Circle();
		onTurn.setRadius(8);
		game.setOnMouseClicked(e -> { 
			try {
				this.recieve();
			} catch (IOException | InterruptedException e2) {
				e2.printStackTrace();
			}
			if(onTurn()) {
				try {
					Click(0,0);
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}

				try {
					clickButton(e);
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
				);
//		Polygon polygon = new Polygon();
//		polygon.getPoints().addAll(new Double[]{
//		    110.0, 110.0,
//		    210.0, 110.0,
//		    210.0, 210.0,
//		    110.0, 210.0});
//		Rectangle rect = new Rectangle((5.0-3.0)*100,(5.0-4.0)*100);
//		rect.setLayoutX(300+10);
//		rect.setLayoutY(400+10);
		
		intro.setStyle("-fx-background-color: cornsilk");
		temp_dot = new List<Integer>();
		temp_dot2 = new List<Integer>();
		Lineas = new List<Linea>();
//		Dots
		dotsx = new List<Integer>();
		dotsx.add(100);
		dotsx.add(200);
		dotsx.add(300);
		dotsx.add(400);
		dotsx.add(500);
		dotsx.add(600);
		dotsx.add(700);
		dotsx.add(800);
		dotsx.add(900);
		dotsy = new List<Integer>();
		dotsy.add(100);
		dotsy.add(200);
		dotsy.add(300);
		dotsy.add(400);
		dotsy.add(500);
		dotsy.add(600);
		dotsy.add(700);
		dotsy.add(800);
		dotsy.add(900);
//		Add to Scene
		btn1.setLayoutX(370);
		btn1.setLayoutY(400);
		btn2.setLayoutX(640);
		btn2.setLayoutY(560);
		iv.setLayoutX(0);
		iv.setLayoutX(0);
		logo.setLayoutX(127);
		logo.setLayoutY(10);
		player.setLayoutX(30);
		player.setLayoutY(300);
		name_game.setLayoutX(10);
		name_game.setLayoutY(10);
		point.setLayoutX(280);
		point.setLayoutY(10);
		opPoint.setLayoutX(280);
		opPoint.setLayoutY(560);
		onTurn_label.setLayoutX(500);
		onTurn_label.setLayoutY(30);
		onTurn.setLayoutX(570);
		onTurn.setLayoutY(45);
//		Place
		game.getChildren().addAll(btn2,name_game,onTurn_label,onTurn,point,opPoint);
		intro.getChildren().addAll(iv2,logo,btn1,player);
//		Window config
		window.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Menu");
		window.show();
		wind_game.initStyle(StageStyle.UNDECORATED);
		wind_game.setScene(scene2);
		System.out.println("Segundo recieve");
		this.recieve();
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void verify() throws IOException, InterruptedException {
		if(name.getText().equals("")){
			AlertBox.show("Error", "You must introduce a name");
		}else {
			System.out.println("Primero");
			if(this.message == null || !this.message.equals("begin")) {
				if(this.playerNumber == 1) {
					this.send("1");
				}
//				System.out.println("Pos sí se cumple");
				Thread.sleep(100);
				this.recieve();
				this.onTurn();
//				Thread.sleep(50);
			}else if(this.message.equals("begin")) {
				if(this.playerNumber == 2) {
					this.send("");
					if(countt == 0) {
						while(!onTurn()) {
							try {
								this.recieve();
								System.out.println("en el while");
								Thread.sleep(1000);
							} catch (IOException | InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
//			name_game.setText("Player: "+ name.getText());
//			window.close();
//			wind_game.show();
//			window.setTitle("Game");
		}
	}
	public void Click(int j, int i) throws IOException, InterruptedException {
//		this.recieve();
		if(onTurn()) {
			int x = generateX();
			int y = generateY();
			int x2 = MouseInfo.getPointerInfo().getLocation().x-334;
			int y2 = MouseInfo.getPointerInfo().getLocation().y-43;
			if (num_click == 1) {
				while(j<dotsx.getLenght() && i<dotsy.getLenght()) {
					if(x2 <= dotsx.getElement(j)+20 && x2 >= dotsx.getElement(j) ) {
						if(y2 <= dotsy.getElement(i)+20 && y2 >= dotsy.getElement(i) ) {
							temp_dot.add(x);
							temp_dot.add(y);
							temp_circ = new Circle();
							temp_circ.setLayoutX(x * 100 + 10);
							temp_circ.setLayoutY(y * 100 + 10);
							temp_circ.setRadius(10);
							temp_circ.setFill(Color.RED);
							game.getChildren().addAll(temp_circ);
							num_click = 2;
							j = dotsx.getLenght();
						}else {
							i=i+1;
							}
					}else {
						j=j+1;
						}
				}
			}
			if (num_click == 2) {
				i = 0;
				j = 0;
				System.out.println("HOLO");
				while(j<dotsx.getLenght() && i<dotsy.getLenght()) {
					System.out.println("HOLO2");
					if(x2 <= dotsx.getElement(j)+20 && x2 >= dotsx.getElement(j) ) {
						System.out.println("Entra 1");
						if(y2 <= dotsy.getElement(i)+20 && y2 >= dotsy.getElement(i) ) {
							System.out.println("Entra al 2");
							if(Math.abs(x - temp_dot.getElement(0)) <= 1 && Math.abs(y - temp_dot.getElement(1)) <= 1) {
								System.err.println("Entra al 3");
								if(x - temp_dot.getElement(0) != 0 || y - temp_dot.getElement(1) != 0){
									System.out.println("Entra al 4");
									temp_dot2.add(x);
									temp_dot2.add(y);
									Block(temp_dot,temp_dot2,0);
									if(figure == 0) {
										System.out.println("Entra al 5");
										Thread t = new Thread() {
											public void run() {
												temp_circ.setFill(Color.BLACK);
												line = new Line(temp_dot.getElement(0)*100+10,temp_dot.getElement(1)*100+10,temp_dot2.getElement(0)*100+10,temp_dot2.getElement(1)*100+10);
												line.setStrokeWidth(7);
												game.getChildren().addAll(line);
												num_click = 1;
												List<Integer> a = new List<Integer>();
												a.add(temp_dot.getElement(0));
												a.add(temp_dot.getElement(1));
												List<Integer> b = new List<Integer>();
												b.add(temp_dot2.getElement(0));
												b.add(temp_dot2.getElement(1));
												Linea temp_line = new Linea(a,b);
												Lineas.add(temp_line);
												temp_dot.delete(0);
												temp_dot.delete(0);
												temp_dot2.delete(0);
												temp_dot2.delete(0);
											}
										};
										t.start();

										System.out.println("Incrementa turn");
										Turn++;
//										onTurn();
										j = dotsx.getLenght();
									}else {
										System.out.println("Entra else 5");
										temp_dot2.delete(0);
										temp_dot2.delete(0);
										j = dotsx.getLenght();
									}
								}else {
									System.out.println("Entra else 4");
									j = dotsx.getLenght();
									}
							}else {
								System.out.println("Entra else 3");
								j = dotsx.getLenght();}
							
						}else {
							System.out.println("Entra else 2");
							i=i+1;}
					}else {
						System.out.println("Entra else 1");
						j=j+1;
					}
					
				}
			}
//			Envío info al server

			System.out.println("Envío al server");
			this.send("");
			while(!onTurn()) {
				System.out.println("Entra while Click");
				this.recieve();
				Thread.sleep(1000);
			}
		}
			
	}
			
	public int generateX(){
		int x = MouseInfo.getPointerInfo().getLocation().x-337;
		return x/100;
			}
	public int generateY(){
		int y = MouseInfo.getPointerInfo().getLocation().y-43;
		return y/100;
			}
	public void createcircles() {
//		Thread t = new Thread() {
//			public void run() {
				int i = 110;
				int j = 110;
				while (i < 710) {
					if (j < 610) {
						circ = new Circle();
						circ.setLayoutX(i);
						circ.setLayoutY(j);
						circ.setRadius(10);
						game.getChildren().addAll(circ);
						j = j+100;
					}else {
						i = i+100;
						j = 110;
					}
				}
//			}
//		};
//		t.start();
	}
	@SuppressWarnings("rawtypes")
	public static void Block(List Punto1, List Punto2, int pos) {
		if (pos >= Lineas.getLenght()) {
			figure= 0;
		}else if (pos<Lineas.getLenght()){
			Linea test= new Linea(Punto1,Punto2);
			Linea test2=(Linea) Lineas.getElement(pos);
			if ((test.punto1.getElement(0)==test2.punto1.getElement(0)) && (test.punto1.getElement(1)==test2.punto1.getElement(1)) && (test.punto2.getElement(0)==test2.punto2.getElement(0)) && (test.punto2.getElement(1)==test2.punto2.getElement(1)) || (test.punto1.getElement(0)==test2.punto2.getElement(0)) && (test.punto1.getElement(1)==test2.punto2.getElement(1)) && (test.punto2.getElement(0)==test2.punto1.getElement(0)) && (test.punto2.getElement(1)==test2.punto1.getElement(1))) {
				figure=1;
			
			}else {
				pos = pos + 1;
				Block(Punto1,Punto2,pos);
			}
		}
	}
	public boolean onTurn() {
		boolean turn2 = false;
		if (Turn % 2 == 0){
			onTurn.setFill(Color.RED);
			System.out.println(turn2);
		}else if(Turn % 2 == 1) {
			onTurn.setFill(Color.GREEN);
			turn2 = true;
			countt++;
		}
		System.out.println("Final onTurn()");
		return turn2;
		
	}
	
	public boolean notEnded() {
		if(count >= 10) {
			return false;
		}else {
			return true;
		}
	}
	
	public synchronized void send(String message) throws IOException {
		Thread t = new Thread() {
			public void run() {
				try {
					dos = new DataOutputStream(clientSocket.getOutputStream());
//					System.out.println(Lineas.getLenght());
					out = new WriteJsonFile(getName1(), (Lineas.getLenght() > 0) ? Lineas.getElement(Lineas.getLenght()-1) : null, 0, points, message);
					dos.writeUTF(out.getJson());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public synchronized void recieve() throws IOException, InterruptedException {
		dis = new DataInputStream(clientSocket.getInputStream());
		String iN = dis.readUTF();
		if(iN != null) {
			in = new ReadJsonFile(iN);
			System.out.println("Entra a recieve" + in.getMessage());
//			adversaryMove = in.getLine();
			if(in.getMessage().equals("1")) {
				System.out.println("1");
				this.playerNumber = 1;
				System.out.println(Turn);
			}else if(in.getMessage().equals("2")) {
				System.out.println("2");
				this.playerNumber = 2;
				this.countt++;
				System.out.println(Turn);
			}else if(in.getMessage().equals("wait")) {
//				this.message = "wait";
				AlertBox mes = new AlertBox();
				mes.show("Wait!", "Waiting for player 2");
			}else if(in.getMessage().equals(" ")) {
				name_game.setText("Player: "+ name.getText());
				window.close();
				wind_game.show();
				window.setTitle("Game");
				this.message = "begin";
				verify();
//				Thread t = new Thread() {
//					public void run() {
//						createcircles();
//					}
//				};
//				t.start();
				
				if(this.playerNumber == 1) {
					this.Turn++;
				}
				
			}else if(in.getLine() != null && in.getMessage() != "figure") {
				this.adversaryMove = in.getLine();
				this.drawOpponentLine(adversaryMove.punto1, adversaryMove.punto2);
				if(in.getMessage().equals("advFigure")) {
//					Bloquear figura
					adversaryPts = in.getOpPts();
					myPoints = in.getPts();
					System.out.println("Turn inicial");
					this.Turn++;
					return;
				}
				System.out.println("Turn final");
				this.Turn++;
			}else if(in.getMessage().equals("figure")) {
//				Bloquear
				adversaryPts = in.getOpPts();
				myPoints = in.getPts();
			}else if(in.getMessage().equals("")) {
				onTurn();
			}else if(in.getMessage().equals("yourTurn")) {
				Turn++;
				onTurn();
			}
		}
		
	}
	
	

	public void drawOpponentLine(List<Integer> Punto1, List<Integer> Punto2) {
		line = new Line(Punto1.getElement(0)*100+10,Punto1.getElement(1)*100+10,Punto2.getElement(0)*100+10,Punto2.getElement(1)*100+10);
		line.setStrokeWidth(7);
		game.getChildren().addAll(line);
		Linea opponentLine = new Linea(Punto1,Punto2);
		Lineas.add(opponentLine);
	}
	public void clean() {
		if (num_click == 2) {
			num_click = 1;
			temp_dot.delete(0);
			temp_dot.delete(0);
			temp_circ.setFill(Color.BLACK);
			
		}
		
	}
	public void clickButton(MouseEvent event) throws IOException, InterruptedException {
		if (event.getButton() == MouseButton.SECONDARY) {
			clean();
		}if (event.getButton() == MouseButton.PRIMARY) {
			Click(0,0);
		}
		
	}
	
//	public void blockFigure() {
//		int i=0;
//		int j=1;
//		List<Integer> Try = new List<Integer>();
//		List<Integer> num_x = new List<Integer>();
//		List<Integer> num_y = new List<Integer>();
//		while(i<Try.getLenght()) {
//			if(num_x.getElement(0) == null) {
//				num_x.add(Try.getElement(i),0);
//				num_x.add(Try.getElement(i),1);
//				i = i+2;
//			}else if(num_x.getElement(0)<Try.getElement(i)) {
//				num_x.delete(0);
//				num_x.add(Try.getElement(i));
//				i = i+2;
//			}else if(num_x.getElement(1)>Try.getElement(i)) {
//				num_x.delete(1);
//				num_x.add(Try.getElement(i),1);
//				i = i+2;}	
//		}
//		while(j<=Try.getLenght()) {
//			if(num_y.getElement(0) == null) {
//				num_y.add(Try.getElement(i),0);
//				num_y.add(Try.getElement(i),1);
//				i = i+2;
//			}else if(num_y.getElement(0)<Try.getElement(i)) {
//				num_y.delete(0);
//				num_y.add(Try.getElement(i));
//				i = i+2;
//			}else if(num_y.getElement(1)>Try.getElement(i)) {
//				num_y.delete(1);
//				num_y.add(Try.getElement(i),1);
//				i = i+2;}	
//		}
//	}
}




	
	
	
	
	
	
	
	
	
	
	
	
