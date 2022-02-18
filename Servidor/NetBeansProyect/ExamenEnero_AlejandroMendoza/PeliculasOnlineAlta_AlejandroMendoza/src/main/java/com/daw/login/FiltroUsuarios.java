package com.daw.login;
import com.daw.peliculas.DAO.UsuarioJpaController;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "FiltroUsuario", urlPatterns = {"/AltaPeliculas"})
public class FiltroUsuarios implements Filter{  
  
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    
    }  
      
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("peliculasonlinealtasPU");
        UsuarioJpaController ctrUsu = new UsuarioJpaController(emf);
        String name = req.getParameter("name"); 
        if (name==null && req.getParameter("password")==null) {
            RequestDispatcher rd=req.getRequestDispatcher("index.html");  
            rd.include(req, resp);
        }
        int password = Integer.parseInt(req.getParameter("password"));

        int admin = ctrUsu.findUsuario(name).getAdmin();
        int contra = ctrUsu.findUsuario(name).getPass();

        if(password == contra && admin == 1) {
            req.setAttribute("login", "correcto");
            chain.doFilter(req, resp); 
        } else {
            RequestDispatcher rd=req.getRequestDispatcher("index2.html");  
            rd.include(req, resp);
        }

    }
    
    @Override
    public void destroy() {
    
    }  
  
}

