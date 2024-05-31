package ids.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getMarkers")
public class GetMarkersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM markers");
            StringBuilder json = new StringBuilder();
            json.append("[");
            while (rs.next()) {
                if (json.length() > 1) json.append(",");
                json.append("{")
                        .append("\"id\":").append(rs.getInt("id")).append(",")
                        .append("\"title\":\"").append(rs.getString("title")).append("\",")
                        .append("\"description\":\"").append(rs.getString("description")).append("\",")
                        .append("\"latitude\":").append(rs.getDouble("latitude")).append(",")
                        .append("\"longitude\":").append(rs.getDouble("longitude")).append(",")
                        .append("\"imageUrl\":\"").append(rs.getString("imageUrl")).append("\"")
                        .append("}");
            }
            json.append("]");
            out.print(json.toString());
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
