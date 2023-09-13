package vahidy.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
	BufferedReader br = null;
	Socket socket = null;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the server hostname:");
		String hostname = scan.nextLine();
		System.out.println("Enter the server port:");
		int port = scan.nextInt();
		scan.close();
		new Client(hostname, port);
	}
	public Client(String host, int port) {
			try {
			socket = new Socket(host, port);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				String line = br.readLine();
				if(line.equalsIgnoreCase("stop")) {
					break;
				} else {
					System.out.println(line);
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
