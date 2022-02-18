package com.daw.institutorelacionalv2.LN;

import com.daw.institutorelacionalv2.DAO.AlumnoJpaController;
import com.daw.institutorelacionalv2.DAO.AsignaturaJpaController;
import com.daw.institutorelacionalv2.DTO.Alumno;
import com.daw.institutorelacionalv2.DTO.Asignatura;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultasBD extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InstitutoRelacionalV2PU");
        AlumnoJpaController ctrAlumno = new AlumnoJpaController(emf);
        AsignaturaJpaController ctrAsignatura = new AsignaturaJpaController(emf);
       
        List<Asignatura> listadoAsignatura = ctrAsignatura.findAsignaturaEntities();
        List<Alumno> listadoAlumnos = ctrAlumno.findAlumnoEntities();
        
        request.setAttribute("alumnos", listadoAlumnos);
        request.setAttribute("asignaturas", listadoAsignatura);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}