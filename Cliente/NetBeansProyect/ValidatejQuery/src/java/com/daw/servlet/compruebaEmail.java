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
public class compruebaEmail extends HttpServlet {
    private String userName;
    private String password;
    private String url;
    private String driver;
    String nombre = "";
    private final Integer contador = 1;
    private ServletContext context;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mail = "";
        boolean acceso = false;
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

            mail = objeto_json.getString("Email");
            acceso = compruebaUsuario(mail);
            JsonObject object = new JsonObject();
            object.addProperty("Email", acceso);
            String jsonS = object.toString();
            out.println(jsonS);
            out.flush();
        } catch (IOException ex) {
            out.println(ex);
        } catch (JSONException ex) {
            Logger.getLogger(compruebaEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    public boolean compruebaUsuario(String correo) {
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
                return false;
            }
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            sqlStr = "select * from usuarios where email ='" + correo + "'";
            ResultSet rset = stmt.executeQuery(sqlStr);
            if (rset.next()) {
                return false;
            } else return true;
        } catch (SQLException ex) {
            Logger.getLogger(controlAcceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}