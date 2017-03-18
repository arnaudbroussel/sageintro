package com.mysage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mysage.services.StockService;
import com.mysage.stock.Stock;
import com.mysage.stock.StockDailyRecord;

public class AppMenu {

	public static int SeeMenu() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Wellcome to Customer & Invoices Application");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n\n");
		System.out.println("1) Create customers");
		System.out.println("2) Customers list");
		System.out.println("3) Create invoice");
		System.out.println("4) Pay invoice");
		System.out.println("5) Create invoice");
		System.out.println("5) Change invoice status");
		System.out.println("0) ...exit");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Choice : ");
		try {
			int menuOption = Integer.parseInt(br.readLine());
			return menuOption;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	public static void InsertItem() throws IOException {

		StockService stockService = new StockService();

		Stock st = stockService.findById(37);

		System.out.println(st);
		System.out.println("Details....");

		for (StockDailyRecord sdr : st.getStockDailyRecords()) {
			System.out.println(sdr);
		}

		/*
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in)); System.out.print("Code stock"); String
		 * codeStock = br.readLine();
		 * 
		 * System.out.print("Name stock"); String nameStock = br.readLine();
		 */
		/*
		 * System.out.print("Enter Integer:"); try{ int i =
		 * Integer.parseInt(br.readLine()); }catch(NumberFormatException nfe){
		 * System.err.println("Invalid Format!"); }
		 */

		/*
		 * StockDao stockDao = new StockDao(); StockDailyRecordDao
		 * stockDailyRecordDao = new StockDailyRecordDao();
		 */

		/*
		 * Stock stock = new Stock(); stock.setStockCode(codeStock);
		 * stock.setStockName(nameStock);
		 * 
		 * 
		 * 
		 * stockDao.save(stock);
		 * 
		 * stock.setStockCode(stock.getStockCode().concat("(U)"));
		 * 
		 * stockDao.update(stock);
		 */

		/*
		 * List<Stock> l = stockDao.findAll();
		 * 
		 * for(Stock s:l){ System.out.println(s); }
		 * 
		 * System.out.println("-----");
		 * 
		 * System.out.println(stockDao.findById(37));
		 * 
		 * List<StockDailyRecord> ls = stockDailyRecordDao.findAll(37);
		 * for(StockDailyRecord s:ls){ System.out.println(s); }
		 */

		/*
		 * StockDailyRecord stockDailyRecords = new StockDailyRecord();
		 * stockDailyRecords.setPriceOpen(new Float("1.2"));
		 * stockDailyRecords.setPriceClose(new Float("1.1"));
		 * stockDailyRecords.setPriceChange(new Float("10.0"));
		 * stockDailyRecords.setVolume(3000000L); stockDailyRecords.setDate(new
		 * Date());
		 * 
		 * stockDailyRecords.setStock(stock);
		 * stock.getStockDailyRecords().add(stockDailyRecords);
		 * 
		 * session.save(stockDailyRecords);
		 * 
		 * session.getTransaction().commit();
		 */
	}
	
}
