<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="filesharing.Dbutil" %>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

String name=request.getParameter("a1");
String uname=request.getParameter("a2");
String pass=request.getParameter("a3");
try {
    Dbutil.connect();
    int i = Dbutil.st.executeUpdate("insert into userdet values('" + name + "','" + uname + "','" + pass + "')");
    if (i > 0) {
    	response.sendRedirect("Ulogin.html");
    } else {
        out.println("record not inserted");
    }
    Dbutil.con.close();
} catch (Exception e) {
    out.println(e);
}

%>
</body>
</html>