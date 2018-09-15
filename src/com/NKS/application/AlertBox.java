package com.NKS.application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class AlertBox {


	public static void show(String tittle, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(tittle);
		window.setMinHeight(100);
		window.setMinWidth(250);
		
		Label label = new Label(message);
		Button btn1 = new Button("Ok");
		btn1.setOnAction(e -> window.close());
		
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label,btn1);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout,100,100);
		window.setScene(scene);
		window.showAndWait();
		

	
	}

}
