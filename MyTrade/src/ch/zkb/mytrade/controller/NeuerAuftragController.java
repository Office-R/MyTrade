package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.NeuerAuftragDao;
@ManagedBean
@SessionScoped
public class NeuerAuftragController {
	
	private NeuerAuftragDao neuerAuftragDao;
	public NeuerAuftragController() {
		neuerAuftragDao = new NeuerAuftragDao();
	}
	public NeuerAuftragDao getNeuerAuftragDao() {
		return neuerAuftragDao;
	}
	public void setNeuerAuftragDao(NeuerAuftragDao neuerAuftragDao) {
		this.neuerAuftragDao = neuerAuftragDao;
	}
	
	
	
}
