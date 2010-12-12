/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.webapp.stock.service;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.exceptions.StockServiceException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soundrapandian_a
 */
public class YahooStockPriceFetcher extends StockPriceFetcherFactory{

    private Logger log = Logger.getLogger(YahooStockPriceFetcher.class.getName());

    private String yahooUrl = "http://finance.yahoo.com/d/quotes.csv?";
    public YahooStockPriceFetcher(){

    }
    
    public StockPriceVO findStockPrice(String tickerSymbol) throws InvalidTickerException,StockServiceException {
        if(tickerSymbol == null)
          return null;
        String rawOutput = null;
        try {
            //String yahooUrl = "http://finance.yahoo.com/d/quotes.csv?s=MSFT&f=sb2b3";
            rawOutput =  getPriceFromYahoo(tickerSymbol);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new StockServiceException("Problem in getting quote for " +
                    tickerSymbol + "\n Detailed Error message " + ex.getLocalizedMessage());
        }
        return convertRawYahooCSVResultToStockVO(rawOutput);
        
    }

    /**
     * This is the helper method to talk to yahoo to find stock ticker price.
     *
     * @param tickerSymbol
     * @return RAW yahoo csv result
     * @throws Exception
     */
    private String getPriceFromYahoo(String tickerSymbol)throws Exception{
        // Construct data
        String data = URLEncoder.encode("s", "UTF-8") + "=" + URLEncoder.encode(tickerSymbol, "UTF-8");
        data += "&" + URLEncoder.encode("f", "UTF-8") + "=" + URLEncoder.encode("sb2b3", "UTF-8");

        final URL url = new URL(yahooUrl+data);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        //conn.setDoInput(true);
        //conn.setRequestMethod("POST");
        //conn.setRequestProperty("Content-Type", "/";
        //conn.setRequestProperty("SOAPAction", GET_REQUEST);

        final Writer writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(data);
        writer.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            // Process line...
            buffer.append(line);
        }
        writer.close();
        rd.close();
        log.info(buffer.toString());
        return buffer.toString();
    }

    private StockPriceVO convertRawYahooCSVResultToStockVO(String rawResultOutput) throws InvalidTickerException{
        StockPriceVO vo = new StockPriceVO();
        String[] items = rawResultOutput.split(",");
        //items[ticker symbol,ask price, bid price] is the expected Array
        if(items != null){

            if(items[1].equals("N/A") & items[1].equals("N/A") ){
                //invalid stock ticker ,throw invalid stock exception
                throw new InvalidTickerException();
            }  

            vo.setStockTicker(items[0].substring(1, items[0].length()-1)); //strip off " char at the start & end
            vo.setAskPrice(new Double(items[1]));
            vo.setBidPrice(new Double(items[2]));
            vo.setQuote(prepareQuote(vo.getAskPrice(),vo.getBidPrice()));
        }
        log.info("Quote : "+ vo.getQuote());
        return vo;
    }

    private Double prepareQuote(Double askPrice,Double bidPrice){
        return (bidPrice + (askPrice - bidPrice)/2);
    }    

}
