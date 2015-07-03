package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.OffeneAuftraegeDao;

/**
 * Controller für das Anzeigen der Offenen Aufträge. Auf dem Button Wird
 * entweder Stornieren oder Kaufen angezeigt, je nach Besitzer der Aktie. Diese
 * Aktion soll dann auch entsprechend ausgeführt.
 * 
 * @version 1.0
 * @author Gwendolin.Maggion
 *
 */
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
