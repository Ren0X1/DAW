import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Articulo;
import pojos.Tienda;

public class index extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext contexto = getServletContext();
		String stringTodosArticulos = getInitParameter("articulos");
		String stringArticulosIndependientes[] = stringTodosArticulos.split(";");
		HashMap<Integer,Articulo> mapaArticulos = new HashMap<Integer,Articulo>();
		for (int i = 0; i < stringArticulosIndependientes.length; i++) {
			String articuloIndependiente[] = stringArticulosIndependientes[i].split(":");
			Articulo nuevoArticulo = new Articulo(
				i,
				articuloIndependiente[0],
				Integer.parseInt(articuloIndependiente[1]),
				Double.parseDouble(articuloIndependiente[2])
			);
			mapaArticulos.put(i,nuevoArticulo);
		}
		Tienda miTienda=new Tienda();
		miTienda.setNombreTienda("Tienda de 2DAW");
		miTienda.setListaArticulos(mapaArticulos);
		contexto.setAttribute("miTienda", miTienda);
	}
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tienda.jsp");
		rd.include(request, response);
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