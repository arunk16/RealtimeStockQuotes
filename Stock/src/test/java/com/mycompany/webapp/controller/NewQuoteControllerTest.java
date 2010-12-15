package com.mycompany.webapp.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.mycompany.webapp.stock.common.vo.StockPriceVO;

public class NewQuoteControllerTest extends BaseControllerTestCase {
	
	private NewQuoteController c;
	private MockHttpServletRequest request;
	private StockPriceVO vo;
	
	
	public void testCancel() throws Exception {
        log.debug("testing cancel...");
        request = newPost("/newQuote.html");
        request.addParameter("cancel", "");

        BindingResult errors = new DataBinder(vo).getBindingResult();
        String view = c.onSubmit(vo, errors, request, new MockHttpServletResponse());

        assertEquals("redirect:stock.html", view);
    }

}
