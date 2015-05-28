package ch.zkb.mytrade.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "helloWorld", eager = true)
@SessionScoped
public class HelloWorld {
	Integer nr = 1;
	String name = "Gabriel";
	String message = "test";
	
	public String greet(){
		nr++;
		return "welcome?faces-redirect=true";
	}
	public String getMessage() {
		message = name + nr.toString();
		return message;
	}
	
	public int getNr() {
		return nr;
	}

	
	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HelloWorld() {
		System.out.println("HelloWorld started!");
	}

}