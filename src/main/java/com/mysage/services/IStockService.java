package com.mysage.services;

import java.util.List;

import com.mysage.stock.Stock;
import com.mysage.stock.StockDailyRecord;

public interface IStockService {

	public void save(Stock entity);	
	public void update(Stock entity);	
	public Stock findById(Integer id);	
	public void delete(Stock entity);	
	public List<Stock> findAll();	
	
	public void addDetail(StockDailyRecord entity);
	
}
