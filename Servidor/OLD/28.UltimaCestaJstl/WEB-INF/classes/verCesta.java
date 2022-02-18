import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.Articulo;

public class verCesta extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			HttpSession miSesion = request.getSession();
			
			String nombreCliente = (String)miSesion.getAttribute("nombreCliente"); // obtengo el nombre de la sesion
			ArrayList<Articulo> miCesta = (ArrayList<Articulo>)miSesion.getAttribute("miCesta"); // obtengo el ArrayList de la sesion
	
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("table, th, td {border: 1px solid black; border-collapse: collapse;}");
			out.println("</style>");
			out.println("</head>");
			
			out.println("<body>");
			
			out.println("<h2>Carrito de "+nombreCliente+"</h2>");
			
			out.println("<ul>");
			
			for (int i = 0; i < miCesta.size(); i++) {
				out.println("<li>");
				out.println("<img src='"+miCesta.get(i).getImagenArticulo()+"'>");
				out.println(miCesta.get(i).getNombreArticulo());
				out.println("</li>");
			}
			
			out.println("</ul>");
			
			out.println("<form action='listaArticulos'>");
			out.println("<input type='submit' value='Seguir Comprando'>");
			out.println("</form>");

			out.println("<form action='confirmar.jsp'>");
			out.println("<input type='submit' value='Confirmar pedido>");
			out.println("</form>");
						
			out.println("</body>");
			out.println("</html>");
			
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