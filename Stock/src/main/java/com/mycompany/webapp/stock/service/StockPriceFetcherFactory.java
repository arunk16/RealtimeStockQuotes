/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.webapp.stock.service;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.exceptions.StockServiceException;

/**
 *
 * @author soundrapandian_a
 */
public class StockPriceFetcherFactory {

    public StockPriceFetcherFactory(){

    }

    public StockPriceVO findStockPrice(String tickerSymbol)throws InvalidTickerException,StockServiceException {
        return new StockPriceVO();
    }


}
