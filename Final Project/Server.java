/* Author: Alex Manley 
 * Date: September 15, 2022
 * CSCI 1175 Final Project is a trivia game with a server and multiple clients,
 * this is the server run this before running Clients.  
 */

package finalproject;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
	// creating int and questionAnswer string (Category, question, answer, wrong answer)
	static int numberCorrect = 0;
	static String player = null;
	static String[][] questionAnswer = {
			{"Sports", "What are the Olympic Weightlifting Lifts?", "Snatch, Clean and jerk", "Squat, Bench, Deadlift"},
			{"Music", "What genre of music is Daft Punks album Discovery?", "Dance", "Electronic"},
			{"Cinema", "Who starred in the 1999 movie the mummy?", "Brendan Fraser", "Tom Cruise"},
			{"History", "The Quote A date which will live in infamy is related to which WW2 battle?", "Pearl Harbor", "D-Day"},
			{"Science", "How many bones do sharks have?", "0", "87"},
	};
	static DataInputStream input = null;
	static DataOutputStream output = null;

	public static void main(String[] args) {
		//created server socket
		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(7999);
				System.out.println("Server started at " + new Date() + '\n');
				
				Socket socket = serverSocket.accept(); 
				
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
				System.out.println(numberCorrect);
				output.writeInt(numberCorrect);
				
			} catch (IOException e) {
				e.printStackTrace();
			
			} 
		}).start(); 
		
	}

}