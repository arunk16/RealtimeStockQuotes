package com.mycompany.webapp.controller;

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
		
		log.info("<--- Submitting form");

        if (validator != null) { // validator is null during testing
            validator.validate(stockPriceVO, errors);

            if (errors.hasErrors()) {
                return getSuccessView();
            }
        }
        
        log.info("Fetching stock quote for "+ stockPriceVO.getStockTicker());
        
        StockPriceVO voFromYahoo = null;
        	
        try {
			voFromYahoo = stockService.getQuote(stockPriceVO.getStockTicker());
		} catch (InvalidTickerException e) {			
			e.printStackTrace();
			errors.rejectValue("stockTicker", "Invalid Stock ticker Symbol");
			
			return getSuccessView();
		}
		
		log.info("Got stock quote for "+ voFromYahoo.getStockTicker() + " with Quote price " + voFromYahoo.getQuote());        
        
        log.info("<--- About to return");
        
        
        return getSuccessView();
	}
	
	

}
