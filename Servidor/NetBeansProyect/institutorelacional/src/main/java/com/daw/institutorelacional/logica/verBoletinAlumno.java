/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacional.logica;

import com.daw.institutorelacional.DAO.AlumnoJpaController;
import com.daw.institutorelacional.DAO.MatriculaJpaController;
import com.daw.institutorelacional.DTO.Alumno;
import com.daw.institutorelacional.DTO.Matricula;
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
 * @author Christian
 */
public class verBoletinAlumno extends HttpServlet {

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
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("institutoPU");   
            AlumnoJpaController ctrAlumno = new AlumnoJpaController(emf);
            
            int codalum = Integer.parseInt(request.getParameter("codalum"));
            Alumno alumnoBoletin = ctrAlumno.findAlumno(codalum);
            
            request.setAttribute("alumnoBoletin", alumnoBoletin);
            
            // Nombres de asignaturas
            
            ArrayList<String> listaNombresAsignaturas = new ArrayList();

            List<Matricula> listaAsignaturasMat = alumnoBoletin.getMatriculaList();
            
            for (Matricula mat : listaAsignaturasMat) {
                listaNombresAsignaturas.add(mat.getAsignatura().getNombreasig());
            }
            
            request.setAttribute("listaNombresAsignaturas", listaNombresAsignaturas);
            
            // ---
            
            // Columna editable
            
            int colEditable = -1;
            
            for (int i = 0; i < 3 && colEditable == -1; i++) { // Recorro por columnas
                for (int j = 0; j < listaNombresAsignaturas.size() && colEditable == -1; j++) {
                    switch (i) {
                        case 0:
                            if (listaAsignaturasMat.get(j).getNota1() == 0) {
                                colEditable = i;
                            }
                        break;
                        case 1:
                            if (listaAsignaturasMat.get(j).getNota2() == 0) {
                                colEditable = i;
                            }
                        break;
                        case 2:
                            if (listaAsignaturasMat.get(j).getNota3() == 0) {
                                colEditable = i;
                            }
                        break;     
                    }
                }
            }
            
            request.setAttribute("colEditable", colEditable);
            
            //---
            
            //--- Editar matrícula
            
            
            
            
            
            //---
        
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
