package com.mycompany.webapp.stock.dao.hibernate;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.apache.log4j.Logger;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;




public class StockDAOHibernateTest extends BaseDaoTestCase{
	
	private Logger log = Logger.getLogger(StockDAOHibernateTest.class);	
	
	@Autowired
	private StockDAO stockDao;
	
	@Test
	public void testSaveAndGet(){
		final String tickerSymbol  = "CHHH";
		final Double quote = new Double(17.80);
		Stock stock = new Stock();
		stock.setStockTicker(tickerSymbol);
		stock.setCreatedDate(new Date());
		stock.setLastUpdatedDate(new Date());
		stock.setQuotePrice(quote);
		//save the stock quote
		stock = stockDao.save(stock);		
		
		Stock returnObj = stockDao.get(stock.getStockId());
		
		assertNotNull(returnObj);
		assertSame(returnObj, stock);
		
		
		
	}
	
	

}
