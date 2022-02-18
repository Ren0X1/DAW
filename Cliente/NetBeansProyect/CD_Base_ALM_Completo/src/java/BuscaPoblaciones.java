
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

 @WebServlet(
        urlPatterns = "/BuscaPoblaciones",
        initParams =
        {
            @WebInitParam(name = "userName", value = "root"),
            @WebInitParam(name = "password", value = "root"),
            @WebInitParam(name = "url",      value = "jdbc:mysql://localhost/clientes"),
            @WebInitParam(name = "driver",   value = "com.mysql.jdbc.Driver")
        }
)

public class BuscaPoblaciones extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    
    private String url;
    private String driver;
    private String userName;
    private String password;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
       
       
        String provincia = request.getParameter("provincia");
        String municipio = request.getParameter("municipio");
      
        String consulta,id,name;
               
        
        JSONObject obj = new JSONObject();
        
        if (municipio!=null)
        {
            consulta ="Select idpoblacion, poblacion, latitud,longitud from poblacion "
                    + " where poblacion like '"+municipio+"%' order by poblacion";
            
            id="idpoblacion";name="poblacion";
            try {
                obj.put("tipo","municipio");
            } catch (JSONException ex) {
                Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            
        
      /*  if (provincia==null) 
            provincia="provincia";*/
        
        if(provincia.equals("provincia"))
          {
             consulta ="Select idprovincia,provincia from provincia order by provincia";
             
             id="idprovincia";name="provincia";
             try {
                obj.put("tipo","provincia");
            } catch (JSONException ex) {
                Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
             
          }else{
            int prov =Integer.parseInt(provincia);
            consulta ="Select idpoblacion,poblacion, latitud,longitud from poblacion "
                    + " where idprovincia ='"+prov+"' order by poblacion";
            
            id="idpoblacion";name="poblacion";
            try {
                obj.put("tipo","localidad");
            } catch (JSONException ex) {
                Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
        }
        
      
        try {
            ResultSet datos=conectaBD(consulta);
            int i=0;
            
            while( datos.next()){// && i<100 ) {
                              
                obj.put(datos.getString(id),datos.getString(name)); 
              
                i++;
            }
        }catch (JSONException ex) {
            Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
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
       
        url=config.getInitParameter("url");
               
        driver=config.getInitParameter("driver");
        
        userName=config.getInitParameter("userName");
        password=config.getInitParameter("password");
    }

        
    
}