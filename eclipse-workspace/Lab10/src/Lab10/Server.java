package Lab10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server {
	// to do --> data structure to hold server threads 
	ServerSocket socket = null;
	Vector<ServerThread> serverThreads = new Vector<ServerThread>();
	
	public Server(int port) {
		// to do --> implement your constructor
		try {
			socket = new ServerSocket(port);
			System.out.println("Bound to port " + port);
			serverThreads = new Vector<ServerThread>();
			while(true) {
				Socket s = socket.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
			}
		} catch (IOException ioe) {
			System.out.println("ioe in Server constructor: " + ioe.getMessage());
		}
	}
	
	public static void main(String [] args) {
		// to do --> implement your main()
		Server s = new Server(6789);
	}
}
