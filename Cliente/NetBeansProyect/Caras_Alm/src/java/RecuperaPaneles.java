// Java Document

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

 
@WebServlet("/RecuperaPaneles") 
public class RecuperaPaneles extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    FileReader fr;
    BufferedReader br;
 
    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     * @throws javax.servlet.ServletException
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, FileNotFoundException {
       
      
        String consulta,id,name;
        JSONObject obj = new JSONObject();
      
            File file= new File("\\Users\\Public\\caras2.txt");
            String linea;
            Integer i=1;
             JSONObject arrayObj = new JSONObject();
            try {
                //fr= new FileReader("C:\\Users\\Usuario\\Documents\\78_Clases\\Cliente\\AjaX_78\\Caras\\caras.txt");
                fr= new FileReader(file);
                br= new BufferedReader(fr);
                
                while( (linea=br.readLine())!=null ){
                 arrayObj.put(i.toString(),linea);
                 i++;
                }
                br.close();
               
                response.getWriter().print(arrayObj.toString());
                
                
            } catch (IOException ex) {
                System.out.println("ERROR CREANDO EL FICHERO....");
            } catch (JSONException ex) {
                Logger.getLogger(GuardaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     
        }
    
    //----------
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request,response);
    
    }
    
    
}