package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LogoutController {
	
	public LogoutController() {
		// TODO Auto-generated constructor stub
	}
	public synchronized String logout(){
		
		System.out.println("logout");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
		return "/MyTrade/faces/login"; //?faces-redirect=true";
			
	}
	

}
