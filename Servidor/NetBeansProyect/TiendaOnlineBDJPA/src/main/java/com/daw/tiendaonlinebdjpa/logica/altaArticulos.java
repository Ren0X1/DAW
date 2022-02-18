/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.tiendaonlinebdjpa.logica;

import com.daw.tiendaonlinebdjpa.DAO.ArticuloJpaController;
import com.daw.tiendaonlinebdjpa.DTO.Articulo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Lola
 */
@MultipartConfig(location = "C:/subidaImg")
public class altaArticulos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deno = request.getParameter("denominacion");
        String cat = request.getParameter("categoria");
        String pp=request.getParameter("precio");
        int precio = Integer.parseInt(pp);
        int exix = Integer.parseInt(request.getParameter("existencias"));
        int cProv = Integer.parseInt(request.getParameter("codproveedor"));
        String imagen = "";
        Collection<Part> elementos = request.getParts();
        Part p=request.getPart("imagen");
       imagen=p.getSubmittedFileName();
                  p.write(imagen);
        
        Articulo a = new Articulo(null, deno, cat, precio, exix, precio, imagen);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("juegosJPU");
        ArticuloJpaController ctrArt = new ArticuloJpaController(emf);
        ctrArt.create(a);
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
