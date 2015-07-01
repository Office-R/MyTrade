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
	
	public DividendeDao() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized ArrayList<SymbolModel> loadSymbol(){
		ConnectionPooling pooling;
		ArrayList<SymbolModel> symbole = new ArrayList<SymbolModel>();
		pooling        = ConnectionPoolingImplementation.getInstance(1, 5);

		Connection c1  = pooling.getConnection();
		PreparedStatement prepStmt;

		try {
			String sqlQuery = "SELECT symbol.symbol_id, symbol.symbol";
			prepStmt = (PreparedStatement) c1.prepareStatement(sqlQuery);
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()) {
				SymbolModel symbolModel = new SymbolModel();
				symbolModel.setSymbol_id(rs.getInt("symbol.symbol_id"));
				symbolModel.setSymbol_id(rs.getInt("symbol.symbol"));
				symbole.add(symbolModel);
			}
			prepStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pooling.putConnection(c1);
		return symbole;
	}
}
