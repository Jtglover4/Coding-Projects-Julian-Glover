package CS201_A3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{

	BufferedReader buffer = null;
	Socket s1 = null;
	
	public Client(String hostname, int port) {
		try {
			s1 = new Socket(hostname, port);
			buffer = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			this.start();
				
				
			
		}catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the server hostname: ");
		String host = sc.nextLine();
		System.out.println("Enter the server port: ");
		int port = sc.nextInt();
		sc.close();
		new Client(host, port);
		
		
	}
	
	public void run() {
		try {
			while(true) {
				String l = buffer.readLine();
				if(l != null) {
					if(l.equalsIgnoreCase("stop")) {
						s1.close();
						break;
			
					}
					else {
						System.out.println(l);
					}
				}
			}
		}catch(IOException io) {
			io.printStackTrace();
		}
	
	

	}
}
