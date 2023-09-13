package jtglover_CSCI201_Lab3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageTest {
	
	public static void main(String[] args) {
		for(int i = 0; i < 2; i++) {
			MessageQueue mq = new MessageQueue();
			Messenger m = new Messenger(mq);
			Subscriber s = new Subscriber(mq);
			ExecutorService executor = Executors.newFixedThreadPool(3);
			executor.execute(m);
			executor.execute(s);
			executor.shutdown();
			while(!executor.isTerminated()) {
				Thread.yield();
			}
			System.out.println("Completed");
		}
		
	}
}
