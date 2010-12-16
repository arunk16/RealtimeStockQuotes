package com.mycompany.webapp.stock.manager;

import java.io.Serializable;
import java.util.List;


import org.appfuse.service.impl.GenericManagerImpl;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

public class StockManagerImpl extends GenericManagerImpl<Stock, Long> implements StockManager{
	
	StockDAO stockDAO;
	
	public StockManagerImpl(StockDAO stockDAO){
		super(stockDAO);
		this.stockDAO = stockDAO;
	}

	
	public List<Stock> getStockQuotes() {		
		return stockDAO.getAll();
	}

	public Stock saveStockQuote(Stock stock) {
		List<Stock> list = stockDAO.findByTickerSymbol(stock.getStockTicker());
		if(list == null || list != null && list.size() == 0){
			//do nothing
		}else{
			//remove the old values from db.
			for(Stock stock1 : list){
				stockDAO.remove(stock1.getStockId());
			}
		}
		return stockDAO.save(stock);
		
	}

}
