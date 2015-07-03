package ch.zkb.mytrade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.dao.NeueAktieDao;
import ch.zkb.mytrade.dao.NeuerBenutzerDao;
import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.NeuerBenutzerModel;
import ch.zkb.mytrade.model.Rolle;
import ch.zkb.mytrade.model.UserModel;

@ManagedBean
@SessionScoped
public class NeueAktieController {

	private AktieModel neueAktie = new AktieModel();
	private NeueAktieDao neueAktieDao;

	public NeueAktieController() {
		neueAktieDao = new NeueAktieDao();
	}

	public AktieModel getNeueAktie() {
		return neueAktie;
	}

	public void setNeueAktie(AktieModel neueAktie) {
		this.neueAktie = neueAktie;
	}

	public NeueAktieDao getNeueAktieDao() {
		return neueAktieDao;
	}

	public void setNeueAktieDao(NeueAktieDao neueAktieDao) {
		this.neueAktieDao = neueAktieDao;
	}

	public void setUser(UserModel user) {
		UserModel currUser = (UserModel) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentUser");
		currUser.getUser_id();
		neueAktie.setUser_id(currUser.getUser_id());
	}

	public String zurueck() {
		return "admin?faces-redirect=true";
	}

	public String aktieSpeichern() {
		neueAktieDao.neueAktie(neueAktie);
		return "bestaetigung_aktie?faces-redirect=true";

	}
	public String weiter() {
		return "admin?faces-redirect=true";
	}

}
