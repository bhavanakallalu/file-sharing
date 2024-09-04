package filesharing;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.sql.*;

@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Line causing the error
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/filemanage";
            String username = "root";
            String password = "tiger";
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO files (filename, filedata) VALUES (?, ?)");
            stmt.setString(1, fileName);
            stmt.setBlob(2, fileContent);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("upload.jsp");
    }
}
