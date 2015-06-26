package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.OffeneAuftraegeDao;

@ManagedBean
@SessionScoped
public class OffeneAuftraegeController {
	
	private OffeneAuftraegeDao offeneAuftraege;
	
	public OffeneAuftraegeController() {
		offeneAuftraege = new OffeneAuftraegeDao();
	}

	public OffeneAuftraegeDao getOffeneAuftraege() {
		offeneAuftraege.getOffeneAuftraege();
		return offeneAuftraege;
	}

	public void setOffeneAuftraege(OffeneAuftraegeDao offeneAuftraege) {
		this.offeneAuftraege = offeneAuftraege;
	}

}
