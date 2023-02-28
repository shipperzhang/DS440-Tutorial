package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String connectionURL = "jdbc:sqlserver://localhost;DatabaseName=DemoDB;username=sa;password=DS440@Demo;trustServerCertificate=true";
        try{
            try (Connection connection = DriverManager.getConnection(connectionURL)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String sql = new StringBuilder().append("SELECT * FROM Demo.Users ")
                        .append("WHERE username = ? AND password = ?;").toString();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();
                    response.setContentType("text/html");
                    PrintWriter out =response.getWriter();
                    if (resultSet.next()) {
                        out.println("<html><body>");
                        out.println("<h1> Welcome! " + resultSet.getString(4) + " " + resultSet.getString(5) + "!</h1>");
                        out.println("<a href=\"index.html\">Back to Login</a>");
                        out.println("</body></html>");
                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Login Failed!');");
                        out.println("location='index.html'");
                        out.println("</script>");
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
