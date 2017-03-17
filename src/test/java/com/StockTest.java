package com;

import java.util.Date;
import java.util.List;

import com.mysage.service.StockService;
import com.mysage.stock.Stock;
import com.mysage.stock.StockDailyRecord;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StockTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public StockTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StockTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void testList() {
		StockService stockService = new StockService();

		List<Stock> l = stockService.findAll();

		assertTrue(l.size() > 0);
	}

	public void testStock() {
		StockService stockService = new StockService();

		Stock s = stockService.findById(37);

		System.out.println(s);
		System.out.println("Details....");

		for (StockDailyRecord sdr : s.getStockDailyRecords()) {
			System.out.println(sdr);
		}

		assertTrue(s != null);
	}

	public void testStockDetailAdd() {
		StockService stockService = new StockService();
		
		Stock s = stockService.findById(37);
		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(123000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(s);
		s.getStockDailyRecords().add(stockDailyRecords);
		
		stockService.addDetail(stockDailyRecords);
		
		assertTrue(stockDailyRecords != null);
	}

}
