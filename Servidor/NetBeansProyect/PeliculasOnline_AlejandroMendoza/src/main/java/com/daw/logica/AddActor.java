package com.daw.logica;

import com.daw.DAO.RepartoJpaController;
import com.daw.DTO.Reparto;
import com.daw.DTO.RepartoPK;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddActor", urlPatterns = {"/AddActor"})
public class AddActor extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actor = request.getParameter("actor");
        String peli = request.getParameter("peli");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.daw_PeliculasOnline_AlejandroMendoza_war_1.0-SNAPSHOTPU");
        RepartoJpaController ctrRepartos = new RepartoJpaController(emf);
        Reparto reparto = new Reparto();
        RepartoPK repartoPK = new RepartoPK();
        repartoPK.setCodActor(Integer.parseInt(actor));
        repartoPK.setCodPelicula(Integer.parseInt(peli));
        reparto.setRepartoPK(repartoPK);
        try {
            ctrRepartos.create(reparto);
        } catch (Exception ex) {
            Logger.getLogger(AddActor.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
        rd.forward(request, response);
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
