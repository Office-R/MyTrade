package ch.zkb.mytrade.model;

public class AktieModel {
	private int       akite_id;
	private String    name;
	private double    nominalpreis;
	private double    dividende;
	private UserModel user;
	private String    symbol;
	
	public AktieModel() {
		// TODO Auto-generated constructor stub
	}

	public int getAkite_id() {
		return akite_id;
	}

	public void setAkite_id(int akite_id) {
		this.akite_id = akite_id;
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