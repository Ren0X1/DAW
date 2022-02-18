/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.peliculasjpaversion1.LogicaN;

import com.daw.peliculasjpaversion1.DAO.PeliculasJpaController;
import com.daw.peliculasjpaversion1.DTO.Peliculas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lola
 */
public class consultas extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet consultas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet consultas at " + request.getContextPath() + "</h1>");
          EntityManagerFactory emf =Persistence.createEntityManagerFactory("peliculasJPAVersion1PU");
            PeliculasJpaController ctrPeliculas =new PeliculasJpaController(emf);
            List <Peliculas> lista =  ctrPeliculas.peliculasPorGenero("drama");
            
            for (Peliculas p:lista){
                
            }
            List <Peliculas> lista2=ctrPeliculas.consultaMixta("aventuras", true);
            for (Peliculas p: lista2){
                
            }
            List ListaPeliDirector=  ctrPeliculas.peliculaDirector();
            for  (Object o:ListaPeliDirector){
               Object [] aObj=(Object [])o;
               out.println(aObj[0]+ "</br>");
                 out.println(aObj[1]+ "</br>");            
                 
               
            }
            
          
                    
            out.println("</body>");
            out.println("</html>");
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
