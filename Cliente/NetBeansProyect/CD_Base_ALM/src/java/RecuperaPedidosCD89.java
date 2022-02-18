import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/RecuperaP")
public class RecuperaPedidosCD89 extends HttpServlet {
    private String userName="root";
    private String password="root";
    private String url="jdbc:mysql://localhost:3306/pedidos";
    private String driver="com.mysql.jdbc.Driver";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,  response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        String pedido;
        pedido=request.getParameter("pedido");
        Connection conn = null;
        Statement stmt = null;
        String sqlStr=null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            JSONObject salida =new JSONObject();
            ResultSet rs=null;
            JSONObject pedidoJs = new JSONObject(pedido);
            int cpedido =Integer.parseInt((String)pedidoJs.get("cpedido"));
            String nif  = (String) pedidoJs.get("cliente");
            if (nif.equals("todos")) {
                rs=stmt.executeQuery("select distinct cliente from pedidos");  
                JSONObject arrayObj = new JSONObject();
                while(rs.next()){
                    arrayObj.put('"'+rs.getString("cliente")+'"',rs.getString("cliente"));
                }
                salida.put("clientes",arrayObj);
            } else if(cpedido!=0) {
                rs=stmt.executeQuery("select * from detallepedidos where "+"pedido='"+cpedido+"'");
                int i=0;
                while(rs.next()){
                    String t = rs.getString("titulo");
                    String a = rs.getString("autor");
                    float p = rs.getFloat("precio");
                    int c = rs.getInt("cantidad");
                    LineaCd linea = new LineaCd(t,a,p,c,"#000000");
                    String jsonStr = new com.google.gson.Gson().toJson(linea);  
                    JSONObject arrayObj = new JSONObject(jsonStr);
                    salida.put( Integer.toString(i), jsonStr);
                    i++;
                }
            } else {
                rs = stmt.executeQuery("select * from pedidos where "+"cliente='"+nif+"'");
                while(rs.next()){
                    Integer c = rs.getInt("codigo");
                    String f = rs.getString("fecha");
                    String cli = rs.getString("cliente");
                    JSONObject arrayObj = new JSONObject();
                    arrayObj.put("codigo",c);
                    arrayObj.put("fecha",f);
                    arrayObj.put("cliente",cli);
                    salida.put( c.toString(), arrayObj);
                } 
            }  
            out.print(salida.toString());
            rs.close ();
            stmt.close ();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NumberFormatException | SQLException | JSONException exc){ 
            out.println("<html><head><title>Resultado de la consulta</title></head><body>");
            out.println("<p> se ha producido un error = " + exc+"</p>");
            out.println("<p> StrSql = " + sqlStr+"</p>");
            out.println("<p> Error  completo= " + exc+"</p>");
            out.println("</body></html>");
        }finally {
            out.close();
            try {
                if(conn!=null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GuardaPedidosCD89.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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


