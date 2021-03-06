package ch.zkb.mytrade.model;

/**
 * Model das die Symbol tabelle der DB abbildet
 * 
 * @version 1.0
 * @author Robin.Frehner
 *
 */
public class SymbolModel {
	private String symbol;
	private int symbol_id;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getSymbol_id() {
		return symbol_id;
	}

	public void setSymbol_id(int symbol_id) {
		this.symbol_id = symbol_id;
	}

}
