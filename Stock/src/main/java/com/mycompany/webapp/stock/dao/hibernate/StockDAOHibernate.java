package com.mycompany.webapp.stock.dao.hibernate;


import java.io.Serializable;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

@Repository("StockDao")
public class StockDAOHibernate extends GenericDaoHibernate<Stock, Long> implements StockDAO{ 

	public StockDAOHibernate() {
		super(Stock.class);		
	}

	public List<Stock> findByTickerSymbol(String ticker) {
		return  getHibernateTemplate().find("from Stock where stockTicker=?", ticker);

	}
}
