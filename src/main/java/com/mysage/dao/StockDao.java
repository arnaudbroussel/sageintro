package com.mysage.dao;

import java.util.List;

import com.mysage.stock.Stock;

public class StockDao extends EntityDao<Stock> {

	public void save(Stock entity) {
		session.save(entity);
	}

	public void update(Stock entity) {
		session.update(entity);
	}

	public Stock findById(Integer id) {
		return (Stock) session.get(Stock.class, id);
	}

	public void delete(Stock entity) {
		session.delete(entity);
	}

	public List<Stock> findAll() {
		@SuppressWarnings("unchecked")
		List<Stock> l = (List<Stock>) session.createQuery("from Stock").list();
		return l;
	}


}
