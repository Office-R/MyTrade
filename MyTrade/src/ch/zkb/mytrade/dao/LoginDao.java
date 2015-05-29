package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.model.UserModel;

public class LoginDao {
	
	
	public LoginDao() {

	}
	public synchronized String authenticate(String username, String password)
	{
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();

		try {
			Statement st = c1.createStatement();
			ResultSet rs = st.executeQuery("SELECT user.user_id, user.name, user.vorname, user.login, "
					                     + "user.kontostand, rolle.rolle "
					                     + "FROM user "
					                     + "JOIN rolle "
					                     + "ON user.fk_rolle=rolle.rolle_id "
					                     + "WHERE user.login= \""+ username + "\" "
					                     + "AND user.password=md5(\"" + password + "\")"); 

			
			while(rs.next()) {
				UserModel user = new UserModel();
				user.setLogin   (rs.getString("user.login"));
				user.setName    (rs.getString("user.name"));
				user.setRolle   (rs.getString("rolle.rolle"));
				user.setUser_id (rs.getInt   ("user.user_id"));
				user.setVorname (rs.getString("user.vorname"));
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap =  externalContext.getSessionMap();
				sessionMap.put("currentUser", user);
				return "mein_portfolio?faces-redirect=true";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "login?faces-redirect=true";
	}
}
