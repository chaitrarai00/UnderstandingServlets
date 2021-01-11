package com.filters.example;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter{
	private ServletContext context;
	public void init(FilterConfig filterConfig) throws ServletException {	
		this.context=filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		String uri=req.getRequestURI();
		this.context.log("Requested resource"+uri);
		
		HttpSession session=req.getSession(false);//if session exists it returns the session else null
		
		if(session==null && !(uri.endsWith("html")||uri.endsWith("LoginServlet"))) {
			this.context.log("Unauthorized Access");
			res.sendRedirect("login.html");
		}
		else {
			chain.doFilter(request, response);
		}
	}
	public void destroy() {
	//close resources if needed
	}
}
