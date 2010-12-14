package com.mycompany.webapp.stock.manager;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

/**
 * This is the test to save data to db using manager & dao and retrieve them with the help of 
 * spring context and after verification, the db will be wiped by SpringJUnit4ClassRunner.
 * 
 * @author arun
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
@Transactional
public class StockManagerImplWithRealSpringContextTest {
	
	 @SuppressWarnings("unused")
	 private Logger logger = Logger.getLogger(StockManagerImplWithRealSpringContextTest.class);
	 
	 
	 private StockDAO stockDao;
	 
	 public void setStockDao(StockDAO stockDao){
		 this.stockDao =  stockDao; 
	 }
	 
	 
	 @Test
	 public void testSaveAndGet(){
		 Stock stock1 = new Stock();
		 stock1.setStockTicker("MSFT");
		 stock1.setQuotePrice(new Double(12.45));
		 stock1.setCreatedDate(new Date());
		 stock1.setLastUpdatedDate(new Date());
		 
		 //save the stock quote first
		 Stock model = stockDao.save(stock1);
		 
		 //get the stock quote from DB 
		 Stock stockFromDB = stockDao.get(model.getStockId());
		 
		 assertEquals(model, stockFromDB);
		 
		 
	 }
	 


}
