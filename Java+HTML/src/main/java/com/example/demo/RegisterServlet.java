package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String connectionURL = "jdbc:sqlserver://localhost;DatabaseName=DemoDB;username=sa;password=DS440@Demo;trustServerCertificate=true";

        try {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println("Connecting to the SQL Server ...");
            try (Connection connection = DriverManager.getConnection(connectionURL)) {
                System.out.println("Successfully connected.");
                String sql = new StringBuilder().append("INSERT Demo.Users (username, password, firstname, lastname) ")
                        .append("VALUES (?,?,?,?);").toString();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.setString(3, firstname);
                    statement.setString(4, lastname);
                    int res = statement.executeUpdate();
                    response.setContentType("text/html");
                    PrintWriter out =response.getWriter();
                    if (res > 0) {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Registration succeed!');");
                        out.println("location='index.html'");
                        out.println("</script>");
                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Registration failed!');");
                        out.println("location='register.html'");
                        out.println("</script>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
