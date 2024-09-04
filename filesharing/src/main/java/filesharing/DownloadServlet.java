package filesharing;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");

        try {
            // Establish MySQL database connection
            Class.forName("com.mysql.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/filemanage";
            String username = "root";
            String password = "tiger";
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            // Retrieve file data from the database
            PreparedStatement stmt = conn.prepareStatement("SELECT filedata FROM files WHERE filename = ?");
            stmt.setString(1, fileName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("filedata");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();

                response.setContentType("application/octet-stream");
                response.setContentLength(fileLength);
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

