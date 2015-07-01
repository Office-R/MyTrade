package ch.zkb.mytrade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import org.apache.commons.collections.bag.SynchronizedBag;

import ch.zkb.mytrade.model.AktieModel;
import ch.zkb.mytrade.model.SymbolModel;
import ch.zkb.mytrade.model.UserModel;

import com.mysql.jdbc.PreparedStatement;

public class DividendeDao {
	
	private Double dividendeInChFPerStk;
	public DividendeDao() {
		System.out.println("DividendeDao");
	}
	
	public synchronized ArrayList<SymbolModel> loadSymbole(){
		ConnectionPooling pooling;
		ArrayList<SymbolModel> symbole = new ArrayList<SymbolModel>();
		pooling        = ConnectionPoolingImplementation.getInstance(1, 5);

		Connection c1  = pooling.getConnection();
		PreparedStatement prepStmt;

		try {
			String sqlQuery = "SELECT symbol.symbol_id, symbol.symbol FROM symbol";
			prepStmt = (PreparedStatement) c1.prepareStatement(sqlQuery);
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()) {
				SymbolModel symbolModel = new SymbolModel();
				symbolModel.setSymbol_id(rs.getInt   ("symbol.symbol_id"));
				symbolModel.setSymbol   (rs.getString("symbol.symbol"));
				symbole.add(symbolModel);
			}
			prepStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
		return symbole;
	}
	
	public synchronized void ausschuetten(String symbol){
		System.out.println("Ausschütten: " + symbol);
		ConnectionPooling pooling;
		pooling        = ConnectionPoolingImplementation.getInstance(1, 5);

		Connection c1  = pooling.getConnection();
		PreparedStatement prepStmt;
		PreparedStatement prepStmtUpdate;

		try {
			String sqlQuery = " SELECT aktie.fk_user from aktie"
							+ " JOIN symbol"
							+ " ON aktie.fk_symbol  = symbol.symbol_id"
							+ " Where symbol.symbol = ?";
			prepStmt = (PreparedStatement) c1.prepareStatement(sqlQuery);
			prepStmt.setString(1, symbol);
			ResultSet rs = prepStmt.executeQuery();
			System.out.println(getDividendeInChFPerStk());
			while(rs.next()) {
				String sqlUpdate = " UPDATE user"
					           	+  " SET user.kontostand = user.kontostand + ?"
					           	+  " WHERE user.user_id = ?";
				prepStmtUpdate = (PreparedStatement) c1.prepareStatement(sqlUpdate);
				prepStmtUpdate.setDouble(1, getDividendeInChFPerStk());
				prepStmtUpdate.setInt(2, rs.getInt("aktie.fk_user"));
				int returnValue = prepStmtUpdate.executeUpdate(); // return value abgefangen 
																  // ohne gabs eine sql-Exception
				System.out.println("Dividende für user Id: "  + rs.getInt("aktie.fk_user"));
			}
			prepStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
	}

	public Double getDividendeInChFPerStk() {
		return dividendeInChFPerStk;
	}

	public void setDividendeInChFPerStk(Double dividendeInChFPerStk) {
		if(dividendeInChFPerStk != null){
			this.dividendeInChFPerStk = dividendeInChFPerStk;
		}
	}

	
	
	
}
