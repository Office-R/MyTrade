package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.zkb.mytrade.model.AuftragModel;

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

		String sqlQuery;
		try {

			sqlQuery = "SELECT auftrag.preis, aktie.name, aktie.aktie_id, symbol.symbol, user.login, user.kontostand FROM auftrag"
					+ " JOIN aktie"
					+ " ON auftrag.fk_aktie = aktie.aktie_id"
					+ " JOIN symbol"
					+ " ON aktie.fk_symbol = symbol.symbol_id"
					+ " JOIN user" + " ON aktie.fk_user = user.user_id";

			prepStmt = con.prepareStatement(sqlQuery);

			prepStmt.executeQuery();

			resultSet = prepStmt.executeQuery();

			while (resultSet.next()) {
				AuftragModel auftrag = new AuftragModel();

				auftrag.setPreis(resultSet.getDouble("auftrag.preis"));
				auftrag.setAktie(resultSet.getString("aktie.name"));
				auftrag.setAktie_id(resultSet.getInt("aktie.aktie_id"));
				auftrag.setSymbol(resultSet.getString("symbol.symbol"));
				auftrag.setLogin(resultSet.getString("user.login"));
				auftrag.setKontostand(resultSet.getDouble("user.kontostand"));
				auftrag.setBesitzer();

				auftragListe.add(auftrag);

			}
			resultSet.close();
			prepStmt.close();
			con.close();

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
}
