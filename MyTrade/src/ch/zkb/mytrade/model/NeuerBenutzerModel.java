package ch.zkb.mytrade.model;

public class NeuerBenutzerModel {
	
	private String name;
	private String vorname;
	private String login;
	private String passwort;
	Rolle rolle;
	
	public NeuerBenutzerModel(){
		name = "";
		vorname = "";
		login = "";
		passwort = "";
		rolle = Rolle.TRADER;
	}	
	
	public String getName() {
		return name;
	}
	public Rolle getRolle() {
		return rolle;
	}

	public void setRolle(Rolle rolle) {
		this.rolle = rolle;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	};
	

}
