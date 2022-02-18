/*import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;*/

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import org.json.JSONObject;


/**@WebServlet(
		name= "ConsultaUsuario",
		urlPatterns={"/consulta"})  Servlet 3.0 ***/
public class controlAcceso extends HttpServlet {
// El metodo doGet() se ejecuta una vez por cada peticion HTTP GET.
//HttpServletRequest request;
private String userName;
private String password;
private String url;
private String driver;
String nombre="";

//atributos para el filtro
private Integer contador=1;
private ServletContext context;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        
    PrintWriter out = response.getWriter();
    String pass="", usuario="",consulta,mail="",acceso="";
        try {
            // Establecemos el tipo MIME del mensaje de respuesta
            //response.setContentType("text/plain");
            response.setContentType("application/json");
           // response.setContentType("text/javascript");
            // Creamos un objeto para poder escribir la respuesta
           // PrintWriter out = response.getWriter();
            Connection conn = null;
            Statement stmt = null;

            InputStreamReader in = new  InputStreamReader(request.getInputStream());
            BufferedReader br = new BufferedReader(in);
           
          // A-JSONObject.................. 
             String json = "";
                if(br != null){
                     json = br.readLine();
                }
            
            JSONObject objeto_json= new JSONObject(json);
           // out.println("usuario Recibido ="+objeto_json.getString("username"));
            consulta =objeto_json.getString("Email");
            if(consulta.equalsIgnoreCase("login")){
                usuario =objeto_json.getString("username");
                pass    = objeto_json.getString("password");
                acceso=compruebaUsuario(1,usuario, pass);//,nombre);
            }else {
                  // mail= objeto_json.getString("username");
                   acceso=compruebaUsuario(2,consulta, pass);
            }
        
            //------------------------------------------------- **/
     
            // Gson json = new Gson();
            
      /*   // B - Con google.gson.------------------------------------
         
           JsonReader lector = new JsonReader(br);
            JsonParser parseador = new JsonParser();
            JsonElement raiz = parseador.parse(lector);
   
          usuario =(String) raiz.getAsJsonObject().get("username").getAsString();
          pass=     raiz.getAsJsonObject().get("password").getAsString(); **/
         
          
          // Comprobamos si existe el usuario------------------------
          
         //      String acceso=compruebaUsuario(1,usuario, pass);//,nombre);
          
          // Preparar la salida en JSON------------------------------
          JsonObject object = new JsonObject();
          object.addProperty("Acceso", acceso);
          object.addProperty("nombre", nombre);
          
          String jsonS = object.toString();
          out.println(jsonS);
 
         //----------------------------------------------------------**/
         out.flush();
           
        
      } catch (Exception ex) {
            out.println(ex);
        }
        
       

}
public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
doGet(request, response);  // Redirecciona la petici�n POST al m�todo doGet()
}


//--------------
public String compruebaUsuario(int tipo,String usuario,String clave){//, String nombre){
    try {    
        Connection conn = null;
        Statement stmt = null;
        String sqlStr="";
        userName="peliculas";
	password="peliculas";
        url=getInitParameter("URLBDM");
	driver=getInitParameter("DriverM");
        
        try {
            //Paso 1: Cargar el driver JDBC.
            Class.forName(driver).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return "Concedido";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return "Concedido";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return "Concedido";
        }
        
        // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
        conn = DriverManager.getConnection(url, userName, password);
        
        // Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
        stmt = conn.createStatement();
        
        if (tipo==1)
            sqlStr ="select * from usuarios where login ='"+ usuario+"' and"
                    + " password ='"+clave+"'";
        else 
            sqlStr ="select * from usuarios where email ='"+ usuario+"'";

        // Paso 5: Ejecutar las sentencias SQL a traves de los objetos Statement
        ResultSet rset = stmt.executeQuery(sqlStr);
        
        //Convierte el ResultSet en un Result
        
        nombre="";
        if ( rset.next()){
            nombre= rset.getString("nombre");// rset.getMetaData().getColumnName(column);
            return "Concedido";
        }
        else return "Denegado";
        
    } catch (SQLException ex) {
        Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
        return "Denegado";
    }
   
}

}
