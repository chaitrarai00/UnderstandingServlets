<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login successful</title>
</head>
<body>
	<%
	String user=null;
	if(session.getAttribute("user")==null)
	{
		response.sendRedirect("/login.html");
	}
	else{
		user=(String)session.getAttribute("user");
	}
	String username=null;
	String sessionid=null;
	Cookie[] cookies=request.getCookies();
	if(cookies !=null)
	{
		for(Cookie cookie:cookies)
		{
		if(cookie.getName().equals("user"))
			username=cookie.getValue();
		if(cookie.getName().equals("JSESSIONID"))
			sessionid=cookie.getValue();
		}
	}
	if(username==null)
		response.sendRedirect("login.html");
	%>
	<h3>Hi <%=username %>, Login Successful. Your sessionId is <%=sessionid %></h3><br>
	User: <%=user %>
	<br>
	<a href="<%=response.encodeUrl("Checkout.jsp") %>">Checkout Page</a>
	<form action="<%=response.encodeUrl("LogoutServlet") %>" method="post">
		<input type="submit" value="Log out">
	</form>
</body>
</html>