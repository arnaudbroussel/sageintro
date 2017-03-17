package com.mysage.service;

import java.util.List;

import com.mysage.dao.StockDailyRecordDao;
import com.mysage.dao.StockDao;
import com.mysage.stock.Stock;
import com.mysage.stock.StockDailyRecord;

public class StockService implements IStockService {

	private static StockDao stockDao;
	private static StockDailyRecordDao stockDailyRecordDao;

	public StockService() {
		stockDao = new StockDao();
		stockDailyRecordDao = new StockDailyRecordDao();
	}

	public void save(Stock entity) {
		stockDao.openCurrentSessionwithTransaction();
		stockDao.save(entity);
		stockDao.closeCurrentSessionwithTransaction();
	}

	public void update(Stock entity) {
		stockDao.openCurrentSessionwithTransaction();
		stockDao.update(entity);
		stockDao.closeCurrentSessionwithTransaction();
	}

	public Stock findById(Integer id) {
		return stockDao.findById(id);
	}

	public void delete(Stock entity) {
		stockDao.openCurrentSessionwithTransaction();
		stockDao.delete(entity);
		stockDao.closeCurrentSessionwithTransaction();
	}

	public List<Stock> findAll() {
		return stockDao.findAll();
	}

	public void addDetail(StockDailyRecord entity) {
		stockDailyRecordDao.openCurrentSessionwithTransaction();
		stockDailyRecordDao.save(entity);
		stockDailyRecordDao.closeCurrentSessionwithTransaction();
	}

}
