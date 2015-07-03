package ch.zkb.mytrade.model;

import javax.faces.context.FacesContext;

import ch.zkb.mytrade.dao.OffeneAuftraegeDao;

/**
 * Model das einen Verkaufs-Auftrag abbildet.
 * 
 * @version 1.0
 * @author Robin.Frehner
 *
 */
public class AuftragModel {
	private int auftrag_id;
	private int aktie_id;
	private String symbol;
	private String aktie;
	private String login;
	private double preis;
	private double kontostand;
	private String currentUserLogin;
	private boolean isBesitzer;
	private int besitzer_id;
	private OffeneAuftraegeDao offeneAuftraegeDao;

	public AuftragModel() {

	}

	public int getAuftrag_id() {
		return auftrag_id;
	}

	public void setAuftrag_id(int auftrag_id) {
		this.auftrag_id = auftrag_id;
	}

	public int getAktie_id() {
		return aktie_id;
	}

	public void setAktie_id(int aktie_id) {
		this.aktie_id = aktie_id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAktie() {
		return aktie;
	}

	public void setAktie(String aktie) {
		this.aktie = aktie;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public double getKontostand() {
		return kontostand;
	}

	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	public boolean setBesitzer() {
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentUser");

		if (currentUser != null) {
			currentUserLogin = currentUser.getLogin();
		} else {
			throw new NullPointerException();
		}

		this.isBesitzer = currentUserLogin.equals(this.login);

		return isBesitzer;
	}

	public String getAktion() {
		if (this.isBesitzer) {
			return "stornieren";
		}
		return "kaufen";
	}

	public String buyOrStorno() {
		if (this.isBesitzer) {
			offeneAuftraegeDao.storno(this);
		} else {
			offeneAuftraegeDao.kauf(this);

		}
		return "mein_portfolio?faces-redirect=true";
	}

	public OffeneAuftraegeDao getOffeneAuftraegeDao() {
		return offeneAuftraegeDao;
	}

	public void setOffeneAuftraegeDao(OffeneAuftraegeDao offeneAuftraegeDao) {
		this.offeneAuftraegeDao = offeneAuftraegeDao;
	}

	public int getBesitzer_id() {
		return besitzer_id;
	}

	public void setBesitzer_id(int besitzer_id) {
		this.besitzer_id = besitzer_id;
	}

}
