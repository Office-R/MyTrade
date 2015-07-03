package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zkb.mytrade.model.UserModel;


/**
 * Controller f�r den Logout-Button. L�scht CurrentUser aus der Session und
 * leitet auf die Login Seite weiter.
 * 
 * @version 1.0
 * @author Gwendolin.Maggion
 *
 */
@ManagedBean
@SessionScoped
public class LogoutController {

	public LogoutController() {

	}

	public synchronized String logout() {

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("currentUser");
		return "/MyTrade/faces/login"; // ?faces-redirect=true";

	}

}
