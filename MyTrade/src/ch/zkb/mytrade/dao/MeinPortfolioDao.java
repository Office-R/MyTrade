package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.UserModel;

public class MeinPortfolioDao {

	private ArrayList<AktieModel> aktien;
	public MeinPortfolioDao() {
		System.out.println("meinPortFolioDao");
		aktien = new ArrayList<AktieModel>();
	}
	
	public synchronized void getStocks()
	{
		ConnectionPooling pooling;
		pooling        = ConnectionPoolingImplementation.getInstance(1, 5);
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance().getExternalContext()
				                                 .getSessionMap().get("currentUser");
		Connection c1  = pooling.getConnection();

		try {
			Statement st = c1.createStatement();
			ResultSet rs = st.executeQuery("SELECT symbol.symbol, aktie.name, aktie.nominalpreis, aktie.aktie_id, aktie.dividende, user.login "
					                     + "FROM aktie "
					                     + "JOIN symbol "
					                     + "ON aktie.fk_symbol=symbol.symbol_id "
					                     + "JOIN user "
					                     + "ON aktie.fk_user=user.user_id "
					                     + "WHERE user.login=\"" + currentUser.getLogin() + "\""); 

			
			while(rs.next()) {
				AktieModel aktie = new AktieModel();
				aktie.setAkite_id     (rs.getInt("aktie.aktie_id"));
				aktie.setDividende    (rs.getDouble("aktie.dividende"));
				aktie.setName         (rs.getString("aktie.name"));
				aktie.setNominalpreis (rs.getDouble("aktie.nominalpreis"));
				aktie.setSymbol       (rs.getString("symbol.symbol"));
				aktie.setUser         (currentUser);
				
				aktien.add(aktie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<AktieModel> getAktien() {
		return aktien;
	}

	public void setAktien(ArrayList<AktieModel> aktien) {
		this.aktien = aktien;
	}
	
}
