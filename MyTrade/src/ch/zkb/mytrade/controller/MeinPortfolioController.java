package ch.zkb.mytrade.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		if(null != portfolioDao){
			portfolioDao.getStocks();
		}
		return portfolioDao;
	}

	public void setPortfolioDao(MeinPortfolioDao portfolioDao) {
		this.portfolioDao = portfolioDao;
	}

	public String verkaufen(int id)
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("neuerAuftragAktienId", id);
		return "neuer_auftrag?faces-redirect=true";
	}

	
}
