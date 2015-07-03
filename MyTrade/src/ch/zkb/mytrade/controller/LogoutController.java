package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zkb.mytrade.model.UserModel;

@ManagedBean
@SessionScoped
public class LogoutController {
	
	public LogoutController() {
		// TODO Auto-generated constructor stub
	}
	public synchronized String logout(){
		
		System.out.println("logout");
//		UserModel user = (UserModel)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
//		user = null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", null);
		return "/MyTrade/faces/login"; //?faces-redirect=true";
			
	}
	

}
