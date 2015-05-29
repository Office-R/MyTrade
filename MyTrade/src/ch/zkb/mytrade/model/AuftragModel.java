package ch.zkb.mytrade.model;

public class AuftragModel {
	int auftrag_id;
	int aktie_id;
	String symbol;
	String aktie;
	String login;
	double preis;
	double kontostand;
	boolean isBesitzer;
	
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

	public AuftragModel() {
		
	}

}
