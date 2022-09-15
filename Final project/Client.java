package finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea; 

public class Client extends Application {
	// text areas to display in client 
	TextArea instructions = new TextArea(); 
	TextArea score = new TextArea(); 
	TextArea answers = new TextArea(); 
	
	public void start(Stage primaryStage) {
		//set TextAreas to not editable
		instructions.setEditable(false);
		score.setEditable(false);
		
		// making border pane and setting stage
		BorderPane pane = new BorderPane(); 
		pane.setTop(instructions);
		pane.setCenter(score);
		pane.setBottom(answers);
		
		Scene scene = new Scene(pane, 300, 400);
		primaryStage.setTitle("Player");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}
