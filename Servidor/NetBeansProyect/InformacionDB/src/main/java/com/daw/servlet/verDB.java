package com.daw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class verDB extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String baseDatos = request.getParameter("db");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet verDB</title>");            
            out.println("</head>");
            out.println("<body>");
            if (baseDatos!=null) {
                Connection conex = conectar("root", "root", baseDatos, "localhost");
                if (conex!=null) {
                    out.println("<h3>Base de datos: "+conex.getCatalog()+"</h3>");
                    DatabaseMetaData dbmd = conex.getMetaData();
                    ResultSet rs  = dbmd.getTables(conex.getCatalog(),null,"%",null);
                    out.println("tipo de base de datos: "+dbmd.getDatabaseProductName());
                    out.println("<br>");
                    out.println("versión: "+dbmd.getDatabaseProductVersion());
                    out.println("<br>");
                    out.println("nombre del driver: " +dbmd.getDriverName());
                    out.println("<br>");
                    out.println("versión del driver: "+dbmd.getDriverVersion());
                    out.println("<br>");
                    out.println("nombre del usuario: "+dbmd.getUserName());
                    out.println("<br>");
                    out.println("url de conexión: "+dbmd.getURL());
                    out.println("<br>");
                    out.println("<a href='index.html'>Volver</a>");
                    out.println("<br>");
                    while(rs.next()) {
                        String tabla = rs.getString(3);
                        out.println("<h3>Tabla: "+tabla+"</h3>");
                        out.println("<table border='1'>");
                        out.println("<tr><th>Columna</th><th>Tipo</th><th>Tamaño</th><th>Permite null</th></tr>");
                        ResultSet res = dbmd.getColumns(null, null, tabla, null);
                        while (res.next()) {
                            out.println("<tr>");
                            out.println("<td>"+res.getString("COLUMN_NAME")+"</td>");
                            out.println("<td>"+res.getString("TYPE_NAME")+"</td>");
                            out.println("<td>"+res.getString("COLUMN_SIZE")+"</td>");
                            String nulleable = res.getString("NULLABLE");
                            if ("0".equals(nulleable)) {
                                nulleable="false";
                            } else if ("1".equals(nulleable)) {
                                nulleable="true";
                            }
                            out.println("<td>"+nulleable+"</td>");
                            out.println("</tr>");
                        }
                        out.println("</table>");
                    }
                } else {
                    out.println("<h3>Error al conectar con la base de datos.</h3>");
                }
            } else {
                out.println("<h3>Error al leer la base de datos seleccionada</h3>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(verDB.class.getName()).log(Level.SEVERE, null, ex);
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
