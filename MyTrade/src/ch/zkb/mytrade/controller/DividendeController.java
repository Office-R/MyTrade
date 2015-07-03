package ch.zkb.mytrade.controller;

import javax.faces.bean.SessionScoped;
import ch.zkb.mytrade.dao.DividendeDao;

/**
 * Controller für das Ausschütten der Dividenden.
 * 
 * @version 1.0
 * @author Robin.Frehner
 *
 */

@javax.faces.bean.ManagedBean
@SessionScoped
public class DividendeController {

	private DividendeDao dividendeDao;

	public DividendeController() {
		dividendeDao = new DividendeDao();
	}

	public DividendeDao getDividendeDao() {
		return dividendeDao;
	}

	public void setDividendeDao(DividendeDao dividendeDao) {
		this.dividendeDao = dividendeDao;
	}

}
