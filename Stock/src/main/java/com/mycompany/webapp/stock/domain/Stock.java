/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.webapp.stock.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import org.appfuse.model.BaseObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author soundrapandian_a
 */
@Entity
public class Stock extends BaseObject{

    private Long stockId;
    private String stockTicker;    
    private Date createdDate;
    private Date lastUpdatedDate;
    private Double quotePrice;   

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getStockId() {
        return stockId; 
    }

    public void setStockId(Long id) {
        this.stockId = id;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="last_updated_date")
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Column(name="stock_ticker", length=10)
    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }    
    
    @Column(name="quote_price", length=10)
    public Double getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(Double quotePrice) {
		this.quotePrice = quotePrice;
	}

	@Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
