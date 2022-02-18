package com.daw.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class ConsultarPeli extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Connection conex = conectar("root", "root", "peliculas", "localhost");
            String sql = "SELECT * FROM peliculas";
            Statement stmt = conex.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Result rslt = ResultSupport.toResult(rs);
            request.setAttribute("ResultadoTabla", rslt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarPeli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Connection conectar(String user, String passwd, String db, String server) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://"+server+"/"+db;
            Connection c = DriverManager.getConnection(url, user, passwd);
            return c;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            return null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
