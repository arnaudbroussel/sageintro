package com.mysage.dao;

import java.util.List;

import com.mysage.stock.StockDailyRecord;

public class StockDailyRecordDao extends EntityDao<StockDailyRecord> {

	public void save(StockDailyRecord entity) {
		session.save(entity);
	}

	public void update(StockDailyRecord entity) {
		session.update(entity);
	}

	public StockDailyRecord findById(Integer id) {
		return (StockDailyRecord) session.get(StockDailyRecord.class, id);
	}

	public void delete(StockDailyRecord entity) {
		session.delete(entity);
	}

	public List<StockDailyRecord> findAll() {
		@SuppressWarnings("unchecked")
		List<StockDailyRecord> l = (List<StockDailyRecord>) session.createQuery("from StockDailyRecord").list();
		return l;
	}

}
