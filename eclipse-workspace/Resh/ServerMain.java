package vahidy.assignment3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMain {
	public static long startTime;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		List<Trader> traders = new ArrayList<Trader>();
		ConcurrentHashMap<String, Double> priceMap = new ConcurrentHashMap<>();
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Stock> stockMap = new HashMap<>();
		
		// validate all the files
		if(schedules.isEmpty()) {
			// Schedule File
			print("What is the path of the schedule file?");
			String scheduleFileName = scanner.nextLine();
		
			File scheduleFile = new File(scheduleFileName);	
			// verify the scheduler file
			while(!scheduleFile.exists()){
				print("That file does not exist");
				scheduleFileName = scanner.nextLine();
				scheduleFile = new File(scheduleFileName);
			}
			Scanner csvScanner = null;
			try {
				csvScanner = new Scanner(scheduleFile);
				String temp = "";
				String[] tempArr;
				Schedule schedule;
				while(csvScanner.hasNext()) {
					temp = csvScanner.next();
					tempArr = temp.split(",");
					schedule = new Schedule(Integer.valueOf(tempArr[0]), tempArr[1], Integer.valueOf(tempArr[2]));
					schedules.add(schedule);
					Stock stock = Util.loadStock(schedule.getTicker());
					priceMap.put(stock.getTicker(), stock.getCurrentPrice());
					stockMap.put(stock.getTicker(), stock);
					
				}
				print("The schedule file has been properly read.");
			} catch (Exception e) {
				print("The file is not formatted correctly");
			} finally {
				if(csvScanner != null) {
					csvScanner.close();
				}
			}
		}
		
		if(traders.isEmpty()) {
			// Schedule File
			print("What is the path of the traders file?");
			String traderFileName = scanner.nextLine();
		
			File traderFile = new File(traderFileName);	
			// verify the scheduler file
			while(!traderFile.exists()){
				print("That file does not exist");
				traderFileName = scanner.nextLine();
				traderFile = new File(traderFileName);
			}
			Scanner csvScanner = null;
			try {
				csvScanner = new Scanner(traderFile);
				String temp = "";
				String[] tempArr;
				Trader trader;
				while(csvScanner.hasNext()) {
					temp = csvScanner.next();
					tempArr = temp.split(",");
					trader = new Trader(Integer.valueOf(tempArr[0]), Integer.valueOf(tempArr[1]));
					traders.add(trader);
				}
				print("The traders file has been properly read.");
			} catch (Exception e) {
				print("The file is not formatted correctly");
			} finally {
				if(csvScanner != null) {
					csvScanner.close();
				}
			}
		}
		
		Server server = new Server();
		server.setTraders(traders);
		server.setPriceMap(priceMap);
		server.createConnection();
		startTime = System.currentTimeMillis();
		server.startService(schedules, stockMap);
		server.closeClients();
		scanner.close();
	}
	
	public static void print(String message) {
		System.out.println(message);
	}
}
