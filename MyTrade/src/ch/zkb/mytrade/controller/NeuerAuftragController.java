package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.dao.NeuerAuftragDao;
@ManagedBean
@SessionScoped
public class NeuerAuftragController {
	
	private NeuerAuftragDao neuerAuftragDao;
	private int aktienId;
	public NeuerAuftragController() {
		neuerAuftragDao = new NeuerAuftragDao();
		neuerAuftragDao.loadAktienProperties((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("neuerAuftragAktienId"));
	}
	public NeuerAuftragDao getNeuerAuftragDao() {
		neuerAuftragDao.loadAktienProperties((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("neuerAuftragAktienId"));
		return neuerAuftragDao;
	}
	public void setNeuerAuftragDao(NeuerAuftragDao neuerAuftragDao) {
//		neuerAuftragDao.loadAktienProperties((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("neuerAuftragAktienId"));
		this.neuerAuftragDao = neuerAuftragDao;
	}
	public void neuerAuftrag(){
		this.neuerAuftragDao.neuerAuftrag();
	}
	
	
}
