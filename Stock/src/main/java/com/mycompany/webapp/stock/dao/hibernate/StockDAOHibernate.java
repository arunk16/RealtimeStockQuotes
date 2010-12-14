package com.mycompany.webapp.stock.dao.hibernate;


import java.io.Serializable;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

@Repository
public class StockDAOHibernate extends GenericDaoHibernate<Stock, Long> implements StockDAO{ 

	public StockDAOHibernate() {
		super(Stock.class);
		// TODO Auto-generated constructor stub
	}
//
//	public List<Stock> getStockQuotes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void saveStockQuote(Stock stock) {
//		// TODO Auto-generated method stub
//		getHibernateTemplate().saveOrUpdate(stock);
//	}	

}
