<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.io.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Download Files</title>
</head>
<body>
    <h1>Download Files</h1>
    <%
        // Establish connection to MySQL database
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filemanage", "root", "tiger");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT filename FROM files");

            while (rs.next()) {
                String fileName = rs.getString("filename");
                out.println("<a href='DownloadServlet?file=" + fileName + "'>" + fileName + "</a><br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    %>
</body>
</html>
