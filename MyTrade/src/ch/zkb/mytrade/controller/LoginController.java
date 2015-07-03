package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.dao.LoginDao;

/**
 * Controller für die Login Seite. Ruft Methoden für das Authentifizieren auf.
 * 
 * @version 1.0
 * @author Robin.Frehner
 *
 */

@ManagedBean
@SessionScoped
public class LoginController {

	public String username;
	public String password;

	public LoginController() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authentication() {
		LoginDao loginDao = new LoginDao();
		if (null != loginDao.authenticate(username, password)) {
			return loginDao.authenticate(username, password);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("Message", MeldungController.LOGIN_FAIL);
		return "login?faces-redirect=true";
	}

}
