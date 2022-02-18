import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class BuscaCoordenadas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consulta="";
        String municipio = request.getParameter("municipio");
        String username = request.getParameter("root");
        String password = request.getParameter("root");
        JSONObject obj = new JSONObject();
        if (municipio!=null) {
            consulta ="Select latitud,longitud from poblacion "+ " where poblacion ='"+municipio+"' order by poblacion";
            try {
                ResultSet datos=conectaBD(consulta);
                int i=0;
                if(datos.next()) {
                    obj.put("latitud",datos.getFloat("latitud"));
                    obj.put("longitud",datos.getFloat("longitud")); 
                } else {
                    obj.put("error","No se ha encontrado el pueblo="+ municipio);
                }
            } catch(JSONException | InstantiationException ex) {
                Logger.getLogger(BuscaPoblaciones.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IllegalAccessException | SQLException ex) {
                Logger.getLogger(BuscaCoordenadas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.setContentType("application/json");
        response.getWriter().print(obj.toString());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }
        
    
    protected ResultSet conectaBD(String consulta) throws InstantiationException, IllegalAccessException, SQLException {
        Connection conn = null;
        Statement stmt = null;
        String sqlStr="";
        ResultSet rset;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url="jdbc:mysql://localhost/cliente";
            conn = DriverManager.getConnection(url, "root", "root");
            stmt = conn.createStatement();
            return  rset = stmt.executeQuery(consulta);
        } catch (ClassNotFoundException ex){
            ex.getMessage();
        }
        return null;
    }
}