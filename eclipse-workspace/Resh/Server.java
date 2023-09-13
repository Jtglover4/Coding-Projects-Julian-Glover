package CS201_A3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class Server extends Thread {

	public static HashMap<String, Double> stockPrice = new HashMap<>(); // broadcast after you finish
	public static HashMap<String, apiStock> stockInfo = new HashMap<>();
	private static List<Stock> globalSchedules = new ArrayList<Stock>();
	// private ArrayList<Trader> traders = new ArrayList<Trader>();
	private ArrayList<Trader> traders = new ArrayList<Trader>();
	private static int port;
	private static List<TraderThreads> tradersList = new ArrayList<>();
	public static long startTime;

	public Server(int port) {
		this.port = port;
		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		scheduleParse();
		tradersParse();
		
	}

	public void awaitingConnections() { // right
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			tradersList = Collections.synchronizedList(new ArrayList<>());
			System.out.println("Listening on port " + port + ".");
			System.out.println("Waiting for traders ...");

			while (tradersList.size() < traders.size()) {
				Socket socket = serverSocket.accept();
				System.out.println("Connection from" + socket.getInetAddress());
				TraderThreads newTrader = new TraderThreads(this, traders.get(tradersList.size()), socket);
				tradersList.add(newTrader);

				if (tradersList.size() < traders.size()) {

					int clientsRemaining = traders.size() - tradersList.size();
					String m1 = "";
					String m2 = "";
					if (clientsRemaining == 1) {
						m1 = clientsRemaining + " more trader is needed before trades can begin\n";
						m2 = "Waiting for " + clientsRemaining + " more trader(s) ...";
						System.out.println("Waiting for 1 more trader ...");
					} else {
						m1 = clientsRemaining + " more trader is needed before trades can begin\n";
						m2 = "Waiting for " + clientsRemaining + " more trader(s) ...";
						System.out.println("Waiting for " + clientsRemaining + " more traders ...");
					}
					String message = m1 + m2;
					// broadcastMessage(message, newTrader);
					defaultBroadcast(message);
					// System.out.println("Waiting for " + clientsRemaining + "more traders ...");

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void defaultBroadcast(String message) throws IOException {
		for(TraderThreads threads : tradersList) {
			threads.otherSendMessage(message);
		}
	}

	// broadcasts message to all the other threads
	public static void broadcastMessage(String message) throws IOException {
		if (message != null) {
			// System.out.println(message);
			// synchronized (traderThreads) {
			for (TraderThreads threads : tradersList) {
				// if (st != threads) { // if the thread is not my thread (not the server
				// thread) send message to it
				threads.sendMessage(message); // implemented in the ServerThread
				// }
			}
		}
	}


	private static void assignAwaitingTrades(ArrayList<Stock> waitingTrades) throws IOException, InterruptedException {
		//start at a time
		//passing through trades you had collected and send to trader thread
		//loop through trader thread and assign trades, and at the trader threat youre at send the trade to the trader thread, happens in server

		for (TraderThreads tradeThread : tradersList) {
			if (tradeThread.isAvailable() == true) {
				//how do you deal w status?
				double spent = 0.0;
				double made = 0.0;
				ArrayList<Stock> assignedTrades = new ArrayList<>();
				for (Stock trade : waitingTrades) {
					if (trade.getQuantity() > 0) {
						double cost_of_trade = trade.getQuantity() * stockPrice.get(trade.getTicker());

						if ((spent * cost_of_trade) < tradeThread.getTrader().getBudget() - tradeThread.getSpent()) {
							assignedTrades.add(trade);
							spent += cost_of_trade;
					// made += trade.getQuantity() * stockPrice.get(trade.getStockSymbol());
							}
					

					}		 
					else {
						double trade_profit = trade.getQuantity() *
						stockPrice.get(trade.getTicker());
						assignedTrades.add(trade);
						made += Math.abs(trade_profit);

					}
				}

			if ((assignedTrades.isEmpty())) {//change to if not empt
				
				
			} else {
				tradeThread.assignTrades(assignedTrades, spent, made);
				for(Stock stock : assignedTrades) {
					waitingTrades.remove(stock);
				}
			}
			
			} else {
				tradeThread.setAvailable();
			}
		}
	}
		
	// public void TradersFinish() {
	// for (TraderThreads tradeThread : tradersList) {
	// if (tradeThread.isEmpty()) {
	// tradeThread.sendMessage("You have finished your trades");
	// }
	// }
	// }

	// ServerSocket ss = new ServerSocket(port);
	public apiStock apiParse(String ticker) {
		apiStock parsedString = null;
		try {
			// String msymbol = "";
			String api = "cdgc4bqad3idup309gggcdgc4bqad3idup309gh0";

			URL myurl = new URL(String.format("https://finnhub.io/api/v1/quote?symbol=%s&token=%s", ticker, api));
			HttpURLConnection c = (HttpURLConnection) myurl.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-Type", "application/json");
			int responseCode = c.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));

			String inputLine;
			StringBuffer r = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				r.append(inputLine);
			}
			in.close();

			String st = r.toString();
			// System.out.println(st);
			// is this right?
			parsedString = new Gson().fromJson(st, apiStock.class);
			parsedString.setTicker(ticker);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// double cost = parsedString.getPriceNow();
		return parsedString;
	}

	


	public void scheduleParse() {
		// schedule shit

		Scanner csvfile = new Scanner(System.in);
		System.out.println("What is the path of the schedule file?");
		String csv = csvfile.nextLine();
		File csv_f = new File(csv);
		boolean ok_csv = false;

		String line = "";
		int count = 0;

		while (ok_csv == false) {
			if (!csv_f.exists()) {
				// print out doesn't exist
				System.out.println("CSV file does not exist.");
				csv = csvfile.nextLine();
				csv_f = new File(csv);
			} else {
				try {
					BufferedReader br = new BufferedReader(new FileReader(csv));

					while ((line = br.readLine()) != null) { // while next line is not null
						String[] values = line.split(",");
						Stock stockSchedule;
						apiStock stock = apiParse(values[1]);
						// hashmap
						stockInfo.put(stock.getTicker(), stock);
						stockPrice.put(stock.getTicker(), stock.getPriceNow());
						// stockPrice.put(values[1], 0.0);

						stockSchedule = new Stock(Integer.valueOf(values[0]), String.valueOf(values[1]),
								Integer.valueOf(values[2]));
						globalSchedules.add(stockSchedule);
						
						ok_csv = true;
						if(count ==0) {
							System.out.println("The file has been properly read.");
							count++;
							
						}
						
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void tradersParse() {
		// so now thread safe

		Scanner traderCSV = new Scanner(System.in);// csvfile
		System.out.println("What is the path of the traders file?");
		String tcsv = traderCSV.nextLine(); // csv
		File csvOfTrades = new File(tcsv);// csv_f
		boolean ok_trades = false;
		String line = "";
		boolean ok_csv = false;
		int count = 0;
		while (ok_trades == false) {
			if (!csvOfTrades.exists()) {
				// print out doesn't exist
				System.out.println("CSV file does not exist.");
				tcsv = traderCSV.nextLine();
				csvOfTrades = new File(tcsv);
			} else {
				try {
					BufferedReader br = new BufferedReader(new FileReader(tcsv));

					while ((line = br.readLine()) != null) { // while next line is not null
						String[] values = line.split(",");


						Trader trader = new Trader(Integer.valueOf(values[0]), Double.valueOf(values[1]));

						traders.add(trader);
					}
					System.out.println("The file has been properly read.");
					ok_trades = true;
					if(count ==0) {
						System.out.println("The file has been properly read.");
						count++;
						
					}
					

				} catch (FileNotFoundException e) {
					System.out.println("The csv file" + csvOfTrades + "could not be found");
					e.printStackTrace();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		Server server = new Server(3456);// do I have too many scanners
		
		ArrayList<Stock> waitingTrades = new ArrayList<Stock>();
		int tradeIndex = 0;
		
	
		server.awaitingConnections();
		broadcastMessage("Starting service.");
		System.out.println("Starting service.");
		startTime = System.currentTimeMillis();
		while(tradeIndex < globalSchedules.size()) {
			while(tradeIndex < globalSchedules.size() && ((System.currentTimeMillis() - startTime)/ 1000) >= globalSchedules.get(tradeIndex).getTime()) {
				waitingTrades.add(globalSchedules.get(tradeIndex));
				tradeIndex++;
			}
			if(!waitingTrades.isEmpty()) {
				assignAwaitingTrades(waitingTrades);
				
			}
		}
		// incomplete trades here
		if(!waitingTrades.isEmpty()) {
			for(int i = 0; i < waitingTrades.size(); i++) {
				Stock trades = waitingTrades.get(i);
				Date date = new Date();
				SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				String str = simple.format(new Date());
				String s = trades.getTime() + " " + trades.getTicker() + ", " + Math.abs(trades.getQuantity()) + ", " + str;
				if(i<waitingTrades.size() -1) {
					s += ", ";
				}
				s += ")";
				broadcastMessage("Incomplete Trades: " + s);
				
			}
			
		}
		else {
			broadcastMessage("Incomplete Trades: NONE");
		}
		
		//print out gained
		
		for(TraderThreads t : tradersList) {
			t.otherSendMessage(String.format("Total profit earned: $%.2f\n", t.getProfit()));
		}
		
		
		defaultBroadcast("Processing complete.");
		defaultBroadcast("stop");
		System.out.println("Processing complete.");


	}
	
	

}
