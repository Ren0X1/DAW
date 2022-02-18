package com.daw.login;

import com.daw.peliculas.DAO.ActorJpaController;
import com.daw.peliculas.DAO.DirectorJpaController;
import com.daw.peliculas.DAO.GeneroJpaController;
import com.daw.peliculas.DAO.PeliculaJpaController;
import com.daw.peliculas.DAO.RepartoJpaController;
import com.daw.peliculas.DTO.Pelicula;
import com.daw.peliculas.DTO.Reparto;
import com.daw.peliculas.DTO.RepartoPK;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "OperacionAlta", urlPatterns = {"/OperacionAlta"})
//@MultipartConfig(location="subidaArchivos")
public class OperacionAlta extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("peliculasonlinealtasPU");
        PeliculaJpaController ctrPeliculas = new PeliculaJpaController(emf);
        RepartoJpaController ctrRepartos = new RepartoJpaController(emf);
        GeneroJpaController ctrGeneros = new GeneroJpaController(emf);
        DirectorJpaController ctrDirectores = new DirectorJpaController(emf);
        ActorJpaController ctrActores = new ActorJpaController(emf);
        String titulo1 = request.getParameter("titulo");
        String director1 = request.getParameter("director");
        String estreno1 = request.getParameter("estreno"); 
        String genero1 = request.getParameter("genero");
        String actor1 = request.getParameter("actores");
        /*RequestDispatcher rd1=request.getRequestDispatcher("creaCarpeta");  
        rd1.include(request, response);*/
        if (titulo1 != null && director1 != null && genero1 != null) {            
            Pelicula pelicula = new Pelicula(ctrPeliculas.getPeliculaCount()+1);
            pelicula.setTitulo(titulo1);
            pelicula.setCodDirector(ctrDirectores.findDirector(Integer.parseInt(director1)));
            pelicula.setCodGenero(ctrGeneros.findGenero(Integer.parseInt(genero1)));
            pelicula.setEstreno(Integer.parseInt(estreno1));
            /*String fileName = "";
            Collection<Part> elementos = request.getParts();
            for(Part p : elementos) {
                p.getName();
                p.getSubmittedFileName();
                p.getContentType();
                p.getInputStream();

                String contenidoHeader = p.getHeader("content-disposition");
                String[] cabeceras = contenidoHeader.split(";");
                for(String s : cabeceras) {
                    if(s.trim().startsWith("filename")) {
                        int pos = s.indexOf('=');
                        fileName = s.substring(pos+1);
                        p.write(fileName.replace("\"", ""));
                    }
                }
            }*/
            String fileName=request.getParameter("foto");
            pelicula.setImagen(fileName);
            pelicula.setPuntuacion(0);
            ctrPeliculas.create(pelicula);
            Reparto reparto = new Reparto();
            reparto.setRepartoPK(new RepartoPK());
            reparto.setPelicula(pelicula);
            reparto.setActor(ctrActores.findActor(Integer.parseInt(actor1)));
            reparto.setMinutos(0);
            try {
                ctrRepartos.create(reparto);
            } catch (Exception ex) {
                Logger.getLogger(OperacionAlta.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd=request.getRequestDispatcher("index3.html");  
            rd.include(request, response);
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
