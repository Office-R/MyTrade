package ch.zkb.mytrade.model;

/**
 * Model das einen bestehenden User abbildet.
 * 
 * @version 1.0
 * @author Gabriel.Daw
 *
 */
public class UserModel {

	private int user_id;
	private String name;
	private String vorname;
	private String login;
	private String passsword;
	RolleModel rolle;

	public UserModel() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
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

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	public RolleModel getRolle() {
		return rolle;
	}

	public void setRolle(RolleModel rolle) {
		this.rolle = rolle;
	}

	public void setRolleString(String rolle) {
		if (rolle.toLowerCase().equals("administrator")) {
			this.rolle = RolleModel.ADMINISTRATOR;
		} else {
			this.rolle = RolleModel.TRADER;
		}
	}

}
