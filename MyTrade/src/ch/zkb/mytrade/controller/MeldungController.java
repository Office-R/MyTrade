package ch.zkb.mytrade.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;




@ManagedBean
@SessionScoped
public class MeldungController {
	
	public static String DIVIDENDE      = "Dividende wurde erfolgreich ausgeschüttet";
	public static String BENUTZER_ADDED = "Ein neuer Benutzer wurde erfolgreich erfasst";
	public static String AKTIE_BUY      = "Die Aktie wurde erfolgreich gekauft";
	public static String AUFTRAG        = "Der Verkaufs-Auftrag wurde erfolgreich erfasst";
	public static String STORNO         = "Der Auftrag wurde erfolgreich storniert";
	public static String AKTIE_ADDED    = "Eine Aktie wurde erfolgreich hinzugefügt";
	public static String AKTIEN_ADDED   = "Mehrere Aktien wurden erfolgreich hinzugefügt";
	public static String ACCESS_DENIED  = "Zugriff verweigert";
	public static String FALSE_URL      = "Der Uniform Resource Locator existiert nicht";
	
	public static String EMPTY_MESSAGE  = "";
	
	
	
	
	public String getMessage(){
		String returnMeldung;
		
		returnMeldung = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Message");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Message",EMPTY_MESSAGE);
		
		return returnMeldung;
		
	}
	

}
