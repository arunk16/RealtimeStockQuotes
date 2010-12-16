package com.mycompany.webapp.stock.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

/**
 * This test is to prove the business logic is working as expected using easymock.
 * 
 * @author arun
 *
 */

public class StockManagerImplTest {
	
	private StockManagerImpl stockManagerImpl;
	private StockDAO stockDAO;
	
	@Before
	public void setUp(){
		stockDAO = createMock(StockDAO.class);
		stockManagerImpl = new StockManagerImpl(stockDAO);
	}
	
	 @After
	public void tearDown() {
	        // called after each test.
	        verify(stockDAO);
	}
	 
	@Test
	public void testGetStockQuotes(){		
		List<Stock> stockQuotes = new ArrayList<Stock>();
		
		Stock stock1 = new Stock();
		stock1.setStockTicker("MSFT");
		stock1.setQuotePrice(new Double(12.45));
		
		Stock stock2 = new Stock();
		stock2.setStockTicker("RHT");
		stock2.setQuotePrice(new Double(12.43));
		
		stockQuotes.add(stock1);
		stockQuotes.add(stock2);
		
		expect(stockDAO.getAll()).andReturn(stockQuotes);
		replay(stockDAO);
		
		List<Stock> returnList = stockManagerImpl.getStockQuotes();		
		assertNotNull(returnList);
		
		assertSame(stockQuotes, returnList);	
	}
	
	@Test
	public void testSaveStockQuote(){
		String ticker = "MSFT";
		Stock stock1 = new Stock();
		stock1.setStockTicker(ticker);
		stock1.setQuotePrice(new Double(12.45));
		
		expect(stockDAO.findByTickerSymbol(ticker)).andReturn(new ArrayList<Stock>());
		
		expect(stockDAO.save(stock1)).andReturn(stock1);
		replay(stockDAO);
		
		Stock stock = stockManagerImpl.saveStockQuote(stock1);
		assertNotNull(stock);
		
	}
	
	

}
