package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.model.Rolle;
import ch.zkb.mytrade.model.UserModel;

public class NeuerBenutzerDao {

	public synchronized String authenticate(String name, String vorname, String login, String Passwort, Rolle rolle)
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();

		try {
			String sqlQuery = "INSERT user.user_iD, user.name, user.vorname, user.login, "
					                     + "user.kontostand, rolle.rolle "
					                     + "FROM user "
					                     + "JOIN rolle "
					                     + "ON user.fk_rolle=rolle.rolle_id "
					                     + "WHERE user.login=? "
					                     + "AND user.password=md5(?)"; 

			prepStmt = c1.prepareStatement(sqlQuery);
			
			prepStmt.setString(1, username);
			prepStmt.setString(2, password);
			
			ResultSet rs = prepStmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "login?faces-redirect=true";
	}
}
