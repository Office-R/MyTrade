package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import ch.zkb.mytrade.controller.MeldungController;
import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.UserModel;

public class NeuerAuftragDao {

	AktieModel aktie;
	private double vkPreis;
	private int amount;
	ArrayList aktienListe = new ArrayList();

	public NeuerAuftragDao() {

	}

	public void loadAktienProperties(int aktie_id) {
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("currentUser");
		System.out.println("id: " + aktie_id);
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);

		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {

			sqlQuery = "SELECT symbol.symbol, aktie.name,  aktie.aktie_id, aktie.nominalpreis, aktie.dividende "
					+ "FROM aktie "
					+ "JOIN symbol "
					+ "ON symbol.symbol_id = aktie.fk_symbol "
					+ "WHERE aktie.aktie_id=? ";

			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setInt(1, aktie_id);
			ResultSet rs = prepStmt.executeQuery();

			if (rs.next()) {
				aktie = new AktieModel();
				aktie.setAktie_id(rs.getInt("aktie.aktie_id"));
				aktie.setDividende(rs.getDouble("aktie.dividende"));
				aktie.setName(rs.getString("aktie.name"));
				aktie.setNominalpreis(rs.getDouble("aktie.nominalpreis"));
				aktie.setSymbol(rs.getString("symbol.symbol"));
			}
		} catch (SQLException e) {
			pooling.putConnection(c1);
			e.printStackTrace();
		}

		pooling.putConnection(c1);

		return;

	}

	public int anzahlAktienImBesitz() {
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

		Connection c1 = pooling.getConnection();
		String sqlQuery;
		String symbol = aktie.getSymbol(); 
		aktienListe.clear();
		try {

			sqlQuery = "SELECT aktie.aktie_id FROM aktie" + " JOIN symbol"
					+ " ON symbol.symbol_id = aktie.fk_symbol"
					+ " WHERE symbol.symbol=?"  
					+ " AND aktie.fk_user=?"
					+ " AND aktie.aktie_id NOT IN (SELECT auftrag.fk_aktie FROM auftrag)";

			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setString(1, symbol);
			prepStmt.setInt(2,  currentUser.getUser_id());
			
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				System.out.println("IM NEXT");
				aktienListe.add(rs.getInt("aktie.aktie_id"));
			}
		} catch (SQLException e) {
			pooling.putConnection(c1);
			e.printStackTrace();
		}
		pooling.putConnection(c1);
		return 	aktienListe.size();

	}

	public String neuerAuftrag(int anzahlAktien) {

		
		executeInsert(anzahlAktien);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("Message", MeldungController.AUFTRAG);
		return "offene_auftraege?faces-redirect=true";
	}

	public void executeInsert(int anazhlAktien) {
		PreparedStatement prepStmt;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);

		Connection c1 = pooling.getConnection();
		String sqlQuery;
		try {
			sqlQuery = "INSERT INTO auftrag (preis, fk_aktie) VALUES ( ? , ? );";
			prepStmt = c1.prepareStatement(sqlQuery);
			prepStmt.setDouble(1, vkPreis);

			for (int i = 0; i < getAmount(); i++) {
				System.out.println(i);
				prepStmt.setInt(2, (Integer) aktienListe.get(i));

				prepStmt.executeUpdate();
			}

		} catch (SQLException e) {
			pooling.putConnection(c1);
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
