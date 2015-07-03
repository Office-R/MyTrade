package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.controller.MeldungController;
import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.UserModel;

public class NeuerAuftragDao {

	AktieModel aktie;
	private double vkPreis;
	private int amount;
	public NeuerAuftragDao() {
		
	}
	
	public int loadAktienProperties(int aktie_id)
	{
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
		System.out.println("id: " + aktie_id);
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		String symbol = "";
		try {

			sqlQuery = "SELECT symbol.symbol, aktie.name,  aktie.aktie_id, aktie.nominalpreis, aktie.dividende "
					+  "FROM aktie "
					+  "JOIN symbol "
					+  "ON symbol.symbol_id = aktie.fk_symbol "
					+  "WHERE aktie.aktie_id=? ";


			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setInt(1, aktie_id);
			ResultSet rs = prepStmt.executeQuery();
			
			if(rs.next()) {
				aktie = new AktieModel();
				aktie.setAktie_id(rs.getInt("aktie.aktie_id"));
				aktie.setDividende(rs.getDouble("aktie.dividende"));
				aktie.setName(rs.getString("aktie.name"));
				aktie.setNominalpreis(rs.getDouble("aktie.nominalpreis"));
				symbol = rs.getString("symbol.symbol");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pooling.putConnection(c1);
		
		return anzahlAktienImBesitz(symbol);
		
	}
	
	public int anzahlAktienImBesitz(String symbol)
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		int anzahlAktien = 0;
		try {

			sqlQuery = "SELECT COUNT(aktie.aktie_id) FROM aktie"
					+ " JOIN symbol"
					+ " ON symbol.symbol_id = aktie.fk_symbol"
					+ " WHERE symbol.symbol=?"
					+ " AND aktie.fk_user=?";

			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setString(1, symbol);
			ResultSet rs = prepStmt.executeQuery();
			if(rs.next()) {
				anzahlAktien = rs.getInt("aktie.aktie_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
		return anzahlAktien;
	}
	
	public String neuerAuftrag()
	{
		executeInsert();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Message", MeldungController.AUFTRAG);
		return "offene_auftraege?faces-redirect=true";
	}
	public void executeInsert()  
	{
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		
		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {

			sqlQuery = "INSERT INTO auftrag (preis, fk_aktie) VALUES ( ? , ? );";
			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setDouble(1, vkPreis);
			prepStmt.setInt(2, aktie.getAktie_id());
			
			for (int i = 0; i < anzahlAktienImBesitz(symbol); i++) {
				//$todo  hier
			}
			prepStmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
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
