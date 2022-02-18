import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.URLEncoder;
import java.net.URLDecoder;
import pojos.*;

public class addArt extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
			PrintWriter out = response.getWriter();
			
			ServletContext contexto = getServletContext();
			HttpSession miSesion = request.getSession();
			
			Tienda miTienda = (Tienda)contexto.getAttribute("miTienda");
			Cesta miCesta = (Cesta)miSesion.getAttribute("miCesta"); 
	
			String articulosSeleccionados[] = request.getParameterValues("articulosSeleccionados");
			
			if (articulosSeleccionados != null) {
				for (int i = 0; i < articulosSeleccionados.length; i++) {
					int codArt = Integer.parseInt(articulosSeleccionados[i]);
					out.println(codArt + "<br>");
					
					miCesta.setAddArticulo(codArt);
					miTienda.getObtenerArticulo(codArt).setDecrementarUnidad();
					
				}
			}
	
			
			
			
				
	}
		
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
}