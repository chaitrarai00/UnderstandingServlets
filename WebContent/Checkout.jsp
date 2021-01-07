<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>
<% 
	if(session.getAttribute("user")==null)
		response.sendRedirect("login.html");
	String username=null;
	String sessionID=null;
	Cookie[] cookies=request.getCookies();
	if(cookies!=null){
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("user"))
				username=cookie.getValue();
		}
	}
%>
<h3>Hi <%=username %>, do the checkout</h3>
<form action="<%=response.encodeUrl("LogoutServlet") %>" method="post">
<input type="submit" value="Logout">
</form>
</body>
</html>