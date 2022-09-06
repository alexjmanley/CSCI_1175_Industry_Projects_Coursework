package exercise33_1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

	new Thread(() -> {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);   
			Platform.runLater(() -> 
			ta.appendText("Server started at " + new Date() + '\n'));
			
	Socket socket = serverSocket.accept(); 
	
	DataInputStream in = new DataInputStream(socket.getInputStream());
	DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
	
	while (true) {
		double annualInterestRate = in.readDouble(); 
		int numberOfYears = in.readInt(); 
		double loanAmount = in.readDouble(); 
		
		Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount); 
		
		out.writeDouble(loan.getMonthlyPayment());
		out.writeDouble(loan.getTotalPayment()); 
		
		Platform.runLater(() -> {
			ta.appendText("Annual Interest received from client: " + annualInterestRate + '\n'); 
			ta.appendText("Number of Years received from client: " + numberOfYears + '\n'); 
			ta.appendText("Loan amount received from client: " + loanAmount + '\n'); 
			ta.appendText("Monthly Payment is " + loan.getMonthlyPayment() + '\n');
			ta.appendText("Total Payment is " + loan.getTotalPayment() + '\n'); 
		});
	}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}).start();
	
	ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
