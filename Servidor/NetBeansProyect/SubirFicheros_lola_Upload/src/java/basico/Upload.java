package basico;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Marcos Y Ale
 */
public class Upload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        //Ruta donde se guardara el fichero
        File destino = new File("C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8_Servidores\\webapps\\files\\");
        // Convertimos el HTTPRequest en un ContextRequest
        ServletRequestContext src = new ServletRequestContext(request);

        //Para evitar errores comprobamos que el formulario tenga enctype=multipart/form-data
        if (ServletFileUpload.isMultipartContent(src)) {
            //Necesario para evitar errores de NullPointerException
            DiskFileItemFactory factory = new DiskFileItemFactory((1024 * 1024), destino);
            //Creamos un FileUpload
            ServletFileUpload upload = new ServletFileUpload(factory);
            //Procesamos el request para que nos devuelva una lista
            //con los parametros y ficheros.
            List lista = null;
            try {
                lista = upload.parseRequest(src);
            } catch (FileUploadException ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            }
            File file = null;
            //Recorremos la lista.
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                //Rescatamos el fileItem
                FileItem item = (FileItem) it.next();
                //Comprobamos si es un campo de formulario
                if (item.isFormField()) //Hacemos lo que queramos con el.
                {
                    out.println("\nFichero añadido con éxito");
                } else {
                    //Si no, es un fichero y lo subimos al servidor.
                    //Primero creamos un objeto file a partir del nombre del fichero.
                    file = new File(item.getName());
                    try {
                        //Lo escribimos en el disco
                        // usando la ruta donde se guardara el fichero
                        // y cogiendo el nombre del file
                        // Nota: Se podria hacer usando el objeto item en vez del file directamente
                        // Pero esto puede causar incompatibilidades segun que navegador, ya que
                        // algunos solo pasan el nombre del fichero subido, pero otros
                        // como Iexplorer, pasan la ruta absoluta, y esto crea un pequeño problema al escribir
                        // el fichero en el servidor.
                        item.write(new File(destino, file.getName()));
                    } catch (Exception ex) {
                        Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.println("<a href='http://localhost:8084/files/"+file.getName()+"' target='_blank'>Pincha aquí para acceder al archivo</a>");

                }
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
