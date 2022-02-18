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
           
            response.setContentType("application/json");
            
            consulta =request.getParameter("Email");
            if(consulta.equalsIgnoreCase("login")){
                usuario =request.getParameter("username");
                pass    =request.getParameter("password");
                acceso=compruebaUsuario(1,usuario, pass);//,nombre);
            }else {
                  // mail= objeto_json.getString("username");
                   acceso=compruebaUsuario(2,consulta, pass);
            }
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
        /*userName="root";
	password="root";
        url="jdbc:mysql://localhost/login";
	driver="com.mysql.jdbc.Driver";
        /*url=getInitParameter("URLBDM");
	driver=getInitParameter("DriverM");*/
        
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
                    + " clave ='"+clave+"'";
        else 
            sqlStr ="select * from usuarios where email ='"+ usuario+"'";

        // Paso 5: Ejecutar las sentencias SQL a traves de los objetos Statement
        ResultSet rset = stmt.executeQuery(sqlStr);
        
        //Convierte el ResultSet en un Result
        
        nombre="";
        if ( rset.next()){
            nombre= rset.getString("login");// rset.getMetaData().getColumnName(column);
            return "Concedido";
        } else return "Denegado";
        
    } catch (SQLException ex) {
        Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
        return "Denegado";
    }
   
}
//-------------------------------------------------
@Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
                 
        //2.-
        ServletContext contex = config.getServletContext();
        
        url= contex.getInitParameter("URLBDM");
             
        driver=contex.getInitParameter("DriverM");
        
        userName=contex.getInitParameter("Usuario");
        password=contex.getInitParameter("password");
    }
    

}
