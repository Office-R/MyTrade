package ch.zkb.mytrade.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.MeinPortfolioDao;

@ManagedBean
@SessionScoped
public class MeinPortfolioController {

	private MeinPortfolioDao portfolioDao;
	
	public MeinPortfolioController() {
		System.out.println("meinPortFolioController");
		portfolioDao = new MeinPortfolioDao();
		portfolioDao.getStocks();
	}
	
	
	public MeinPortfolioDao getPortfolioDao() {
		return portfolioDao;
	}


	public void setPortfolioDao(MeinPortfolioDao portfolioDao) {
		this.portfolioDao = portfolioDao;
	}


	
}
