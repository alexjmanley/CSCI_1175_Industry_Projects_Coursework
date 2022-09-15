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
	int numberCorrect = 0;
	String player = null;
	String[][] questionAnswer = {
			{"Sports", "What are the Olympic Weightlifting Lifts?", "Snatch, Clean and jerk", "Squat, Bench, Deadlift"},
			{"Music", "What genre of music is Daft Punks album Discovery?", "Dance", "Electronic"},
			{"Cinema", "Who starred in the 1999 movie the mummy?", "Brendan Fraser", "Tom Cruise"},
			{"History", "The Quote A date which will live in infamy is related to which WW2 battle?", "Pearl Harbor", "D-Day"},
			{"Science", "How many bones do sharks have?", "0", "87"},
	};

	public static void main(String[] args) {
		//created server socket
		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(8001);
				System.out.println("Server started at " + new Date() + '\n');
				
			} catch (IOException e) {
				e.printStackTrace();
			
			} 
		}).start(); 
		
	}

}
