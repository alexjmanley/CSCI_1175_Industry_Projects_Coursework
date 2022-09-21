package finalproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode; 

public class Client extends Application {
	// text areas to display in client 
	TextArea instructions = new TextArea(); 
	TextArea score = new TextArea(); 
	TextArea answers = new TextArea(); 
	DataInputStream input = null; 
	DataOutputStream output = null; 
	
	public void start(Stage primaryStage) {
		answers.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				try { 
					score.clear();
					output.flush();
					output.writeUTF(answers.getText().trim());
					output.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				answers.clear();
				instructions.clear();
			}
		});
		
		new Thread(() ->{
			try {
				Socket socket = new Socket("localhost", 7999);
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
				
				instructions.appendText("Enter your name in the bottom Text Area");
				score.appendText(input.readUTF());
				
				//While loop to start game after name is entered. 
				int i = 0; 
				while (i != 5) {
					instructions.appendText("Topic " + input.readUTF() + '\n');
					instructions.appendText(input.readUTF() + '\n');
					instructions.appendText("1. " + input.readUTF() + '\n');
					instructions.appendText("2. " + input.readUTF() + '\n');
					instructions.appendText("Write your answer in the text below \n Example(Clean, and jerk 'Hit Enter Key')");
					score.appendText(input.readUTF());
					i++;
				}
				score.clear();
				instructions.appendText("The Winner is: " + input.readUTF() + " \nNumber Correct: "  + input.readInt());
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
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