# CSCI 1175 Final Project
## This is my Final Project for CSCI 1175 Industry Projects Coursework at Southwest Technical College. It is a multiplayer trivia game using socket networking.
### I built this software to show the skills I have learned during this course and because trivia games are fun!
### To run this program you will need the Server.java file and both Client and Client2.java files. Run the server file first and then the Client files in any order.
[Image of Final Project GUI]([Screen Shot 2022-09-22 at 12.01.12 PM.png](https://github.com/alexjmanley/CSCI_1175_Industry_Projects_Coursework/blob/main/Final%20Project/Screen%20Shot%202022-09-22%20at%2012.01.12%20PM.png))
### I am very proud of this snippet of coding showing the threads allowing for multiple clients to connect to one server. 
```
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
```
#### I am the only one who has contributed to this project so far! If you would like to help send me a message on github. 
