package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.dao.NeuerAuftragDao;

/**
 * Controller zum Erstellen eines neuen Verkauf-Auftrages. Ruft die Methode auf,
 * um Aktien zu verkaufen.
 * 
 * @version 1.0
 * @author Robin.Frehner
 * @author Gabriel.Daw
 *
 */
@ManagedBean
@SessionScoped
public class NeuerAuftragController {

	private NeuerAuftragDao neuerAuftragDao;
	private int aktienId;
	private int anzahlAktien;

	public NeuerAuftragController() {
		neuerAuftragDao = new NeuerAuftragDao();
		neuerAuftragDao.loadAktienProperties((Integer) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("neuerAuftragAktienId"));
	}

	public NeuerAuftragDao getNeuerAuftragDao() {
		neuerAuftragDao.loadAktienProperties((Integer) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("neuerAuftragAktienId"));
		anzahlAktien = this.neuerAuftragDao.anzahlAktienImBesitz();
		return neuerAuftragDao;
	}

	public void setNeuerAuftragDao(NeuerAuftragDao neuerAuftragDao) {
		// neuerAuftragDao.loadAktienProperties((Integer)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("neuerAuftragAktienId"));
		this.neuerAuftragDao = neuerAuftragDao;
	}

	public String neuerAuftrag() {

		this.neuerAuftragDao.neuerAuftrag(anzahlAktien);

		return "mein_portfolio?faces-redirect=true";
	}

	public String zurueck() {
		return "mein_portfolio?faces-redirect=true";
	}

	public int getAnzahlAktien() {
		return anzahlAktien;
	}

	public void setAnzahlAktien(int anzahlAktien) {
		this.anzahlAktien = anzahlAktien;
	}

}
