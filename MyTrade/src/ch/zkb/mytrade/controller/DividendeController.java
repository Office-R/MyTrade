package ch.zkb.mytrade.controller;

import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.DividendeDao;

@javax.faces.bean.ManagedBean
@SessionScoped
public class DividendeController {
	
	private DividendeDao dividendeDao;
	public DividendeController() {
		System.out.println("DividendeController:");
		dividendeDao = new DividendeDao();
	}
	public DividendeDao getDividendeDao() {
		return dividendeDao;
	}
	public void setDividendeDao(DividendeDao dividendeDao) {
		this.dividendeDao = dividendeDao;
	}
	
}
