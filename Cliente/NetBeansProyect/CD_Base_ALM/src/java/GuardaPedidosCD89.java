import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/GuardaPedidosCD")
public class GuardaPedidosCD89 extends HttpServlet {

    private String userName="pedidos";
    private String password="pedidos";
    private String url="jdbc:mysql://localhost:3306/pedidos";
    private String driver="com.mysql.jdbc.Driver";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("application/json");
            String jsonString=request.getParameter("pedido"); 
            JSONObject pedido = new JSONObject(jsonString);
            guardaPedidoBD(pedido,out);
            out.flush();
        } catch (JSONException ex) {
            out.println(ex);
        }
    }
    
    public void guardaPedidoBD(JSONObject pedido, PrintWriter out) {
        Connection     conn = null;
        Statement      stmt;
        String         sqlStr=null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            String fecha =(String)pedido.get("fecha"); 
            sqlStr ="INSERT INTO pedidos(cliente,fecha) "+"VALUES('"+pedido.get("cliente")+"', '"+fecha+"')";
            int nreg = stmt.executeUpdate(sqlStr);
            ResultSet rs=stmt.executeQuery("select max(codigo) ultimoPedido from pedidos");
            rs.next();
            int ultimoPedido=rs.getInt("ultimoPedido");
            String titulo,cd;
            double precio;
            int cantidad;
            String autor;
            JSONObject linea;
            JSONObject detalle = (JSONObject)pedido.get("detalle");
            Iterator lineas = detalle.keys();
            while(lineas.hasNext()){
                String clave =(String) lineas.next();
                linea = detalle.getJSONObject(clave);
                cd=linea.getString("cd");
                titulo =cd.substring(0, cd.indexOf('/'));
                autor=cd.substring(cd.indexOf('/')+1);
                precio=linea.getDouble("precio");
                cantidad=linea.getInt("cantidad");
                sqlStr="INSERT INTO detallepedidos (titulo,autor,precio,cantidad,pedido) VALUES('"+titulo+"', '"+autor+"', '"+precio+"', '"+cantidad+"', '"+ultimoPedido+"')";
                nreg = stmt.executeUpdate(sqlStr);
            }
            String json="{\"codigo\":\"" +ultimoPedido+ "\",\"fecha\":\""+fecha+"\"}";
            out.println(json);
        } catch (Exception exc){ 
            out.println("<html><head><title>Resultado de la consulta</title></head><body>");
            out.println("<p> se ha producido un error = " +exc+"</p>");
            out.println("<p> StrSql = " +sqlStr+"</p>");
            out.println("</body></html>");
        } finally {
            out.close();
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GuardaPedidosCD89.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext contexto = config.getServletContext();
        
        url       = contexto.getInitParameter("url");
        driver    = contexto.getInitParameter("driver");
        userName  = contexto.getInitParameter("username");
        password  = contexto.getInitParameter("password");
    }
}

      

