<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="filesharing.Dbutil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String uname=request.getParameter("t1");
String pass=request.getParameter("t2");
try
{
	Dbutil.connect();
	int i=Dbutil.st.executeUpdate("update userdet set usern='"+uname+"' where usern='"+uname+"'&&pass='"+pass+"'");
	if(i>0){
		response.sendRedirect("userpage.html");
		}
	else{
		out.println("Invalid Credentials");
	}
	}
catch(Exception e)
{
	out.println(e);
}
%>
</body>
</html>