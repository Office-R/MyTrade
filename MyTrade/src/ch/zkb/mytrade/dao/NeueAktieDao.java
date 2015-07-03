package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import ch.zkb.mytrade.controller.MeldungController;
import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.UserModel;

/**
 * Data Access Object für das erstellen einer oder mehrerer Aktien
 * 
 * @version 1.0
 * @author Gabriel.Daw
 *
 */
public class NeueAktieDao {

	private UserModel currUser;

	public NeueAktieDao() {
		currUser = (UserModel) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentUser");
	}

	public synchronized String neueAktie(AktieModel inputAktie) {
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		ResultSet resultSet;
		String sqlQuery;

		int fk_symbol = 0;

		Connection c1 = pooling.getConnection();

		try {
			sqlQuery = "INSERT INTO symbol (symbol.symbol) " + "VALUES (?)";

			prepStmt = c1.prepareStatement(sqlQuery);

			prepStmt.setString(1, inputAktie.getSymbol());

			prepStmt.executeUpdate();

			prepStmt.close();


			// -----------------------------------------------------------------------

			sqlQuery = "SELECT symbol.symbol_id FROM symbol where symbol.symbol = ?";

			prepStmt = c1.prepareStatement(sqlQuery);

			prepStmt.setString(1, inputAktie.getSymbol());

			resultSet = prepStmt.executeQuery();

			if (resultSet.next()) {
				fk_symbol = resultSet.getInt(1);
			}

			prepStmt.close();


			// ---------------------------------------------------------------------------

			sqlQuery = "INSERT INTO aktie (aktie.name, aktie.nominalpreis, aktie.dividende, aktie.fk_user, aktie.fk_symbol) "
					+ "VALUES (?, ?, ?, ?, ?)";

			prepStmt = c1.prepareStatement(sqlQuery);

			prepStmt.setString(1, inputAktie.getName());
			prepStmt.setDouble(2, inputAktie.getNominalpreis());
			prepStmt.setDouble(3, inputAktie.getDividende());
			prepStmt.setInt(4, currUser.getUser_id());
			prepStmt.setInt(5, fk_symbol);

			for (int i = 0; i < inputAktie.getStueckZahl(); i++) {
				prepStmt.execute();
			}

			prepStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("Message", MeldungController.AKTIE_ADDED);
		if (1 < inputAktie.getStueckZahl()) {
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap()
					.put("Message", MeldungController.AKTIEN_ADDED);
		}

		pooling.putConnection(c1);
		return "../meinPortfolio?faces-redirect=true";
	}
}
