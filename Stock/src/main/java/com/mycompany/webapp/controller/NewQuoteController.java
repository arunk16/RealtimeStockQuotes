package com.mycompany.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;
import com.mycompany.webapp.stock.exceptions.InvalidTickerException;
import com.mycompany.webapp.stock.exceptions.StockServiceException;
import com.mycompany.webapp.stock.service.StockService;
/**
 * Controller for new stock quotes.
 * 
 * @author arun
 *
 */

@Controller
@RequestMapping("/newQuote.*")
public class NewQuoteController extends BaseFormController {
	
	private Logger log = Logger.getLogger(NewQuoteController.class);
	
	private StockService stockService;

	public NewQuoteController() {
		setCancelView("redirect:stock.html");
        setSuccessView("redirect:newQuote.html");
	}
	
	@Autowired
	public void StockService(StockService stockService){
		this.stockService = stockService;
	}
	
	
	@ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public StockPriceVO showForm() {
        return new StockPriceVO();
    }
	
	
	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(StockPriceVO stockPriceVO, BindingResult errors, HttpServletRequest request,
    		HttpServletResponse response)
            throws Exception {
		
		if (request.getParameter("cancel") != null) {
			return getCancelView();
		}
		
		log.info("<--- Submitting form");

        if (validator != null) { // validator is null during testing
            validator.validate(stockPriceVO, errors);

            if (errors.hasErrors()) {
                return getSuccessView();
            }
        }
        
        Locale locale = request.getLocale();
        
        log.info("Fetching stock quote for "+ stockPriceVO.getStockTicker());
        
        StockPriceVO voFromYahoo = null;
        	
        try {
			voFromYahoo = stockService.getQuote(stockPriceVO.getStockTicker());
		} catch (InvalidTickerException e) {			
			saveMessage(request, getText("stock.invalid.ticker", 
					stockPriceVO.getStockTicker() == null || (stockPriceVO.getStockTicker() != null && stockPriceVO.getStockTicker().trim()
							.equals("")) ? stockPriceVO.getStockTicker() : stockPriceVO.getStockTicker()  + " is ", locale));			
			return getSuccessView();
		} catch (StockServiceException e) {			
			saveMessage(request, getText("stock.yahoo.connection.problem", locale));			
			return getSuccessView();
		}	
		
				
		log.info("Got stock quote for "+ voFromYahoo.getStockTicker() + " with Quote price " + voFromYahoo.getQuote());        
        
		saveMessage(request, getText("stock.fetched.quote", voFromYahoo.getStockTicker() + " : "+ voFromYahoo.getQuote(), locale));
		
        log.info("<--- About to return");        
        
        return getSuccessView();
	}
	
	

}
