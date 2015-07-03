package ch.zkb.mytrade.controller;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.zkb.mytrade.model.Rolle;
import ch.zkb.mytrade.model.UserModel;

@WebFilter("/faces/private/admin/*")
public class AdminFilter implements Filter {

	
	boolean debug = true;
	private void debugOut(String meldung) {
		if(debug) {
			System.out.println("Debug MyAuthFilter." + meldung);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		debugOut("init(): AuthFilter...");
	}

/**
 * Versuche, die Session aus dem Request zu holen.
 * Ist das nicht möglich, so gehe über den FacesContext.
 */
	HttpSession holeSessionVariable(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(null == session) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			if(null == facesContext || null == facesContext.getExternalContext()) {
				debugOut("holeSessionVariable(): No session!");
			} else {
				session = (HttpSession) facesContext.getExternalContext().getSession(true);            		
				
			}
		}
		return session;
	}


	String meinPortfolioUrl = "http://localhost:8080/MyTrade/faces/private/mein_portfolio.xhtml";
	String loginUrl = "http://localhost:8080/MyTrade/faces/login.xhtml";



	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		try {
			debugOut("doFilter(): start...");

			eigenerDoHTTPFilter((HttpServletRequest)  req, 
			                    (HttpServletResponse) res, 
			                    chain);	

			debugOut("doFilter(): ... done.TraderoderAdmin");
		} catch (Exception ex) {
			System.out.println("Exception im MyAuthFilter " + ex);
			ex.printStackTrace();
		}
	}

	boolean istLoginURL(HttpServletRequest request) {
		String reqString = request.getRequestURI();
		debugOut("istLoginURL(): reqString: [" + reqString + "]");
		return reqString.contains(loginUrl);
	}

	/**
	 * Wie "doFilter", doch a) mit throws, statt try-catch und
	 *                      b) mit HttpServlet, statt Servlet
	 */
	private void eigenerDoHTTPFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(null == holeSessionVariable(request)) {
			response.sendRedirect(loginUrl);
			return;
		}

		if(istLoginURL(request)) {
			debugOut("eigenerDoHTTPFilter(): Request is login request!");
			chain.doFilter(request, response); // hier drauf darf eingentlich jeder
			return;
		}
		UserModel user = (UserModel) holeSessionVariable(request).getAttribute("currentUser");
		
		
		if(null == user){
			debugOut("eigenerDoHTTPFilter(): Trader ist angemeldet");
			response.sendRedirect(loginUrl);
			return;
		}
		
		if(user.getRolle() == Rolle.ADMINISTRATOR) {
			debugOut("eigenerDoHTTPFilter(): Administrator ist angemeldet");
			chain.doFilter(request, response); // jeder, da öffentlich	
			return;
		}
		if(user.getRolle() == Rolle.TRADER) {
			debugOut("eigenerDoHTTPFilter(): Trader ist angemeldet");
			response.sendRedirect(meinPortfolioUrl);
			return;
		}

	}
//	private void behandleLeereSession(HttpServletRequest  request,
//	            HttpServletResponse response,
//	            FilterChain         chain)
//	throws IOException, ServletException 
//	{
//	debugOut("behandleLeereSession(): Session ist null");
//	if(istOeffentlicheSeite(request) || istLoginURL(request)) {
//	chain.doFilter(request, response);	
//	} else {
//	response.sendRedirect(loginUrl);
//	}
//	}

	@Override
	public void destroy() {
		// must be overriden
	}
}
