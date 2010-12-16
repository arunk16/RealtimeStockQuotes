package com.mycompany.webapp.stock.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.mycompany.webapp.stock.domain.Stock;

public abstract interface StockDAO extends GenericDao<Stock, Long>{
	
	public abstract List<Stock> findByTickerSymbol(String ticker);
	

}
