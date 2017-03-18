package com.mysage;

import java.io.IOException;

import com.mysage.services.StockService;
import com.mysage.stock.Stock;
import com.mysage.stock.StockDailyRecord;

public class App {
	public static void main(String[] args) throws IOException {

		clearScreen();		
		
		int menu=0;
		
		do {
			menu=AppMenu.SeeMenu();
			
			if (menu!=0) InsertItem();
			
		}while (menu!=0);
				
		System.out.println("Done");
	}
	
	
	public static void InsertItem() throws IOException {

		StockService stockService = new StockService();
		
		Stock st = stockService.findById(37);
	
		System.out.println(st);
		System.out.println("Details....");
				
		for(StockDailyRecord sdr:st.getStockDailyRecords()){
			System.out.println(sdr);
		}
		
		
/*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Code stock");
        String codeStock = br.readLine();
        
        System.out.print("Name stock");
        String nameStock = br.readLine();
  */      
        /*
        System.out.print("Enter Integer:");
        try{
            int i = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
        */
		
		/*
        StockDao stockDao = new StockDao();
        StockDailyRecordDao stockDailyRecordDao = new StockDailyRecordDao();
		*/
        
        /*
		Stock stock = new Stock();
		stock.setStockCode(codeStock);
		stock.setStockName(nameStock);
		
		
		
		stockDao.save(stock);
		
		stock.setStockCode(stock.getStockCode().concat("(U)"));
		
		stockDao.update(stock);
		*/
        
		
		/*
		List<Stock> l = stockDao.findAll();
		
		for(Stock s:l){
			System.out.println(s);
		}
		
		System.out.println("-----");
		
		System.out.println(stockDao.findById(37));
		
		List<StockDailyRecord> ls = stockDailyRecordDao.findAll(37);
		for(StockDailyRecord s:ls){
			System.out.println(s);
		}
		*/

/*
		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);

		session.save(stockDailyRecords);

		session.getTransaction().commit();		
		*/
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
}