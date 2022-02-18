// Java Document

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

 
@WebServlet("/GuardaPanel") 
public class GuardaPanel extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    FileWriter fw;
    PrintWriter pw;
 
    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     * @throws javax.servlet.ServletException
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
      
        String datos = request.getParameter("jugada");
        String consulta,id,name;
        JSONObject obj = new JSONObject();
                  
            try {
      
                fw= new FileWriter("C:\\Users\\Public\\caras2.txt",true);
                
                pw= new PrintWriter(fw);
                pw.println(datos);
             
                pw.close();
                
                JSONObject arrayObj = new JSONObject();
                arrayObj.put("guardado","guardado");
                response.getWriter().print(arrayObj.toString());
                
                
            } catch (IOException ex) {
                System.out.println("ERROR CREANDO EL FICHERO....");
            } catch (JSONException ex) {
                Logger.getLogger(GuardaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }  
   //----------------------------
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request,response);
    
    }
    //----------
    
}