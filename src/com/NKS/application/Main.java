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
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
	public Scene scene,scene2;
	public Stage window,wind_game;
	public Button btn1,btn2;
	private TextField name;
	
	public String getName1() {
		System.out.println(name.getText());
		return name.getText();
	}

	public Label name_game,onTurn_label;
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
	public int Turn;
	private int points = 0;
	
//	Sockets
	
	private Socket clientSocket;
	private DataOutputStream dos;
	@SuppressWarnings("unused")
	private DataInputStream dis;
	
//	Escritura y lectura de Json
	
	private WriteJsonFile out;
	private ReadJsonFile in;
	
//	
	@SuppressWarnings("unused")
	private Linea adversaryMove;
	@SuppressWarnings("unused")
	private int adversaryPts;
	private int count;
	

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
		onTurn_label = new Label("Turn:");
		onTurn_label.setStyle("-fx-font-size: 20");
		name = new TextField();
		Label player_name = new Label("Player Name");
		name_game = new Label("Player: ");
		name_game.setStyle("-fx-font-size: 20");
		VBox player = new VBox();
		player.getChildren().addAll(player_name,name);
		player.setSpacing(15);

//		Scenes
		scene2 = new Scene(game,700,600);
		scene = new Scene(intro,500,500);
		
		
//		Inicializaci�n del socket
		clientSocket = new Socket("localhost", 9000);
		
// 		Button Config	
		btn1 = new Button("Play",play_btn);
		btn1.setOnAction(e -> {
			onTurn();
			createcircles();
			verify();	
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
		Turn = 0;
		onTurn = new Circle();
		onTurn.setRadius(8);
		game.setOnMouseClicked(e -> { 
			if(Turn == 0) {
			try {
				Click(0,0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
				
			try {
				clickButton(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}});
		
		
		intro.setStyle("-fx-background-color: cornsilk");
		temp_dot = new List<Integer>();
		temp_dot2 = new List<Integer>();
		Lineas = new List<>();
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
		onTurn_label.setLayoutX(500);
		onTurn_label.setLayoutY(30);
		onTurn.setLayoutX(570);
		onTurn.setLayoutY(45);
//		Place
		game.getChildren().addAll(btn2,name_game,onTurn_label,onTurn);
		intro.getChildren().addAll(iv2,logo,btn1,player);
//		Window config
		window.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Menu");
		window.show();
		wind_game.initStyle(StageStyle.UNDECORATED);
		wind_game.setScene(scene2);
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void verify() {
		if(name.getText().equals("")){
		AlertBox.show("Error", "You must introduce a name");
		}else {
		name_game.setText("Player: "+ name.getText());
		window.close();
		wind_game.show();
		window.setTitle("Game");
		}
	}
	public void Click(int j, int i) throws IOException {
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
							i=i+1;}
					}else {
						j=j+1;}
				}
			}
			if (num_click == 2) {
				while(j<dotsx.getLenght() && i<dotsy.getLenght()) {
					if(x2 <= dotsx.getElement(j)+20 && x2 >= dotsx.getElement(j) ) {
						if(y2 <= dotsy.getElement(i)+20 && y2 >= dotsy.getElement(i) ) {
							if(Math.abs(x - temp_dot.getElement(0)) <= 1 && Math.abs(y - temp_dot.getElement(1)) <= 1) {
								if(x - temp_dot.getElement(0) != 0 || y - temp_dot.getElement(1) != 0){
									temp_dot2.add(x);
									temp_dot2.add(y);
									Block(temp_dot,temp_dot2,0);
									if(figure == 0) {
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
										onTurn();
										j = dotsx.getLenght();	
									}else {
										temp_dot2.delete(0);
										temp_dot2.delete(0);
										j = dotsx.getLenght();
									}
								}else {
									j = dotsx.getLenght();}
							}else {
								j = dotsx.getLenght();}
							
						}else {
							i=i+1;}
					}else {
						j=j+1;}
				}
			}
			count++;
//			Env�o info al server
			this.send(null);
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
	}
	@SuppressWarnings("rawtypes")
	public static void Block(List Punto1, List Punto2, int pos) {
		if (pos >=Lineas.getLenght()) {
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
	public void onTurn() {
		if (Turn == 0){
			onTurn.setFill(Color.GREEN);
		}else if(Turn == 1) {
			onTurn.setFill(Color.RED);
		}
	}
	
	public boolean notEnded() {
		if(count >= 1) {
			return false;
		}else {
			return true;
		}
	}
	
	public void send(String message) throws IOException {
//		Thread t = new Thread() {
//			public void run() {
//				try {
		dos = new DataOutputStream(clientSocket.getOutputStream());
		out = new WriteJsonFile(getName1(), Lineas.getElement(Lineas.getLenght()-1), 0, this.points, message);
		dos.writeUTF(out.getJson());
//				} catch (UnknownHostException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		t.start();
	}
	
	public void recieve() throws IOException {
		dis = new DataInputStream(clientSocket.getInputStream());
		in = new ReadJsonFile(dis.readUTF());
		adversaryMove = in.getLine();
		adversaryPts = in.getOpPts();
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
		}else {
			
		}
		
	}
	public void clickButton(MouseEvent event) throws IOException {
		if (event.getButton() == MouseButton.SECONDARY) {
			clean();
		}if (event.getButton() == MouseButton.PRIMARY) {
			Click(0,0);
		}
		
	}
}



	
	
	
	
	
	
	
	
	
	
	
	
