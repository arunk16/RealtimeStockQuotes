package com.mycompany.webapp.stock.manager;

import java.io.Serializable;

import org.appfuse.service.GenericManager;

import com.mycompany.webapp.stock.domain.Stock;

public abstract interface StockManager extends GenericManager<Stock, Serializable>{

}
  