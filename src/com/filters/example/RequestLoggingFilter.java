package com.filters.example;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
	
	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 this.context=filterConfig.getServletContext();
		 this.context.log("RequestLoggingFilter initialized");
	}
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * Logging in request processing
		 */
		HttpServletRequest req=(HttpServletRequest)request;
		Enumeration<String> params=req.getParameterNames();
		while(params.hasMoreElements()) {
			String name=params.nextElement();
			String value=request.getParameter(name);
			this.context.log(req.getRemoteAddr()+"::RequestParams::{"+name+"="+value+"}");
		}
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				this.context.log(req.getRemoteAddr()+"::RequestParams::{"+cookie.getName()+"="+cookie.getValue()+"}");
			}
		}
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	
	}
}
