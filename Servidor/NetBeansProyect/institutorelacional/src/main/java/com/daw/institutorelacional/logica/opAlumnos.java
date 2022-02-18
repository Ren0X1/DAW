/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacional.logica;

import com.daw.institutorelacional.DAO.AlumnoJpaController;
import com.daw.institutorelacional.DAO.AsignaturaJpaController;
import com.daw.institutorelacional.DAO.MatriculaJpaController;
import com.daw.institutorelacional.DAO.exceptions.IllegalOrphanException;
import com.daw.institutorelacional.DAO.exceptions.NonexistentEntityException;
import com.daw.institutorelacional.DTO.Alumno;
import com.daw.institutorelacional.DTO.Asignatura;
import com.daw.institutorelacional.DTO.Matricula;
import com.daw.institutorelacional.DTO.MatriculaPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class opAlumnos extends HttpServlet {

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
        AsignaturaJpaController ctrAsignatura = new AsignaturaJpaController(emf);
        
        List<Alumno> listaAlumnos = ctrAlumno.findAlumnoEntities();
        List<Asignatura> listaAsignaturas = ctrAsignatura.findAsignaturaEntities();
        
        String operacion = request.getParameter("op");
        
        if (operacion != null) {
            
            switch (operacion) {
                case "Asignaturas":
                    int codalum = Integer.parseInt(request.getParameter("codalum"));
                    
                    List<Matricula> listaMat = ctrAlumno.findAlumno(codalum).getMatriculaList();
                    ArrayList<String> listaAsigs = new ArrayList();
                    for (Matricula asig : listaMat) {
                        listaAsigs.add(asig.getAsignatura().getNombreasig());
                    }
                    
                    request.setAttribute("listaAsigs", listaAsigs);
   
                break;

                case "Alumnnos":
                    int codasig = Integer.parseInt(request.getParameter("codasig"));
                    
                    List<Matricula> listaMat2 = ctrAsignatura.findAsignatura(codasig).getMatriculaList();
                    ArrayList<String> listaAlumns = new ArrayList();
                    for (Matricula alum : listaMat2) {
                        listaAlumns.add(alum.getAlumno().getNombrealumno());
                    }
                    
                    request.setAttribute("listaAlumns", listaAlumns);
                    
                break;

            }
            
        }
        
        request.setAttribute("listaAlumnos", listaAlumnos);
        request.setAttribute("listaAsignaturas", listaAsignaturas);

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
