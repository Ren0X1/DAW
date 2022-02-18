import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONObject;


/**@WebServlet(
		name= "ConsultaUsuario",
		urlPatterns={"/consulta"})  Servlet 3.0 ***/
@WebServlet("/GuardaPedidosCD")
public class GuardaPedidosCD89 extends HttpServlet {

//HttpServletRequest request;
private String userName="pedidos";
private String password="pedidos";
private String url="jdbc:mysql://localhost:3306/pedidos";
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
            jsonString=request.getParameter("pedido"); 
            
            JSONObject pedido;
               
            pedido = new JSONObject(jsonString);
            
            guardaPedidoBD(pedido,out);
            
          
            out.flush();
                       
        
        } catch (Exception ex) {
            out.println(ex);
        }
   
}
    //---------------------------------------
    public void guardaPedidoBD(JSONObject pedido, PrintWriter out){
    
         Connection     conn = null;
         Statement      stmt = null;
         
         String         sqlStr=null;

        try {
        //Paso 1: Cargar el driver JDBC.
           Class.forName(driver).newInstance();

        // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
           conn = DriverManager.getConnection(url, userName, password);
          // conn=pool.getConnection();
           
        // Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
            stmt = conn.createStatement();

      // Paso 4: Crear la sentencias SQL, a partir de los parametros recibidos
       String fecha =(String)pedido.get("fecha"); 
             
       /* sqlStr ="INSERT INTO pedidos(codigo,cliente,fecha) "
                + "VALUES('"+pedido.get("codigo")
                +         "', '"+pedido.get("cliente")
                +         "', '"+fecha+"')";*/
       sqlStr ="INSERT INTO pedidos(cliente,fecha) "
                + "VALUES('"+pedido.get("cliente")
                +         "', '"+fecha+"')";
        
        int nreg = stmt.executeUpdate(sqlStr);
        ResultSet rs=stmt.executeQuery("select max(codigo) ultimoPedido from pedidos");
        rs.next();
        int ultimoPedido=rs.getInt("ultimoPedido");
        
        String titulo,cd;
        double precio;
        int cantidad;
        String autor;
                
        JSONObject linea;
        
       // JSONArray detalle =(JSONArray) pedido.get("detalle");
        JSONObject detalle = (JSONObject)pedido.get("detalle");
        
        Iterator lineas = detalle.keys();
        
       // for(Object clave:it){
       while(lineas.hasNext()){
           String clave =(String) lineas.next();
           
         //  Iterator columnas = detalle.keys();
           linea = detalle.getJSONObject(clave);
           
           cd=linea.getString("cd");
           titulo =cd.substring(0, cd.indexOf('/'));
           autor=cd.substring(cd.indexOf('/')+1);
           
           precio=linea.getDouble("precio");
           //titulo=linea.getString("Titulo");
           cantidad=linea.getInt("cantidad");
                    
          sqlStr="INSERT INTO detallepedidos (titulo,autor,precio,cantidad,pedido) VALUES('"+
                    titulo+"', '"+autor+"', '"+precio+"', '"+
                    cantidad+"', '"+ultimoPedido+"')";

           // Paso 5: Ejecutar las sentencias SQL a traves de los objetos Statement    
     
                nreg = stmt.executeUpdate(sqlStr);
          }

        //Integer ped =(Integer)pedido.get("codigo");
        // Paso 6: Response................
        String json="{\"codigo\":\"" +ultimoPedido+ "\",\"fecha\":\""+fecha+"\"}";
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
                Logger.getLogger(GuardaPedidosCD89.class.getName()).log(Level.SEVERE, null, ex);
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


}

      

