package com.servlets.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "A servlet Page for logging in", 
		urlPatterns = { "/LoginServlet" },
		initParams = {
				@WebInitParam(name="user", value="Chai"),
				@WebInitParam(name="password", value="pwd")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String HTML_START="<html><body>";
	private static final String HTML_END="</html></body>";
	/**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	if(getServletContext().getInitParameter("DBURL").equals("jdbc:mysql://localhost/mysql_db") &&
    			getServletContext().getInitParameter("DBUser").equals("mysql_user") &&
    			getServletContext().getInitParameter("DBpassword").equals("mysql_pwd"))
    		getServletContext().setAttribute("DBSTATUS", "True");
    	else throw new ServletException("DBConnectionError");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * using HttpServletRequest getSession method the HttpSession object is 
	 * created along with a cookie added to response with name JSESSIONID
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		String password=request.getParameter("password");
		String user_param=getServletConfig().getInitParameter("user");
		String password_param=getServletConfig().getInitParameter("password");
		log("user:"+user+"password"+password);
		if(user.equals(user_param) && password.equals(password_param)) {
			HttpSession session=request.getSession();
			session.setAttribute("user", "Chai");
			session.setMaxInactiveInterval(30*60);
			Cookie usernamecookie=new Cookie("user", user);
			usernamecookie.setMaxAge(30*60);
			response.addCookie(usernamecookie);
			//cookies are disables and thats only when urlrewriting is used
			String encodeURL=response.encodeRedirectUrl("LoginSuccess.jsp");
			response.sendRedirect(encodeURL);
		}
		else {
			RequestDispatcher rd= getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out=response.getWriter();
			out.println("<font color=red> Either username or password is wrong</font>");
			rd.include(request, response);
		}
	}

}
