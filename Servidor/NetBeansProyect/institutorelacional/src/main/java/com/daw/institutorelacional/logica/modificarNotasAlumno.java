/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacional.logica;

import com.daw.institutorelacional.DAO.AlumnoJpaController;
import com.daw.institutorelacional.DAO.MatriculaJpaController;
import com.daw.institutorelacional.DAO.exceptions.NonexistentEntityException;
import com.daw.institutorelacional.DTO.Alumno;
import com.daw.institutorelacional.DTO.Matricula;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian
 */
public class modificarNotasAlumno extends HttpServlet {

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
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("institutoPU");   
        AlumnoJpaController ctrAlumno = new AlumnoJpaController(emf);
        MatriculaJpaController ctrMatricula = new MatriculaJpaController(emf);
        
        String notasAl1[] = request.getParameterValues("notaAl1");
        String notasAl2[] = request.getParameterValues("notaAl2");
        String notasAl3[] = request.getParameterValues("notaAl3");
        
        int codAlMod = Integer.parseInt(request.getParameter("codAlMod"));
        
        Alumno alSeleccionado = ctrAlumno.findAlumno(codAlMod);
        Matricula matAMod = new Matricula();
        
        
            
        for (int i = 0; i < notasAl1.length; i++) {
            matAMod = alSeleccionado.getMatriculaList().get(i);
            matAMod.setNota1(Integer.parseInt(notasAl1[i]));

            
            
            try {
                ctrMatricula.edit(matAMod);
            } catch (Exception ex) {
                Logger.getLogger(modificarNotasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < notasAl2.length; i++) {
            matAMod = alSeleccionado.getMatriculaList().get(i);
            matAMod.setNota2(Integer.parseInt(notasAl2[i]));

            try {
                ctrMatricula.edit(matAMod);
            } catch (Exception ex) {
                Logger.getLogger(modificarNotasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < notasAl3.length; i++) {
            matAMod = alSeleccionado.getMatriculaList().get(i);
            matAMod.setNota3(Integer.parseInt(notasAl3[i]));

            try {
                ctrMatricula.edit(matAMod);
            } catch (Exception ex) {
                Logger.getLogger(modificarNotasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        for (int i = 0; i < alSeleccionado.getMatriculaList().size(); i++) {
            int suma = Integer.parseInt(notasAl1[i]) + Integer.parseInt(notasAl2[i]) + Integer.parseInt(notasAl3[i]);
            
            matAMod = alSeleccionado.getMatriculaList().get(i);
            matAMod.setNotafinal(suma/3);
            
            try {
                ctrMatricula.edit(matAMod);
            } catch (Exception ex) {
                Logger.getLogger(modificarNotasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
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
