package com.NKS.application;
	
import java.awt.MouseInfo;

import com.NKS.Puntos.Linea;
import com.NKS.lists.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	public TextField name;
	public Label name_game;
	public Label dot;
	public Pane game,intro;
	public List<Integer> dotsx,dotsy;
	public Integer num_click;
	public List<Integer> temp_dot,temp_dot2;
	public Circle circ,temp_circ;
	public Line line;
	static List Lineas;
	static int figure;
	
	public void start(Stage primaryStage) {
		window = primaryStage;
		wind_game = new Stage();
//  	Images
		ImageView iv = new ImageView(new Image("file:D:\\Fotos\\Fondo/Dots.png"));
		ImageView iv2 = new ImageView(new Image("file:D:\\Fotos\\Fondo/dots.jpg"));
		ImageView play_btn = new ImageView(new Image("file:D:\\Fotos\\Fondo/play.png"));
		ImageView logo = new ImageView(new Image("file:D:\\Fotos\\Fondo/Logo.png"));
		iv2.setPreserveRatio(true);
//		Panes
		intro = new Pane();
		game = new Pane();
//		Labels and TextFields
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
// 		Button Config	
		btn1 = new Button("Play",play_btn);
		btn1.setOnAction(e -> {
			createcircles();
			verify();	
		});
		btn1.setContentDisplay(ContentDisplay.CENTER);
		btn1.setStyle("-fx-background-color: transparent; -fx-font-size: 30; -fx-font-color: white");
		
		btn2 = new Button("Menu");
		btn2.setOnAction(e -> {
			wind_game.close();
			window.show();
		});
//		Others
		num_click = 1;
		game.setOnMouseClicked(e -> Click(0,0));
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
//		Place
		game.getChildren().addAll(btn2,name_game);
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
	public void Click(int j, int i) {
			int x = generateX();
			int y = generateY();
			int x2 = MouseInfo.getPointerInfo().getLocation().x-333;
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
										line.setFill(Color.AQUA);
										game.getChildren().addAll(line);
										num_click = 1;
										List a = new List<>();
										a.add(temp_dot.getElement(0));
										a.add(temp_dot.getElement(1));
										List b = new List<>();
										b.add(temp_dot2.getElement(0));
										b.add(temp_dot2.getElement(1));
										Linea temp_line = new Linea(a,b);
										System.out.println("print de la linea hecha");
										temp_line.printLine();
										Lineas.add(temp_line);
										temp_dot.delete(0);
										temp_dot.delete(0);
										temp_dot2.delete(0);
										temp_dot2.delete(0);
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
	public static void Block(List Punto1, List Punto2, int pos) {
		if (pos >=Lineas.getLenght()) {
			figure= 0;
			System.out.println("print de el valor de figure"+figure);
		}else if (pos<Lineas.getLenght()){
			Linea test= new Linea(Punto1,Punto2);
			Linea test2=(Linea) Lineas.getElement(pos);
			System.out.println("print de test");
			test.printLine();
			System.out.println("print de test2");
			test2.printLine();
			if ((test.Punto1.getElement(0)==test2.Punto1.getElement(0)) && (test.Punto1.getElement(1)==test2.Punto1.getElement(1)) && (test.Punto2.getElement(0)==test2.Punto2.getElement(0)) && (test.Punto2.getElement(1)==test2.Punto2.getElement(1)) || (test.Punto1.getElement(0)==test2.Punto2.getElement(0)) && (test.Punto1.getElement(1)==test2.Punto2.getElement(1)) && (test.Punto2.getElement(0)==test2.Punto1.getElement(0)) && (test.Punto2.getElement(1)==test2.Punto1.getElement(1))) {
				figure=1;
				System.out.println("print de el valor de figure"+figure);
			
			}else {
				pos = pos + 1;
				Block(Punto1,Punto2,pos);
			}
		}
	}
	
	
}


	
	
	
	
	
	
	
	
	
	
	
	
