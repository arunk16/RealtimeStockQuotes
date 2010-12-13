package com.mycompany.webapp.stock.dao.hibernate;


import java.io.Serializable;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.mycompany.webapp.stock.dao.StockDAO;
import com.mycompany.webapp.stock.domain.Stock;

public class StockDAOHibernate extends GenericDaoHibernate<Stock, Serializable> implements StockDAO{ 

	public StockDAOHibernate(Class<Stock> persistentClass) {
		super(persistentClass);
		// TODO Auto-generated constructor stub
	}

	public List<Stock> getStockQuotes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveStockQuote(Stock stock) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(stock);
	}	

}
