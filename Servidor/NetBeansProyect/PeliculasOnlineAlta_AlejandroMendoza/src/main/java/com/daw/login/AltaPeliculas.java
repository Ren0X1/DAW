package com.daw.login;

import com.daw.peliculas.DAO.ActorJpaController;
import com.daw.peliculas.DAO.DirectorJpaController;
import com.daw.peliculas.DAO.GeneroJpaController;
import com.daw.peliculas.DTO.Actor;
import com.daw.peliculas.DTO.Director;
import com.daw.peliculas.DTO.Genero;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AltaPeliculas", urlPatterns = {"/AltaPeliculas"})
public class AltaPeliculas extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("peliculasonlinealtasPU");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AltaPeliculas</title>");            
            out.println("</head>");
            out.println("<body>");
                    GeneroJpaController ctrGeneros = new GeneroJpaController(emf);
                    DirectorJpaController ctrDirectores = new DirectorJpaController(emf);
                    ActorJpaController ctrActores = new ActorJpaController(emf);
                out.println("<h1>Bienvenido al alta de Peliculas</h1>");
                 out.println("<form action=\"OperacionAlta\">  ");
                 out.println("Titulo:<input type=\"text\" name=\"titulo\"/><br/>");
                 out.println("Director:<select name=\"director\">");
                    List<Director> listaDirecotres = ctrDirectores.findDirectorEntities();
                    for (Director director : listaDirecotres) {
                        out.println("<option value="+director.getCodDIrector()+">"+director.getNombreDirector()+"</option>");
                    }
                 out.println("</select><br/>");
                 out.println("Generos:<select name=\"genero\">");
                    List<Genero> listaGeneros = ctrGeneros.findGeneroEntities();
                    for (Genero genero : listaGeneros) {
                        out.println("<option value="+genero.getCodGenero()+">"+genero.getGenero()+"</option>");
                    }
                 out.println("</select><br/>");
                 out.println("Actores:<select name=\"actores\" multiple>");
                    List<Actor> listaActores = ctrActores.findActorEntities();
                    for (Actor actor : listaActores) {
                        out.println("<option value="+actor.getCodActor()+">"+actor.getNombreActor()+"</option>");
                    }
                 out.println("</select><br/>");
                 out.println("Estreno:<input type=\"text\" name=\"estreno\"/><br/>");
                 out.println("Imagen:<input type=\"file\" name=\"foto\"/><br/>");
                 out.println("<input type=\"submit\" value=\"Dar de alta\">");
                out.println("</form>");
                out.println("<a href=\"index.html\">Volver al inicio</a>");
            out.println("</body>");
            out.println("</html>");
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
