/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.webapp.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;

/**
 *Core Yahoo service interaction calls are tested here.
 *
 * @author soundrapandian_a
 */
public class YahooStockPriceFetcherTest {

    private StockPriceFetcherFactory factory = null;
    
    @Before
    public void setUp(){
        factory = new YahooStockPriceFetcher();
    }
    
    @Test(expected=InvalidTickerException.class)
    public void testfindStockPriceForNull()throws Exception {
        factory.findStockPrice(null);
    }
    
    @Test
    public void testFindStockPriceForValidTickerSymbol()throws Exception {
       final String ticker = "MSFT";
        StockPriceVO vo = factory.findStockPrice(ticker);
        assertNotNull(vo);

        //check the return content in StockPriceVO
        assertEquals(ticker, vo.getStockTicker());
        assertNotNull(vo.getAskPrice());
        assertNotNull(vo.getBidPrice());
        assertNotNull(vo.getQuote());     
    }
    
    @Test(expected=InvalidTickerException.class)
    public void testFindStockPriceForInvalidTickerSymbol()throws Exception {
       final String ticker = "BLAH";       
	   StockPriceVO vo = factory.findStockPrice(ticker);	
        
    }

}
