package com.daw.institutorelacionalv2.LN;

import com.daw.institutorelacionalv2.DAO.AlumnoJpaController;
import com.daw.institutorelacionalv2.DAO.AsignaturaJpaController;
import com.daw.institutorelacionalv2.DAO.MatriculaJpaController;
import com.daw.institutorelacionalv2.DAO.exceptions.IllegalOrphanException;
import com.daw.institutorelacionalv2.DAO.exceptions.NonexistentEntityException;
import com.daw.institutorelacionalv2.DTO.Alumno;
import com.daw.institutorelacionalv2.DTO.Asignatura;
import com.daw.institutorelacionalv2.DTO.Matricula;
import com.daw.institutorelacionalv2.DTO.MatriculaPK;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestionBD extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacion = request.getParameter("operacion");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InstitutoRelacionalV2PU");
        AlumnoJpaController ctrAlumno = new AlumnoJpaController(emf);
        MatriculaJpaController ctrMatricula = new MatriculaJpaController(emf);
        AsignaturaJpaController ctrAsignatura = new AsignaturaJpaController(emf);

        int nMatricula = Integer.parseInt(request.getParameter("alumno"));

        switch (operacion) {
            case "borrarAlumno": 
                try {
                    ctrAlumno.destroy(nMatricula);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;


            case "altaMatricula":
                int codAsig = Integer.parseInt(request.getParameter("asignatura"));
                int nota1 = Integer.parseInt(request.getParameter("nota1"));
                int nota2 = Integer.parseInt(request.getParameter("nota2"));
                int nota3 = Integer.parseInt(request.getParameter("nota3"));

                int notafinal = (nota1 + nota2 + nota3) / 3;

                MatriculaPK matriculaPK = new MatriculaPK(nMatricula, codAsig);

                Matricula matricula = new Matricula(matriculaPK);
                matricula.setNota1(nota1);
                matricula.setNota2(nota2);
                matricula.setNota3(nota3);
                matricula.setNotafinal(notafinal);
                
                Alumno alumno = ctrAlumno.findAlumno(nMatricula);
                Asignatura asignatura = ctrAsignatura.findAsignatura(codAsig);
                
                matricula.setAlumno(alumno);
                matricula.setAsignatura(asignatura);

                    try {
                        ctrMatricula.create(matricula);
                    } catch (Exception ex) {
                        Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
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
