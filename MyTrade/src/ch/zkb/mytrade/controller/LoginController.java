package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.dao.LoginDao;

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
	
	public String authentication()
	{
		LoginDao loginDao = new LoginDao();
		return loginDao.authenticate(username, password);
	}
	
}