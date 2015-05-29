package ch.zkb.mytrade.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.OffeneAuftraegeDao;
import ch.zkb.mytrade.model.AuftragModel;

@ManagedBean
@SessionScoped
public class OffeneAuftraegeController {
	
	private OffeneAuftraegeDao offeneAuftraege;
	public OffeneAuftraegeController() {
		offeneAuftraege = new OffeneAuftraegeDao();
	}

	public ArrayList<AuftragModel> loadAuftraege()
	{
		return offeneAuftraege.getOffeneAuftraege();
	}

}
