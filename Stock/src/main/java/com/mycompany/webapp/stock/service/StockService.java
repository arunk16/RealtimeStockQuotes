package com.mycompany.webapp.stock.service;

import java.util.List;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.exceptions.StockServiceException;

/**
 * Main interface to deal with real time stock quote calls 
 * from web layer as well as stock quotes from data store.
 * 
 * @author arun
 *
 */
public abstract interface StockService {
	
	StockPriceVO getQuote(String tickerSymbol) throws InvalidTickerException,StockServiceException;
	
	List<StockPriceVO> showAllQuotesFromDataStore(); 

}
