package unisa.dse.a2.students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name)
	{
		this.name = name;
	    this.brokers = new DSEListGeneric<>();
	    this.announcements = new DSEListGeneric<>();
	    this.companies = new HashMap<>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company)
	{
		if (company != null && !companies.containsKey(company.getCode())) {
	        companies.put(company.getCode(), company);
	        return true;
	    }
	    return false;
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker)
	{
		if (broker != null && !brokers.contains(broker)) {
			brokers.add(broker);
			return true;
        }
		return false;
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound()
	{
		int successfulTrades = 0; // Initialize the count of successful trades
		   
	    List<Trade> tradesToProcess = new ArrayList<>();  // List to store trades to be processed

	    //Processing each Broker's trades
	    
	    for (StockBroker broker : brokers) { 
	       
	        Trade trade = broker.getNextTrade();  // Get the next trade from the broker's queue	  
	       
	        
	        if (trade != null) {  
	        	tradesToProcess.add(trade); // Add the trade to the list if it's not null
	        }
	    }
	 // Process each collected trade
	    for (Trade trade : tradesToProcess) {
	    	ListedCompany company = companies.get(companyCode);  // Get the ListedCompany object from the map	            
            int quantity = trade.getShareQuantity(); // Get the quantity of shares to be traded           
            int currentPrice = company.getCurrentPrice(); // Get the current price of the company's shares
            StockBroker broker = trade.getStockBroker(); // Get the broker who placed the trade

            // Create the announcement string for the trade
            String announcement = "Trade: " + quantity + " " + companyCode + " @ " + currentPrice + " via " + broker.getName();	            
            announcements.add(announcement); // Add the announcement to the announcements list	          
            successfulTrades++;  // Increment the count of successful trades
            
            
            // Process the trade and update the company's current price
            company.setCurrentPrice(company.getCurrentPrice() + quantity / 100);
            if (company.getCurrentPrice() < 1) {
                company.setCurrentPrice(1); // Ensure the price does not go below 1
            }
	    }
	 // Return the total number of successful trades
	    return successfulTrades; 
	}
	
	public int runCommandLineExchange(Scanner sc)
	{
		return 0;
	}
}
