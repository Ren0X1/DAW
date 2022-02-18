package com.daw.servlet;

import com.daw.DTO.Pelicula;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OperacionesDB extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Connection conex = conectar("root", "", "peliculas", "localhost");
            String op = request.getParameter("opera");
            Pelicula nuevaPelicula = (Pelicula)request.getAttribute("peli");
            if (op.equals("borrar")) {
                String codigo_borrar = request.getParameter("codigoBorrar");
                String sql = "DELETE FROM peliculas WHERE codigo_pelicula='"+codigo_borrar+"'";
                Statement stmt = conex.createStatement();
                stmt.execute(sql);
            } else if (op.equals("add")) {
                String nom = nuevaPelicula.getNombre();
                String dir = nuevaPelicula.getDirector();
                String gen = nuevaPelicula.getGenero();                        
                String imag = nuevaPelicula.getImagen();
                String estreno = request.getParameter("estreno");
                String publico = request.getParameter("publico");
                String fecha = request.getParameter("fecha");
                
                if (estreno==null) {
                    estreno = "0";
                } else {
                    estreno = "1";
                }
                if (publico==null) {
                    publico = "0";
                } else {
                    publico = "1";
                }
                String sql = "INSERT INTO peliculas VALUES ("+null+",?,?,?,?,?,?,?)";
                try {
                    PreparedStatement stmt = conex.prepareStatement(sql);
                    Date date=Date.valueOf(fecha);
                    stmt.setString(1, nom);
                    stmt.setString(2, dir);
                    stmt.setString(3, gen);
                    stmt.setInt(4, Integer.parseInt(estreno));
                    stmt.setInt(5, Integer.parseInt(publico));
                    stmt.setDate(6, date);
                    stmt.setString(7, imag);
                    stmt.execute();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace(out);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesDB.class.getName()).log(Level.SEVERE, null, ex);
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
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

