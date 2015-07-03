package ch.zkb.mytrade.controller;

/**
 * Controller um beim Aufruf einer Seite, die nicht existiert auf eine
 * Fehler-Seite zu referenzieren.
 * 
 * @version 1.0
 * @author Robin.Frehner
 *
 */
public class NotFoundController {
	public NotFoundController() {
		// TODO Auto-generated constructor stub
	}

	public String sendBack() {
		return "mein_portfolio?faces-redirect=true";
	}
}
