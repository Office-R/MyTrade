package ch.zkb.mytrade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.model.NeuerBenutzerModel;
import ch.zkb.mytrade.model.Rolle;

@ManagedBean
@SessionScoped
public class NeuerBenutzerController {

	private NeuerBenutzerModel neuerBenutzer;

	public String getName() {
		return neuerBenutzer.getName();
	}

	public Rolle getRolle() {
		System.out.println("geht durch 2");
		return neuerBenutzer.getRolle();
	}

	public void setRolle(Rolle rolle) {
		System.out.println("geht durch 3");
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

	private HashMap<String, Rolle> rollen;

	public Map<String, Rolle> getRollen() {
		System.out.println("geht durch 1");
		rollen = new HashMap<String, Rolle>();

		rollen.put("Administrator", Rolle.ADMINISTRATOR);
		rollen.put("Trader", Rolle.TRADER);

		return rollen;
	}

	// public void setRollen(HashMap<String, Rolle> rollen) {
	// this.rollen = rollen;
	// }

	public NeuerBenutzerController() {
		System.out.println("geht durch 4");
		setNeuerBenutzer(new NeuerBenutzerModel());
	}

	public NeuerBenutzerModel getNeuerBenutzer() {
		return neuerBenutzer;
	}

	public void setNeuerBenutzer(NeuerBenutzerModel neuerBenutzer) {
		this.neuerBenutzer = neuerBenutzer;

	}
}
