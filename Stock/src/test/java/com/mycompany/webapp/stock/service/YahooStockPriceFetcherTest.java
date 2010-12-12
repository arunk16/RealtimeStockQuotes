/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.webapp.stock.service;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import junit.framework.TestCase;

/**
 *
 * @author soundrapandian_a
 */
public class YahooStockPriceFetcherTest extends TestCase {

    private StockPriceFetcherFactory factory = null;

    public void setUp(){
        factory = new YahooStockPriceFetcher();
    }

    public void testfindStockPriceForNull()throws Exception {
        assertNull(factory.findStockPrice(null));
    }

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
    
    /**
     * Test to prove that wrong stock tickers will get invalidTickerException.
     * May be simpler in junit4, could have done this using annotation @Test(expected=InvalidTickerException.class)
     * @throws Exception
     */
    
    public void testFindStockPriceForInvalidTickerSymbol()throws Exception {
       final String ticker = "BLAH";
        try {
			StockPriceVO vo = factory.findStockPrice(ticker);
		} catch (Exception e) {			
			assertTrue(e instanceof InvalidTickerException );
		}
        
    }

}
