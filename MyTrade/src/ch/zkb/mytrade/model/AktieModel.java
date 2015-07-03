package ch.zkb.mytrade.model;

/**
 * Model das eine Aktie abbildet.
 * 
 * @version 1.0
 * @author Gabriel.Daw
 *
 */
public class AktieModel {
	private int aktie_id;
	private String name;
	private double nominalpreis;
	private double dividende;
	private UserModel user;
	private int user_id;
	private String symbol;
	private int stueckZahl;

	public AktieModel() {
		// TODO Auto-generated constructor stub
	}

	public int getStueckZahl() {
		return stueckZahl;
	}

	public void setStueckZahl(int stueckZahl) {
		this.stueckZahl = stueckZahl;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAktie_id() {
		return aktie_id;
	}

	public void setAktie_id(int akite_id) {
		this.aktie_id = akite_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNominalpreis() {
		return nominalpreis;
	}

	public void setNominalpreis(double nominalpreis) {
		this.nominalpreis = nominalpreis;
	}

	public double getDividende() {
		return dividende;
	}

	public void setDividende(double dividende) {
		this.dividende = dividende;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
