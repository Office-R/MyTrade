package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import ch.zkb.mytrade.controller.MeldungController;
import ch.zkb.mytrade.model.RolleModel;

/**
 * Data Access Object für das Erstellen eines neuen Benutzers, wobei die Rollen
 * Administrator und Trader unterschieden wird.
 * 
 * @version 1.0
 * @author Gwendolin.Maggion
 *
 */

public class NeuerBenutzerDao {

	public synchronized void neuerBenutzer(String name, String vorname,
			String login, String passwort, RolleModel rolle) {
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		int rolleI = 0;

		Connection c1 = pooling.getConnection();

		try {
			String sqlQuery = "INSERT INTO user (user.name, user.vorname, user.login, user.password, user.fk_rolle, user.kontostand) "
					+ "VALUES (?, ?, ?, md5(?), ?, ?)";

			prepStmt = c1.prepareStatement(sqlQuery);

			prepStmt.setString(1, name);
			prepStmt.setString(2, vorname);
			prepStmt.setString(3, login);
			prepStmt.setString(4, passwort);

			if (rolle.equals(RolleModel.ADMINISTRATOR)) {
				rolleI = 1;
			} else {
				rolleI = 2;
			}

			prepStmt.setInt(5, rolleI);
			prepStmt.setInt(6, 10000);
			if (prepStmt.execute()) {
			}

			prepStmt.close();

		} catch (SQLException e) {
			pooling.putConnection(c1);
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("Message", MeldungController.BENUTZER_ADDED);
		pooling.putConnection(c1);
	}
}
