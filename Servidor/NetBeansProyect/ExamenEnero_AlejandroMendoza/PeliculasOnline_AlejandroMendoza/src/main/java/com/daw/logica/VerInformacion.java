package com.daw.logica;

import com.daw.DAO.*;
import com.daw.DTO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VerInformacion", urlPatterns = {"/VerInformacion"})
public class VerInformacion extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.println("<tr>");
            String id = request.getParameter("codigo");
            if (id != null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.daw_PeliculasOnline_AlejandroMendoza_war_1.0-SNAPSHOTPU");
                PeliculaJpaController ctrPeliculas = new PeliculaJpaController(emf);
                RepartoJpaController ctrRepartos = new RepartoJpaController(emf);
                ActorJpaController ctrActores = new ActorJpaController(emf);
                OpinionJpaController ctrOpiniones = new OpinionJpaController(emf);
                
                int codPeli = 0;
                List<Pelicula> listaPeliculas = ctrPeliculas.findPeliculaEntities();
                for (Pelicula pelicula : listaPeliculas) {
                    if (pelicula.getTitulo().equals(id)) {
                        codPeli = pelicula.getCodPelicula();
                    }
                }
                out.println("<td>");
                out.println("<h1>Titulo: "+ctrPeliculas.findPelicula(codPeli).getTitulo()+"</h1>");
                String imagen = ctrPeliculas.findPelicula(codPeli).getImagen();
                out.println("<img src=\"./img/"+imagen+".jpg\">");
                out.println("<h3>Casting:</h3>");
                List<Reparto> listaReparto = ctrRepartos.findRepartoEntities();
                for (Reparto reparto : listaReparto) {
                    if(reparto.getRepartoPK().getCodPelicula()==codPeli) {
                        int codActor = reparto.getRepartoPK().getCodActor();
                        String nombreActor = ctrActores.findActor(codActor).getNombreActor();
                        out.println("<p>"+nombreActor+"</p>");
                    }
                }
                out.println("</td>");
                ArrayList<String> Calculo =  new ArrayList<>();
                out.println("<td>");
                out.println("<h3>Comentarios:</h3>");
                List<Opinion> listaOpiniones = ctrOpiniones.findOpinionEntities();
                for (Opinion opinion : listaOpiniones) {
                    if(opinion.getCodPelicula()==codPeli) {
                        out.println("<p>"+opinion.getComentario()+" - "+opinion.getPuntuacion()+"</p>");
                        Calculo.add(opinion.getPuntuacion()+"");
                    }
                }
                if(!Calculo.isEmpty()) {
                    int i = 0;
                    int suma=0;
                    for (String x : Calculo) {
                        suma=suma+Integer.parseInt(x);
                        i++;
                    }

                    int media = Math.round(suma/i);
                    switch (media) {
                        case 1:
                            out.println("<img src=\"./img/1.jpg\">");
                            break;
                        case 2:
                            out.println("<img src=\"./img/2.jpg\">");
                            break;
                        case 3:
                            out.println("<img src=\"./img/3.jpg\">");
                            break;
                        case 4:
                            out.println("<img src=\"./img/4.jpg\">");
                            break;
                        case 5:
                            out.println("<img src=\"./img/5.jpg\">");
                            break;
                        default:
                            break;
                    }
                }
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println("<h4>Actor</h4>");
                out.println("<form action=\"AddActor\">");
                    out.println("</select><br/>");
                     out.println("Actores:<select name=\"actor\">");
                        List<Actor> listaActores = ctrActores.findActorEntities();
                        for (Actor actor : listaActores) {
                            out.println("<option value="+actor.getCodActor()+">"+actor.getNombreActor()+"</option>");
                        }
                     out.println("</select><br/>");
                     out.println("<input type=\"hidden\" value="+codPeli+" name=\"peli\">");
                     out.println("<input type=\"submit\" value=\"Add Actor\">");
                out.println("</form>");
                out.println("</td>");
                out.println("<td>");
                out.println("<h4>Comentario</h4>");
                out.println("<form action=\"AddComentario\">");
                     out.println("<input type=\"text\" name=\"comen\">");
                     out.println("<input type=\"number\" min=\"1\" max=\"5\" value=\"1\" name=\"puntu\">");
                     out.println("<input type=\"hidden\" value="+codPeli+" name=\"peli\">");
                     out.println("<input type=\"submit\" value=\"Add Comentario\">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            } else {
                out.println("<h1>Error al ejecutar la sentencia</h1>");
            }
            out.println("</table>");
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
