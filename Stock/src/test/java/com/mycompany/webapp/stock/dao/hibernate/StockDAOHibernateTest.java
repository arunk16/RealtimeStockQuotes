package com.mycompany.webapp.stock.dao.hibernate;



import java.util.Date;

import org.apache.log4j.Logger;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

/**
 * Thsi test is bit different from others as it will create data in Db and verify them and
 * clears the data after test is finished.
 * 
 * @author arun
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
@Transactional
public class StockDAOHibernateTest {
	
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
		//get the stock using PK
		Stock returnObj = stockDao.get(stock.getStockId());
		
		assertNotNull(returnObj);
		assertSame(returnObj, stock);
		
		
		
	}
	
	

}
