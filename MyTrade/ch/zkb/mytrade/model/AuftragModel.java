package ch.zkb.mytrade.model;

import javax.faces.context.FacesContext;


public class AuftragModel {
	private int auftrag_id;
	private int aktie_id;
	private String symbol;
	private String aktie;
	private String login;
	private double preis;
	private double kontostand;
	String currentUserLogin;
	private boolean isBesitzer;

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
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("currentUser");
		
		if (currentUser != null) {
			currentUserLogin = currentUser.getLogin();
		}
		else{
			System.out.println("currentUser is null!");
		}
		
		
		this.isBesitzer = currentUserLogin.equals(this.login);
		System.out.println(currentUser.getLogin());
		System.out.println(this.login);
		
		return isBesitzer;
	}
	
	public String getAktion(){
		if(this.isBesitzer){
			return "stornieren";
		}
		return "kaufen";
	}
		
}
