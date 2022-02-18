import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONObject;


/**@WebServlet(
		name= "ConsultaUsuario",
		urlPatterns={"/consulta"})  Servlet 3.0 ***/
@WebServlet("/GuardaC")
public class GuardaClientes extends HttpServlet {

//HttpServletRequest request;
private String userName="root";
private String password="root";
private String url="jdbc:mysql://localhost:3306/clientes";
private String driver="com.mysql.jdbc.Driver";

 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        
        //1.-  Creamos un objeto para poder escribir la respuesta
            PrintWriter out = response.getWriter();
        try {
        //2.-  Establecemos el tipo MIME del mensaje de respuesta
            //response.setContentType("text/plain");
            response.setContentType("application/json");
           // response.setContentType("text/javascript");
                        
            Connection conn = null;
            Statement stmt = null;

            String jsonString = "";
            jsonString=request.getParameter("cliente"); 
            
            JSONObject cliente;
            
            if(jsonString==null)
                guardaClienteBD(null,request,out);
            else{
                cliente = new JSONObject(jsonString);
                guardaClienteBD(cliente,request,out);
            }
            
    //  request.getRequestDispatcher("TestServlet").include(request, response);
      
            out.flush();
                       
        
        } catch (Exception ex) {
            out.println(ex);
        }
   
}
    //---------------------------------------
    public void guardaClienteBD(JSONObject cliente,HttpServletRequest request, PrintWriter out){
   //  public void guardaClienteBD(PrintWriter out,HttpServletRequest request){
    
         Connection     conn = null;
         Statement      stmt = null;
         
         String         sqlStr=null;
         String     nombre,apellidos,nif,sexo,email,provincia,localidad;
         int        edad;

        try {
        //Paso 1: Cargar el driver JDBC.
           Class.forName(driver).newInstance();

        // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
           conn = DriverManager.getConnection(url, userName, password);
          // conn=pool.getConnection();
           
        // Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
            stmt = conn.createStatement();
            
        // Paso 4: Crear la sentencias SQL, a partir de los parametros recibidos
        if (cliente!=null){   
             nombre    =(String)cliente.get("nombre"); 
             apellidos =(String)cliente.get("apellidos"); 
             nif       =(String)cliente.get("nif"); 
             edad         = Integer.parseInt(cliente.getString("edad"));
             sexo = (String)cliente.get("sexo");
             email = (String)cliente.get("email");
             provincia = (String)cliente.get("provincia");
             localidad = (String)cliente.get("localidad"); 
        }
        else{
             nombre    =(String)request.getParameter("Nombre"); 
             apellidos =(String)request.getParameter("Apellidos"); 
             nif       =(String)request.getParameter("Nif"); 
             edad         = Integer.parseInt(request.getParameter("Edad"));
             sexo = (String)request.getParameter("Sexo");
             email = (String)request.getParameter("Email");
             provincia = (String)request.getParameter("Provincia");
             localidad = (String)request.getParameter("Localidad");
        }
        sqlStr="INSERT INTO clientes(nombre,apellidos,nif,edad,sexo,email,provincia,localidad) VALUES (?,?,?,?,?,?,?,?)";
        
        PreparedStatement pstm= conn.prepareStatement(sqlStr);
        pstm.setString(1, nombre);
        pstm.setString(2, apellidos);
        pstm.setString(3, nif);
        pstm.setInt(4, edad);
        pstm.setString(5, sexo);
        pstm.setString(6, email);
        pstm.setString(7, provincia);
        pstm.setString(8, localidad);
       
        
        int nreg = pstm.executeUpdate();
        

        //Integer ped =(Integer)pedido.get("codigo");
        // Paso 6: Response................
        String json="{\"cliente\":\"" +nombre+","+apellidos+ "\",\"nif\":\""+nif+"\"}";
       // out.println( "pedido n�  "+ pedido.get("codigo")+" Guardado correctamente");
        out.println( json.toString());
        
               
        }catch (Exception exc){ 
            out.println("<html><head><title>Resultado de la consulta</title></head><body>");
            out.println("<p> se ha producido un error = " + exc+"</p>");
            out.println("<p> StrSql = " + sqlStr+"</p>");
            out.println("</body></html>");
        }finally{
            out.close();
            try{
                if(conn!=null) conn.close(); // Devuelve la conexion al pool
            } catch (SQLException ex) {
                Logger.getLogger(GuardaClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
//----------------------------------------
public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
doGet(request, response);  // Redirecciona la petici�n POST al m�todo doGet()
}

//--------------------------------------------------
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        ServletContext contexto=config.getServletContext();
        
        url=contexto.getInitParameter("url");
               
        driver=contexto.getInitParameter("driver");
        
        userName=contexto.getInitParameter("userName");
        
        password=contexto.getInitParameter("password");
    }
    //-------------------------------------------------------------------------

}

      

