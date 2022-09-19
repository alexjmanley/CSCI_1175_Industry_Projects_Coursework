/* Author: Alex Manley 
 * Date: September 15, 2022
 * CSCI 1175 Final Project is a trivia game with a server and multiple clients,
 * this is the server run this before running Clients.  
 */

package finalproject;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {

	private TextArea ta = new TextArea();
	private int clientNo = 0; 
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		  primaryStage.setTitle("MultiThreadServer"); // Set the stage title
		    primaryStage.setScene(scene); // Place the scene in the stage
		    primaryStage.show(); // Display the stage

		    new Thread( () -> {
		      try {
		        // Create a server socket
		        ServerSocket serverSocket = new ServerSocket(7999);
		        ta.appendText("MultiThreadServer started at " 
		          + new Date() + '\n');
		    
		        while (true) {
		          // Listen for a new connection request
		          Socket socket = serverSocket.accept();
		    
		          // Increment clientNo
		          clientNo++;
		          
		          Platform.runLater( () -> {
		            // Display the client number
		            ta.appendText("Starting thread for client " + clientNo +
		              " at " + new Date() + '\n');
		            // Find the client's host name, and IP address
		            InetAddress inetAddress = socket.getInetAddress();
		            ta.appendText("Client " + clientNo + "'s host name is "
		              + inetAddress.getHostName() + "\n");
		            ta.appendText("Client " + clientNo + "'s IP Address is "
		              + inetAddress.getHostAddress() + "\n");
		          });
		          
		          new Thread(new HandleAClient(socket)).start(); 
		        }
		      }
		      catch(IOException ex) {
		    	  System.err.println(ex);
		      }
		    }).start(); 
		  }
	class HandleAClient implements Runnable {
		private Socket socket; 
		int numberCorrect = 0;
		String player = null;
		String[][] questionAnswer = {
				{"Sports", "What are the Olympic Weightlifting Lifts?", "Snatch, Clean and jerk", "Squat, Bench, Deadlift"},
				{"Music", "What genre of music is Daft Punks album Discovery?", "Dance", "Electronic"},
				{"Cinema", "Who starred in the 1999 movie the mummy?", "Brendan Fraser", "Tom Cruise"},
				{"History", "The Quote A date which will live in infamy is related to which WW2 battle?", "Pearl Harbor", "D-Day"},
				{"Science", "How many bones do sharks have?", "0", "87"},
		};
		DataInputStream input = null;
		DataOutputStream output = null;

		
		public HandleAClient(Socket socket) {
			this.socket = socket; 
		}
		
		public void run() {
			try {
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
				
				//get player name and send back info 
				player = input.readUTF();
				output.writeUTF(player + " Number correct: " + String.valueOf(numberCorrect));
				
				int i = 0;
				while (i != 5) {
					output.writeUTF(questionAnswer[i][0]);
					output.writeUTF(questionAnswer[i][1]);
					output.writeUTF(questionAnswer[i][2]);
					output.writeUTF(questionAnswer[i][3]);
					
					String answer = input.readUTF();
					if (answer.equalsIgnoreCase(questionAnswer[i][2])) {
						numberCorrect++;
					}
					
					output.writeUTF(player + " Number correct: " + String.valueOf(numberCorrect));
					i++;
				}
				
				output.writeUTF(player);
				output.flush();
				output.writeInt(numberCorrect);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
