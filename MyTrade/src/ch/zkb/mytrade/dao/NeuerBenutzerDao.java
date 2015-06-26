package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import ch.zkb.mytrade.model.Rolle;


public class NeuerBenutzerDao {

	public synchronized String neuerBenutzer(String name, String vorname, String login, String passwort, Rolle rolle)
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		int rolleI = 0;
		
		Connection c1 = pooling.getConnection();

		try {
			String sqlQuery = "INSERT INTO user (user.name, user.vorname, user.login, user.password, user.rolle) "
					+ "VALUES (?, ?, ?, ?, ?)" ;

			prepStmt = c1.prepareStatement(sqlQuery);
			
			prepStmt.setString(1, name);
			prepStmt.setString(2, vorname);
			prepStmt.setString(3, login);
			prepStmt.setString(4, passwort);
			
			if(rolle.equals(Rolle.ADMINISTRATOR)){
				rolleI = 1;
			} else {
				rolleI = 2;
			}
			
			prepStmt.setInt(5, rolleI);
			if(prepStmt.execute()){
				System.out.println("hat geklappt");
			}
			
			prepStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
		System.out.println("ging durch DB");
		return "meinPortfolio?faces-redirect=true";
	}
}
