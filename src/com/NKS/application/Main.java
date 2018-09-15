package com.NKS.application;
	
import java.awt.MouseInfo;
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
import javafx.stage.Stage;


public class Main extends Application{
	public Scene scene,scene2;
	public Stage window;
	public Button btn1,btn2;
	public TextField name;
	public Label name_game;
	public Label dot;
	public Pane game,intro;
	public List<Integer> dotsx,dotsy;
	public Integer num_click;
	public List<Integer> temp_dot,temp_dot2;
	
	public void start(Stage primaryStage) {
		window = primaryStage;
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
			verify();	
		});
		btn1.setContentDisplay(ContentDisplay.CENTER);
		btn1.setStyle("-fx-background-color: transparent; -fx-font-size: 30; -fx-font-color: white");
		
		btn2 = new Button("Menu");
		btn2.setOnAction(e -> {
			window.setScene(scene);
			window.setTitle("Menu");
		});
//		Others
		num_click = 1;
		game.setOnMouseClicked(e -> Click(0,0));
		intro.setStyle("-fx-background-color: cornsilk");
		temp_dot = new List<Integer>();
		temp_dot2 = new List<Integer>();
//		Dots
		ImageView dot = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot2 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot3 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot4 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot5 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot6 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot7 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot8 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		ImageView dot9 = new ImageView(new Image("file:D:\\Fotos\\Fondo/punto.png"));
		dotsx = new List<Integer>();
		dotsx.add(100);
		dotsx.add(200);
		dotsx.add(300);
		dotsy = new List<Integer>();
		dotsy.add(100);
		dotsy.add(200);
		dotsy.add(300);
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
		dot.setLayoutX(100);
		dot.setLayoutY(100);
		dot2.setLayoutX(200);
		dot2.setLayoutY(100);
		dot3.setLayoutX(300);
		dot3.setLayoutY(100);
		dot4.setLayoutX(100);
		dot4.setLayoutY(200);
		dot5.setLayoutX(200);
		dot5.setLayoutY(200);
		dot6.setLayoutX(300);
		dot6.setLayoutY(200);
		dot7.setLayoutX(100);
		dot7.setLayoutY(300);
		dot8.setLayoutX(200);
		dot8.setLayoutY(300);
		dot9.setLayoutX(300);
		dot9.setLayoutY(300);
//		Place
		game.getChildren().addAll(btn2,name_game,dot,dot2,dot3,dot4,dot5,dot6,dot7,dot8,dot9);
		intro.getChildren().addAll(iv2,logo,btn1,player);
//		Window config
		window.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Menu");
		window.show();
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void verify() {
		if(name.getText().equals("")){
		AlertBox.show("Error", "You must introduce a name");
		}else {
		name_game.setText("Player: "+ name.getText());
		window.setScene(scene2);
		window.setTitle("Game");
		}
	}
	public void Click(int j, int i) {
			int x = MouseInfo.getPointerInfo().getLocation().x-428;
			int y = MouseInfo.getPointerInfo().getLocation().y-89;
			if (num_click == 1) {
				while(j<dotsx.getLenght() && i<dotsy.getLenght()) {
					if(x <= dotsx.getElement(j)+20 && x >= dotsx.getElement(j) ) {
						if(y <= dotsy.getElement(i)+20 && y >= dotsy.getElement(i) ) {
							temp_dot.add(x);
							temp_dot.add(y);
							System.out.println("hey hey hey i am the first click");
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
					if(x <= dotsx.getElement(j)+20 && x >= dotsx.getElement(j) ) {
						if(y <= dotsy.getElement(i)+20 && y >= dotsy.getElement(i) ) {
							if(x - temp_dot.getElement(0) < 120 && y - temp_dot.getElement(1) < 120) {
								System.out.println(temp_dot.getElement(1)); 
								System.out.println(temp_dot.getElement(0));
								System.out.println(y);
								System.out.println(x);
								num_click = 1;
								temp_dot.delete(0);
								temp_dot.delete(0);
								j = dotsx.getLenght();
							}else {
								j = dotsx.getLenght();}
							
						}else {
							i=i+1;}
					}else {
						j=j+1;}
				}
			}
	}
			
	public void createDots(){
		for (int column=150; column<(900); column = column + 150) {
			for (int row=50; row<(900); row = row + 150 ) {
				//List tempDot = new List<>();
				//tempDot.add(column);
				//tempDot.add(row);
			}
		}
		//dots.printL();
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
