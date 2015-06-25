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
	private double vkPreis;
	private int amount;
	public NeuerAuftragDao() {
		
	}
	
	public void loadAktienProperties(int aktie_id)
	{
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
		System.out.println("id: " + aktie_id);
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {

			sqlQuery = "SELECT aktie.name,  aktie.aktie_id, aktie.nominalpreis, aktie.dividende FROM aktie"
				    + " WHERE aktie.aktie_id=?";


			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setInt(1, aktie_id);
			ResultSet rs = prepStmt.executeQuery();
			System.out.println(sqlQuery);
			
			while(rs.next()) {
				aktie = new AktieModel();
				aktie.setAktie_id(rs.getInt("aktie.aktie_id"));
				aktie.setDividende(rs.getDouble("aktie.dividende"));
				aktie.setName(rs.getString("aktie.name"));
				aktie.setNominalpreis(rs.getDouble("aktie.nominalpreis"));
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
					+ " WHERE aktie.name=?"
					+ " AND aktie. ";

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
		executeInsert();
//		 int anzahlAktien = anzahlDerAktienImBesitz(aktie.getName());
//		 if(amount <= anzahlAktien){
//			 for (int i = 0; i < amount; i++){
//				 
//			 }
//		 }
	}
	public void executeInsert()
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {

			sqlQuery = "INSERT INTO auftrag (preis, fk_aktie) VALUES (?, ?)";
			System.out.println(vkPreis + "" +  aktie.getAktie_id());
			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setDouble(1, vkPreis);
			prepStmt.setInt(2, aktie.getAktie_id());
			prepStmt.executeUpdate(sqlQuery);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AktieModel getAktie() {
		return aktie;
	}

	public void setAktie(AktieModel aktie) {
		this.aktie = aktie;
	}

	public double getVkPreis() {
		return vkPreis;
	}

	public void setVkPreis(double vkPreis) {
		this.vkPreis = vkPreis;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
