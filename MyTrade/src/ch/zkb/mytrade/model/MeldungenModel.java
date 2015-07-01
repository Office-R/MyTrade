package ch.zkb.mytrade.model;

public class MeldungenModel {
	
	private static String meldung;
	private String meldung1;
	private String meldung2;
	private String meldung3;
	private String meldung4;
	private String meldung5;
	private String meldung6;
	private String meldung7;
	private String meldung8;
	private String meldung9;
	
	
	public String getMeldung() {
		meldung = "";
		return meldung;
	}
	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}
	public String getMeldung1() {
		meldung1 = "Logindaten sind falsch";
		return meldung1;
	}
	public void setMeldung1(String meldung1) {
		this.meldung1 = meldung1;
	}
	public String getMeldung2() {
		meldung2 = "Sie wurden erfolgreich angemeldet";
		return meldung2;
	}
	public void setMeldung2(String meldung2) {
		this.meldung2 = meldung2;
	}
	public String getMeldung3() {
		meldung3 = "Ihre Aktie wurde erfasst";
		return meldung3;
	}
	public void setMeldung3(String meldung3) {
		this.meldung3 = meldung3;
	}
	public String getMeldung4() {
		meldung4 = "Der Benutzer wurde erfasst";
		return meldung4;
	}
	public void setMeldung4(String meldung4) {
		this.meldung4 = meldung4;
	}
	public String getMeldung5() {
		meldung5 = "Dividenden wurden ausgeschüttet";
		return meldung5;
	}
	public void setMeldung5(String meldung5) {
		this.meldung5 = meldung5;
	}
	public String getMeldung6() {
		meldung6 = "Es ist ein Fehler aufgetreten, versuchen Sie es erneut.";
		return meldung6;
	}
	public void setMeldung6(String meldung6) {
		this.meldung6 = meldung6;
	}
	public String getMeldung7() {
		meldung7 = "Ihre Offenen Aufträge:";
		return meldung7;
	}
	public void setMeldung7(String meldung7) {
		this.meldung7 = meldung7;
	}
	public String getMeldung8() {
		meldung8 = "Ihre Aktie wurde verkauft";
		return meldung8;
	}
	public void setMeldung8(String meldung8) {
		this.meldung8 = meldung8;
	}
	public String getMeldung9() {
		meldung8 = "Sie haben die Aktie gekauft";
		return meldung9;
	}
	public void setMeldung9(String meldung9) {
		this.meldung9 = meldung9;
	}
	
	
	public String meldungausgeben(int meldungsNr){
		if (meldungsNr == 1){
			meldung = meldung1;
		}
		if (meldungsNr == 2){
			meldung = meldung2;
		}
		if (meldungsNr == 3){
			meldung = meldung3;
		}
		if (meldungsNr == 4){
			meldung = meldung4;
		}
		if (meldungsNr == 5){
			meldung = meldung5;
		}
		if (meldungsNr == 6){
			meldung = meldung6;
		}
		if (meldungsNr == 7){
			meldung = meldung7;
		}
		if (meldungsNr == 8){
			meldung = meldung8;
		}
		if (meldungsNr == 9){
			meldung = meldung9;
		} 
		
		return meldung;
	}
	
	
	
	
	

}
