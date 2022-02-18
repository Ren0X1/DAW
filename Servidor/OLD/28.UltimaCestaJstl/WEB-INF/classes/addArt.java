import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.Cesta;
import pojos.Tienda;

public class addArt extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		ServletContext contexto = getServletContext();
		HttpSession miSesion = request.getSession();
		
		Tienda miTienda = (Tienda)contexto.getAttribute("miTienda");
		Cesta miCesta = (Cesta)miSesion.getAttribute("miCesta"); 

		String articulosSeleccionados[] = request.getParameterValues("articulosSeleccionados");
		
		if (articulosSeleccionados != null) {
			for (int i = 0; i < articulosSeleccionados.length; i++) {
				int codArt = i;
				int cantidad = Integer.parseInt(articulosSeleccionados[i]);			
				miCesta.setAddArticulo(codArt,cantidad);
				miTienda.getObtenerArticulo(codArt).decrementar(cantidad);
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