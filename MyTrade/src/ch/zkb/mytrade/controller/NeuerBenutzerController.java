package ch.zkb.mytrade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.NeuerBenutzerDao;
import ch.zkb.mytrade.model.NeuerBenutzerModel;
import ch.zkb.mytrade.model.RolleModel;

@ManagedBean
@SessionScoped
/**
 * Controller für das Erstellen eines neuen Benutzers.
 * 
 * @version 1.0
 * @author Gwendolin.Maggion
 *
 */
public class NeuerBenutzerController {

	private NeuerBenutzerModel neuerBenutzer = new NeuerBenutzerModel();
	private NeuerBenutzerDao neuerBenutzerDao;

	public String getName() {
		return neuerBenutzer.getName();
	}

	public RolleModel getRolle() {
		return neuerBenutzer.getRolle();
	}

	public void setRolle(RolleModel rolle) {
		neuerBenutzer.setRolle(rolle);
	}

	public void setName(String name) {
		neuerBenutzer.setName(name);
	}

	public String getVorname() {
		return neuerBenutzer.getVorname();
	}

	public void setVorname(String vorname) {
		neuerBenutzer.setVorname(vorname);
	}

	public String getLogin() {
		return neuerBenutzer.getLogin();
	}

	public void setLogin(String login) {
		neuerBenutzer.setLogin(login);
	}

	public String getPasswort() {
		return neuerBenutzer.getPasswort();
	}

	public void setPasswort(String passwort) {
		neuerBenutzer.setPasswort(passwort);
	}

	private HashMap<String, RolleModel> rollen;

	public Map<String, RolleModel> getRollen() {
		rollen = new HashMap<String, RolleModel>();

		rollen.put("Administrator", RolleModel.ADMINISTRATOR);
		rollen.put("Trader", RolleModel.TRADER);

		return rollen;
	}

	// public void setRollen(HashMap<String, Rolle> rollen) {
	// this.rollen = rollen;
	// }

	public NeuerBenutzerController() {
		// neuerBenutzer;
		setNeuerBenutzer(new NeuerBenutzerModel());
	}

	public NeuerBenutzerModel getNeuerBenutzer() {
		return neuerBenutzer;
	}

	public void setNeuerBenutzer(NeuerBenutzerModel neuerBenutzer) {
		this.neuerBenutzer = neuerBenutzer;

	}

	public NeuerBenutzerDao getNeuerBenutzerDao() {
		return neuerBenutzerDao;
	}

	public void setNeuerBenutzerDao(NeuerBenutzerDao neuerBenutzerDao) {
		this.neuerBenutzerDao = neuerBenutzerDao;
	}

	public String zurueck1() {
		return "admin?faces-redirect=true";
	}

	public String zurueck2() {
		return "neuer_benutzer?faces-redirect=true";
	}

	public String benutzerspeichern() {
		return "bestaetigung_benutzer?faces-redirect=true";
	}

	public String weiter() {
		neuerBenutzerDao = new NeuerBenutzerDao();
		neuerBenutzerDao.neuerBenutzer(neuerBenutzer.getName(),
				neuerBenutzer.getVorname(), neuerBenutzer.getLogin(),
				neuerBenutzer.getPasswort(), neuerBenutzer.getRolle());
		return "admin?faces-redirect=true";
	}

}
