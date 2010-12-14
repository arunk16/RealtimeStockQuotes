package com.mycompany.webapp.stock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.domain.Stock;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.exceptions.StockServiceException;
import com.mycompany.webapp.stock.manager.StockManager;

/**
 * Main interface implementation to deal with real time stock quote calls 
 * from web layer as well as retrieval of stock quotes from data store.
 * 
 * @author arun
 *
 */

public class StockServiceImpl implements StockService {
	
	/**
	 * Factory method object always returns StockPriceVO object with stock quote related data.
	 */
	private final StockPriceFetcherFactory stockPriceFetcher = new YahooStockPriceFetcher();
	
	/**
	 *  Stock manager spring bean facade gives CRUD operations on Stock table in DB via StockDao.
	 */
	private StockManager stockManager;
	
	public void setStockManager(StockManager stockManager){
		this.stockManager = stockManager;
	}
	
	
	/**
	 * Method gets the latest stock quote from Yahoo for the user as well as 
	 * stores or updates the stock quote in DB.
	 * @param tickerSymbol
	 * @return  StockPriceVO  
	 */
	public StockPriceVO getQuote(String tickerSymbol)
			throws InvalidTickerException, StockServiceException {
		//step 1 : get the latest price from Yahoo
		StockPriceVO vo = stockPriceFetcher.findStockPrice(tickerSymbol);
		
		//step 2 : before returning to web layer, save or update the ticker symbol in our DB with latest quote price.
		Stock stock = convertVOToStockEntity(vo);
		stockManager.saveStockQuote(stock);
		
		//send vo to web page
		return vo;
	}
	
	/**
	 * This method returns all saved stock quotes in DB.
	 * @return List of StockPriceVOs
	 */
	public List<StockPriceVO> showAllQuotesFromDataStore() {
		List<Stock> stockList  = stockManager.getStockQuotes();
		List<StockPriceVO> stockVoList = convertStocksToVoList(stockList);
		return stockVoList;
	}
	
	/**
	 * Helper method to convert stockpriceVO output from yahoo stock service to Stock entity.
	 * 
	 * @param StockPriceVO
	 * @return Stock
	 */
	private Stock convertVOToStockEntity(StockPriceVO vo){
		Stock stock = new Stock();
		stock.setStockTicker(vo.getStockTicker());
		stock.setQuotePrice(vo.getQuote());
		stock.setLastUpdatedDate(new Date());
		stock.setCreatedDate(new Date());
		return stock;
	}
	
	
	/**
	 * Helper method to convert stock objects from DB to VO objects 
	 * to help avoid sending entity objects directly to web page.
	 * 
	 * @param stockList 
	 * @return List<StockPriceVO>
	 */
	private List<StockPriceVO> convertStocksToVoList(List<Stock> stockList){
		List<StockPriceVO> stockVoList  = new ArrayList<StockPriceVO>();
		for(Stock stock : stockList){
			StockPriceVO vo = new StockPriceVO();
			vo.setCreatedDate(stock.getCreatedDate());
			vo.setLastUpdatedDate(stock.getLastUpdatedDate());
			vo.setQuote(stock.getQuotePrice());
			vo.setStockTicker(stock.getStockTicker());
			
			stockVoList.add(vo);
		}
		
		return stockVoList;
	}

}
