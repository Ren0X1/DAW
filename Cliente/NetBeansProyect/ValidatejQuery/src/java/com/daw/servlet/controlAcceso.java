package com.daw.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet(name = "ConsultaUsuario", urlPatterns = {"/consulta"})
public class controlAcceso extends HttpServlet {
    private String userName;
    private String password;
    private String url;
    private String driver;
    String nombre = "";
    private Integer contador = 1;
    private ServletContext context;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String pass = "", usuario = "", consulta, mail = "", acceso = "";
        try {
            response.setContentType("application/json");
            Connection conn = null;
            Statement stmt = null;
            InputStreamReader in = new InputStreamReader(request.getInputStream());
            BufferedReader br = new BufferedReader( in );
            String json = "";
            if (br != null) {
                json = br.readLine();
            }

            JSONObject objeto_json = new JSONObject(json);
            consulta = objeto_json.getString("Email");
            if (consulta.equalsIgnoreCase("login")) {
                usuario = objeto_json.getString("username");
                pass = objeto_json.getString("password");
                acceso = compruebaUsuario(1, usuario, pass); //,nombre);
            } else {
                acceso = compruebaUsuario(2, consulta, pass);
            }
            JsonObject object = new JsonObject();
            object.addProperty("Acceso", acceso);
            object.addProperty("nombre", nombre);

            String jsonS = object.toString();
            out.println(jsonS);
            out.flush();
        } catch (IOException | JSONException ex) {
            out.println(ex);
        }



    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public String compruebaUsuario(int tipo, String usuario, String clave) {
        try {
            Connection conn = null;
            Statement stmt = null;
            String sqlStr = "";
            userName = "root";
            password = "root";
            url = "jdbc:mysql://localhost/validate_jqueary";
            driver = "com.mysql.jdbc.Driver";

            try {
                Class.forName(driver).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
                return "Concedido";
            }
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();

            if (tipo == 1)
                sqlStr = "select * from usuarios where login ='" + usuario + "' and" +
                " password ='" + clave + "'";
            else
                sqlStr = "select * from usuarios where email ='" + usuario + "'";
            ResultSet rset = stmt.executeQuery(sqlStr);
            nombre = "";
            if (rset.next()) {
                nombre = rset.getString("nombre");
                return "Concedido";
            } else return "Denegado";

        } catch (SQLException ex) {
            Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return "Denegado";
        }
    }
}