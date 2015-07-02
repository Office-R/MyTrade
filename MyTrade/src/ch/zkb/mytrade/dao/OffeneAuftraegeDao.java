package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.zkb.mytrade.model.AuftragModel;
import ch.zkb.mytrade.model.UserModel;

@ManagedBean
@SessionScoped
public class OffeneAuftraegeDao {
	ArrayList<AuftragModel> auftragListe;

	public OffeneAuftraegeDao() {
		auftragListe = new ArrayList<AuftragModel>();
	}

	public void getOffeneAuftraege() {

		Connection con;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);

		con = pooling.getConnection();
		PreparedStatement prepStmt;
		ResultSet resultSet;
		auftragListe.clear();
		String sqlQuery;
		try {

			sqlQuery = "SELECT aktie.fk_user, auftrag.auftrag_id, auftrag.preis, aktie.name, aktie.aktie_id, symbol.symbol, user.login, user.kontostand FROM auftrag"
					+ " JOIN aktie"
					+ " ON auftrag.fk_aktie = aktie.aktie_id"
					+ " JOIN symbol"
					+ " ON aktie.fk_symbol = symbol.symbol_id"
					+ " JOIN user ON aktie.fk_user = user.user_id";

			prepStmt = con.prepareStatement(sqlQuery);

			prepStmt.executeQuery();

			resultSet = prepStmt.executeQuery();

			while (resultSet.next()) {
				AuftragModel auftrag = new AuftragModel();
				auftrag.setAuftrag_id(resultSet.getInt("auftrag.auftrag_id"));
				auftrag.setBesitzer_id(resultSet.getInt("aktie.fk_user"));
				auftrag.setPreis(resultSet.getDouble("auftrag.preis"));
				auftrag.setAktie(resultSet.getString("aktie.name"));
				auftrag.setAktie_id(resultSet.getInt("aktie.aktie_id"));
				auftrag.setSymbol(resultSet.getString("symbol.symbol"));
				auftrag.setLogin(resultSet.getString("user.login"));
				auftrag.setKontostand(resultSet.getDouble("user.kontostand"));
				auftrag.setBesitzer();
				auftrag.setOffeneAuftraegeDao(this);

				System.out.println(resultSet.getString("aktie.name"));
				System.out.println(auftrag.getAktion());

				auftragListe.add(auftrag);

			}
			resultSet.close();
			prepStmt.close();
			pooling.putConnection(con);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public ArrayList<AuftragModel> getAuftragListe() {
		return auftragListe;
	}

	public void setAuftragListe(ArrayList<AuftragModel> auftragListe) {
		this.auftragListe = auftragListe;
	}

	public void storno(AuftragModel auftragModel) {
		Connection con;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);

		con = pooling.getConnection();
		PreparedStatement prepStmt;
		String sqlQuery;

		try {

			sqlQuery = "DELETE FROM auftrag WHERE auftrag.auftrag_id = ?";
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setInt(1, auftragModel.getAuftrag_id());

			prepStmt.executeUpdate();

			prepStmt.close();
			pooling.putConnection(con);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	public double getKontostand(int userId){
		
		Connection con;
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);

		con = pooling.getConnection();
		PreparedStatement prepStmt;
		String sqlQuery;
		ResultSet resultSet;

		try {

			sqlQuery = "SELECT user.kontostand FROM user WHERE user.user_id = ?";
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setInt(1, userId);
			
			resultSet = prepStmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getDouble("user.kontostand");
			}

			prepStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		pooling.putConnection(con);
		return 0;		
		
	}

	public synchronized void kauf(AuftragModel auftragModel) {
		UserModel currentUser = (UserModel) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("currentUser");
		
		if (getKontostand(currentUser.getUser_id()) >= auftragModel.getPreis()){
			Connection con;
			ConnectionPooling pooling;
			pooling = ConnectionPoolingImplementation.getInstance(1, 5);

			con = pooling.getConnection();
			PreparedStatement prepStmt;
			String sqlQuery;

			try {

				sqlQuery = "UPDATE aktie SET aktie.fk_user = ? WHERE aktie.aktie_id = ?";
				prepStmt = con.prepareStatement(sqlQuery);
				prepStmt.setInt(1, currentUser.getUser_id());
				prepStmt.setInt(2, auftragModel.getAktie_id());

				prepStmt.executeUpdate();
				
				sqlQuery = "UPDATE user SET user.kontostand = user.kontostand - ? WHERE user.user_id = ?";
				prepStmt = con.prepareStatement(sqlQuery);
				prepStmt.setDouble(1, auftragModel.getPreis());
				prepStmt.setInt(2, currentUser.getUser_id());

				prepStmt.executeUpdate();
				
				sqlQuery = "UPDATE user SET user.kontostand = user.kontostand + ? WHERE user.user_id = ?";
				prepStmt = con.prepareStatement(sqlQuery);
				prepStmt.setDouble(1, auftragModel.getPreis());
				prepStmt.setInt(2, auftragModel.getBesitzer_id());

				prepStmt.executeUpdate();

				prepStmt.close();
				pooling.putConnection(con);
				
				storno(auftragModel);
				

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
	}

}
