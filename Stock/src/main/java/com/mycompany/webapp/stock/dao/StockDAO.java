package com.mycompany.webapp.stock.dao;

import java.io.Serializable;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.mycompany.webapp.stock.domain.Stock;

public abstract interface StockDAO extends GenericDao<Stock, Serializable>{
	
	public abstract List<Stock> getStockQuotes();
	
	public abstract void saveStockQuote(Stock stock);
	

}
