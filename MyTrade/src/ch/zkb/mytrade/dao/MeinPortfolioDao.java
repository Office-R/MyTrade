package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import com.mysql.jdbc.PreparedStatement;

import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.UserModel;

/**
 * Data Access Object für anzeigen aller Aktien im persönlichen Portfolio
 * 
 * @version 1.0
 * @author Robin.Frehner
 *
 */
public class MeinPortfolioDao {

	private ArrayList<AktieModel> aktien;

	public MeinPortfolioDao() {
		aktien = new ArrayList<AktieModel>();
	}

	public synchronized void getStocks() {
		aktien.clear();
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentUser");
		if (null == currentUser)
			return;
		Connection c1 = pooling.getConnection();
		PreparedStatement prepStmt;

		try {
			String sqlQuery = "SELECT symbol.symbol, aktie.name, aktie.nominalpreis, aktie.aktie_id, aktie.dividende, user.login"
					+ " FROM aktie"
					+ " JOIN symbol"
					+ " ON aktie.fk_symbol=symbol.symbol_id"
					+ " JOIN user"
					+ " ON aktie.fk_user=user.user_id"
					+ " WHERE user.login = ? "
					+ " AND aktie.aktie_id NOT IN (SELECT auftrag.fk_aktie FROM auftrag)";

			prepStmt = (PreparedStatement) c1.prepareStatement(sqlQuery);
			prepStmt.setString(1, currentUser.getLogin());
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				AktieModel aktie = new AktieModel();
				aktie.setAktie_id(rs.getInt("aktie.aktie_id"));
				aktie.setDividende(rs.getDouble("aktie.dividende"));
				aktie.setName(rs.getString("aktie.name"));
				aktie.setNominalpreis(rs.getDouble("aktie.nominalpreis"));
				aktie.setSymbol(rs.getString("symbol.symbol"));
				aktie.setUser(currentUser);

				aktien.add(aktie);
			}
			prepStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
	}

	public ArrayList<AktieModel> getAktien() {
		return aktien;
	}

	public void setAktien(ArrayList<AktieModel> aktien) {
		this.aktien = aktien;
	}

}
