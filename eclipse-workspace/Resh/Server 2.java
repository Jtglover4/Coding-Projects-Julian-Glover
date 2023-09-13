package vahidy.assignment3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
	private List<ClientHandler> clients;
	private List<Trader> traders;
	private ConcurrentHashMap<String, Double> priceMap;
	private ServerSocket serverListener;
	
	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}
	
	public void setPriceMap(ConcurrentHashMap<String, Double> priceMap) {
		this.priceMap = priceMap;
	}
	
	public ConcurrentHashMap<String, Double> getPriceMap(){
		return this.priceMap;
	}
	
	public void assignWaiting(ArrayList<Schedule> waitingTrades) throws IOException, InterruptedException {
		for(ClientHandler client : clients) {
			if(client.isOpen()) {
				double toSpend = 0.0, toMake = 0.0;
				List<Schedule> toTrade = new ArrayList<>();
				for(Schedule trade : waitingTrades) {
					// trade is going to be< a purchase
					double cost = Math.abs(trade.getAmount() * priceMap.get(trade.getTicker()));
					if(trade.getAmount() > 0) {
						
						
						if((toSpend + cost) < (client.getTrader().getInitialBalance() - client.getSpent())) {
							toTrade.add(trade);
							toSpend += cost;
						}
					} 
					// trade is going to be a sale
					else {
						
						toTrade.add(trade);
						toMake += cost;
					}
				}
				
				if(!toTrade.isEmpty()) {
					client.assignTrades(toTrade, toSpend, toMake);
					
					for(Schedule trade : toTrade) {
						waitingTrades.remove(trade);
					}
				}
			} else {
				client.setOpen();
			}
		}
	}
	
	public void createConnection() throws IOException {
	//		Create the server listener
		serverListener = new ServerSocket(3456);
		print("Listening on port 3456.");
		clients = Collections.synchronizedList(new ArrayList<>());
		int joined = 0;
		print("Waiting for traders...");
		while(joined < traders.size()) {
			Socket client = serverListener.accept();
			print("Connection from: " + client.getInetAddress());
			ClientHandler clientHandler = new ClientHandler(client, this, traders.get(joined));
			clients.add(clientHandler);
			joined++;
			if(joined < traders.size()) { 
				int remaining = traders.size() - joined;
				String message;
				if(remaining == 1) {
					message = remaining + " more trader is needed before the service can begin.";
				} else {
					message = remaining + " more traders are needed before the service can begin.";
				}
				broadcastMessage(message);
				broadcastMessage("Waiting...");
				print("Waiting for " + remaining + " more trader(s)...");
			}
		}
	}
	
	public boolean allFinished() {
		for(ClientHandler client : clients) {
			if(client.isFinished()) return true;
		}
		
		return false;
	}
	
	public void startService(ArrayList<Schedule> schedules, HashMap<String, Stock> stockMap) throws IOException, InterruptedException {
		broadcastMessage("All traders have arrived!");
		broadcastMessage("Starting service.");
		print("Starting service.");
		
		ArrayList<Schedule> waitingTrades = new ArrayList<Schedule>();
//		print("" + schedules.size());
		int counter = 0;
		while(counter < schedules.size()) {
			
			while (counter < schedules.size() && ((System.currentTimeMillis()-ServerMain.startTime) / 1000) >= schedules.get(counter).getStartTime()){
				waitingTrades.add(schedules.get(counter));
//				print("Adding to waiting trades.");
				counter++;
			};
			if(!waitingTrades.isEmpty()) {
//				print("Now Assigning");
//				print("" + waitingTrades.get(0).getTicker());
				assignWaiting(waitingTrades);
			}
			if(allFinished()) {
				break;
			}
		}
		if(!waitingTrades.isEmpty()) {
			// incomplete trades
			String message = "(";
			for(int i = 0; i < waitingTrades.size(); i++) {
				Schedule trade = waitingTrades.get(i);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				String str = sdf.format(date).toString();
				message += trade.getStartTime() + ", " + trade.getTicker() + ", " + Math.abs(trade.getAmount()) + ", " + str;
				if(i < waitingTrades.size() - 1) {
					message += ", "; 
				}
			}
			message += ")";
			broadcastMessage(Util.printOut("Incomplete Trades: " + message));
		} else {
			broadcastMessage(Util.printOut("Incomplete Trades: NONE"));
		}
		for(ClientHandler client : clients) {
			client.sendMessage(client.getProfit());
		}
		broadcastMessage("Processing complete.");
		print("Processing complete.");
	}
	
	public void broadcastMessage(String message) throws IOException {
		// go through each client and send a message through pw
		for(ClientHandler s : clients) {
			s.sendMessage(message);
		}
	}
	public void print(String message) {
		System.out.println(message);
	}

	public void closeClients() throws IOException {
		broadcastMessage("stop");
		serverListener.close();
	}
}
