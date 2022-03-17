// Java Document

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

 
@WebServlet("/ProvPueblos") 
public class ProvPueblos extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    private String url;
    private String driver;
    private String userName;
    private String password;
 
    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     * @throws javax.servlet.ServletException
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
       
         //0......
        String provincia = request.getParameter("provincia");
        String municipio = request.getParameter("municipio");
        
        response.addHeader("Access-Control-Allow-Origin", "*");
       
        String consulta,id,name;
               
        
        JSONObject obj = new JSONObject();
             
        if (provincia==null) 
           {
             consulta ="Select * from provincia order by provincia";
             
             id="idprovincia";name="provincia";
             
        }else{
            if(provincia.equalsIgnoreCase("*"))
                consulta ="Select idpoblacion,poblacion, latitud,longitud from poblacion";
            else {                   
                //int prov =Integer.parseInt(provincia);
                consulta ="Select idpoblacion,poblacion, latitud,longitud from poblacion "
                   // + " where idprovincia ='"+prov+"' order by poblacion";
                         + " where idprovincia ="+provincia+" order by poblacion";
            }
            id="idpoblacion";name="poblacion";
         }
        //JSONObject obj = new JSONObject();
        try {
            ResultSet datos             =conectaBD(consulta);
            ResultSetMetaData metaDatos =datos.getMetaData();
            
            int numeroColumnas = metaDatos.getColumnCount();
            Object[] etiquetas= new Object[numeroColumnas];
            
            for(int i=0;i<numeroColumnas; i++){// ResultSetMetaData la primera columna es la 1
              etiquetas[i]=metaDatos.getColumnLabel(i+1);
            }
            
            int j=0;
            
            while( datos.next() ){
                JSONObject arrayObj = new JSONObject();
                for(int i=0; i<numeroColumnas; i++){
                    arrayObj.put(etiquetas[i].toString(),datos.getObject(i+1));
                   
                }  
                                   
               // obj.put(datos.getString(id),arrayObj); 
                obj.put(datos.getObject(1).toString(),arrayObj); 
                j++;
            }
        }catch (JSONException ex) {
            System.out.println("Error-> "+ex.getMessage());
        } catch (InstantiationException ex) {
             System.out.println("Error-> "+ex.getMessage());
        } catch (IllegalAccessException ex) {
             System.out.println("Error-> "+ex.getMessage());
        } catch (SQLException ex) {
             System.out.println("Error-> "+ex.getMessage());
        }
        
        response.setContentType("application/json");
       
        response.getWriter().print(obj.toString());
        }//doPost
    
    //----------
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request,response);
    
    }
    //----------
        
    
protected ResultSet conectaBD(String consulta) throws InstantiationException, IllegalAccessException, SQLException
{
    Connection conn = null;
    Statement stmt = null;
    String sqlStr="";
    ResultSet rset;
   try {
            //Paso 1: Cargar el driver JDBC.
    Class.forName(driver).newInstance();
   
    // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
   
    //URL de la base de datos(equipo, puerto, base de datos)
    //String url="jdbc:mysql://localhost/clientes";
    conn = DriverManager.getConnection(url, userName, password);
    // Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
    stmt = conn.createStatement();
    
    return  rset = stmt.executeQuery(consulta);
    
    } catch (ClassNotFoundException ex){
        ex.getMessage();
        ex.printStackTrace();
    }
        return null;
    
}//-------------        
//--------------------------------------------------
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        ServletContext contexto=config.getServletContext();
        
        url=contexto.getInitParameter("url");
               
        driver=contexto.getInitParameter("driver");
        
        userName=contexto.getInitParameter("userName");
        password=contexto.getInitParameter("password");
    }

        
    
}