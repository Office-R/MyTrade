package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.UserModel;

public class NeuerAuftragDao {

	AktieModel aktie;
	public NeuerAuftragDao() {
		
	}
	
	public void loadAktienProperties(int aktie_id)
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {

			sqlQuery = "SELECT auftrag.preis, aktie.name, aktie.aktie_id, symbol.symbol, user.login, user.kontostand FROM auftrag"
					+ " JOIN aktie"
					+ " ON auftrag.fk_aktie = aktie.aktie_id"
					+ " JOIN symbol"
					+ " ON aktie.fk_symbol = symbol.symbol_id"
					+ " JOIN user" + " ON aktie.fk_user = user.user_id"
				    + " WHERE aktie.aktie_id=?";


			prepStmt = c1.prepareStatement(sqlQuery);
			
			prepStmt.setInt(1, aktie_id);
			
			ResultSet rs = prepStmt.executeQuery();
			
			
			while(rs.next()) {
				aktie = new AktieModel();
				aktie.setAktie_id(rs.getInt("aktie.aktie_id"));
				aktie.setDividende(rs.getDouble("aktie.dividende"));
				aktie.setName(rs.getString("aktie.name"));
				aktie.setNominalpreis(rs.getDouble("aktie.nominalpreis"));
				aktie.setSymbol(rs.getString("symbol.symbol"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int anzahlDerAktienImBesitz(String aktienName)
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {

			sqlQuery = "SELECT COUNT(aktie.aktie_id) FROM aktie"
					+ " WHERE aktie.name=?";


			prepStmt = c1.prepareStatement(sqlQuery);
			
			prepStmt.setString(1, aktienName);
			
			ResultSet rs = prepStmt.executeQuery();
			
			
			while(rs.next()) {
				return rs.getInt("aktie.aktie_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public void neuerAuftrag()
	{
		 
	}

	public AktieModel getAktie() {
		return aktie;
	}

	public void setAktie(AktieModel aktie) {
		this.aktie = aktie;
	}
	
}
