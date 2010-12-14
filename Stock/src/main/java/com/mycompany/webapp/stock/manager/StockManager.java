package com.mycompany.webapp.stock.manager;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.mycompany.webapp.stock.domain.Stock;

public abstract interface StockManager extends GenericManager<Stock, Long>{
	
	public abstract Stock saveStockQuote(Stock stock);
	
	public abstract List<Stock> getStockQuotes();
	
	//public abstract void setStockDAO();

}
  