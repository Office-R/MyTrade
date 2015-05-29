package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {

	public static void main(String[] args) {
		try {
			new Test().top();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*
	-----SQL STATEMENT FÜR OFFENE POSITIONEN ANZEIGEN------

	SELECT auftrag.preis, aktie.name, aktie.aktie_id, symbol.symbol, user.login, user.kontostand FROM auftrag
	JOIN aktie
	ON auftrag.fk_aktie = aktie.aktie_id
	JOIN symbol
	ON aktie.fk_symbol = symbol.symbol_id
	JOIN user
	ON aktie.fk_user = user.user_id;

	------Statement für portfolio anzeigen---------

	SELECT symbol.symbol, aktie.name, aktie.dividende, user.login FROM aktie
	JOIN symbol
	ON aktie.fk_symbol = symbol.symbol_id
	JOIN user
	ON aktie.fk_user = user.user_id
	WHERE user.login ="gwen";
*/
	void top() throws Exception {
		ConnectionPooling pooling;
		pooling = ConnectionPoolingImplementation.getInstance(1, 5);
		System.out.println(pooling);
		
		Connection c1 = pooling.getConnection();
		Connection c2 = pooling.getConnection();
		Connection c3 = pooling.getConnection();
		
		pooling.putConnection(c2);
		pooling.putConnection(c3);
		System.out.println(pooling);

		// erster Fehler: Connection zurÃ¼ckgeben, welche nicht busy war:
		pooling.putConnection(c2);

		// zweiter Fehler: zu viele Connecitons beansprucht:
		pooling.getConnection();
		pooling.getConnection();
		pooling.getConnection();
		pooling.getConnection();
		pooling.getConnection(); // mehr als 5 sind nicht mÃ¶glich

		//remark: c1 is still active
		Statement st = c1.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM `Ort`");
		while(rs.next()) {
			System.out.println(rs.getString("Ortsname"));
		}
	}
	
}
 // end of class Test