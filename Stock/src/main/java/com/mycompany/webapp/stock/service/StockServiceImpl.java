package com.mycompany.webapp.stock.service;

import java.util.List;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.exceptions.StockServiceException;

public class StockServiceImpl implements StockService {

	public StockPriceVO getQuote(String tickerSymbol)
			throws InvalidTickerException, StockServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StockPriceVO> showAllQuotesFromDataStore() {
		// TODO Auto-generated method stub
		return null;
	}

}
