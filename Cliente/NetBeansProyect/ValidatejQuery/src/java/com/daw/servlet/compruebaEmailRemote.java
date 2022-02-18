package com.daw.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "ConsultaUsuario", urlPatterns = {"/consulta"})
public class compruebaEmailRemote extends HttpServlet {
    private String userName;
    private String password;
    private String url;
    private String driver;
    String nombre = "";
    private Integer contador = 1;
    private ServletContext context;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mail = "";
        boolean acceso = false;
        try {
            mail = request.getParameter("Email");
            acceso = compruebaUsuario(mail);
            out.println(acceso);
            out.flush();
        } catch (Exception ex) {
            out.println(ex);
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    public boolean compruebaUsuario(String correo) {
        try {
            Connection conn = null;
            Statement stmt = null;
            String sqlStr = "";
            userName = "root";
            password = "root";
            url = "jdbc:mysql://localhost/validate_jqueary";
            driver = "com.mysql.jdbc.Driver";

            try {
                Class.forName(driver).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            sqlStr = "select * from usuarios where email ='" + correo + "'";
            ResultSet rset = stmt.executeQuery(sqlStr);

            if (rset.next()) {
                return false;
            } else return true;

        } catch (SQLException ex) {
            Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}