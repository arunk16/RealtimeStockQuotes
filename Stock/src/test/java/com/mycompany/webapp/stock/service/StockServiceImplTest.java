package com.mycompany.webapp.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.domain.Stock;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.manager.StockManager;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * Stock application main interface implementation under test here.
 * 
 * @author arun
 *
 */

public class StockServiceImplTest {
	
	private StockServiceImpl stockServiceImpl;
	
	private StockManager stockManager;
		
	@Before
	public void setUp(){
		stockManager = createMock(StockManager.class);
		stockServiceImpl = new StockServiceImpl();
		stockServiceImpl.setStockManager(stockManager);
		
	}
	
	
	public void tearDown(){
		verify(stockManager);
	}
	
	
	@Test
	public void testGetQuoteForValidtickerSymbol() throws Exception{
		final String ticker = "MSFT";		
		
		expect(stockManager.saveStockQuote((Stock)anyObject())).andReturn(new Stock() );
		replay(stockManager);
		
		StockPriceVO vo = stockServiceImpl.getQuote(ticker);
		assertNotNull(vo);
		
		assertEquals(ticker, vo.getStockTicker());	
		
		//finish method
		verify(stockManager);
	}
	
	@Test(expected=InvalidTickerException.class)
	public void testGetQuoteForInvalidTickerSymbol() throws Exception{
		final String ticker = "BLAH";	
		stockServiceImpl.getQuote(ticker);
	}
	
	@Test(expected=InvalidTickerException.class)
	public void testGetQuoteForNullTickerSymbol() throws Exception{
		final String ticker = null;	
		stockServiceImpl.getQuote(ticker);
	}
	
	@Test
	public void testShowAllQuotesFromDBStore(){
		expect(stockManager.getStockQuotes()).andReturn(new ArrayList<Stock>());
		replay(stockManager);
		
		List<StockPriceVO> returnList = stockServiceImpl.showAllQuotesFromDataStore();
		assertNotNull(returnList);		
		
		//finish method
		verify(stockManager);		
	}

}
