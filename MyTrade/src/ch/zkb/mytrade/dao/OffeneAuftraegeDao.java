package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean
@SessionScoped
public class OffeneAuftraegeDao {

	public OffeneAuftraegeDao() {
		
	}
	
	public String getOffeneAuftraege() {
		InitialContext ctx;
	    DataSource ds;
	    Connection con;
	    PreparedStatement prepStmt;
	    ResultSet resultSet;
	    ArrayList<Auftrag> temp = new ArrayList<Auftrag>();
	    String sqlQuery;
	    try {
	    	ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("NotenRess");
			con = ds.getConnection();
			 
			sqlQuery = "UPDATE fach " +
			           "   SET fachKurz=?, bezeichnung=?" +
				       " WHERE (fachID = ?)";
			 
			prepStmt = con.prepareStatement(sqlQuery);
			 
			prepStmt.setString(1, getFachKurz());
			prepStmt.setString(2, getBezeichnung());
			prepStmt.setInt   (3, currentId);
			 
			prepStmt.executeUpdate();
			
	      resultSet = prepStmt.executeQuery();
	 
	      while (resultSet.next())
	      {
	        Buch buch = new Buch();
	        buch.setBuchId(resultSet.getInt("buchId"));
	        buch.setTitel(resultSet.getString("titel"));
	        buch.setAutor(resultSet.getString("autor"));
	        buch.setPreis(resultSet.getDouble("preis"));
	        buch.setIsbn(resultSet.getString("isbn"));
	 
	        temp.add(buch);
	      }
	      resultSet.close();
	      prepStmt.close();
	      con.close();
	 
	    } catch (NamingException e) {
	      e.printStackTrace();
	 
	    } catch (SQLException e) {
	      e.printStackTrace();
	 
	    }
	    return temp;
	}
}
