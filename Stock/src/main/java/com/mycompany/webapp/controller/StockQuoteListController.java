package com.mycompany.webapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.stock.service.StockService;


@Controller
@RequestMapping("/stockQuotesInDB.*")
public class StockQuoteListController {
	
	private transient final Log log = LogFactory.getLog(StockQuoteListController.class);
	
	private StockService stockService;
	
	@Autowired
	public void StockService(StockService stockService){
		this.stockService = stockService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        return new ModelAndView("/quotesList", "quotesList", stockService.showAllQuotesFromDataStore());
    }

}
