import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();
		
		String nombrePersona = request.getParameter("nombre");
		String apellidoPersona = request.getParameter("apellido");
		String sistemaOpe = request.getParameter("so");
		String lenguaFav = request.getParameter("lengua");
		String deporte = request.getParameter("check");
		
		out.println("<!doctype html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset="+"utf-8"+">");
				out.println("<title>Formulario</title>");
			out.println("</head>");
			out.println("<body>");
				out.println("<p>Nombre: "+nombrePersona+"</p>");
				out.println("<p>Apellidos: "+apellidoPersona+"</p>");
				out.println("<p>Sistema Operativo: "+sistemaOpe+"</p>");
				out.println("<p>Lengua: "+lenguaFav+"</p>");
				out.println("<p>Deporte: "+deporte+"</p>");
			out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doGet (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		proceso(request,response);
	}

	@Override
	protected void doPost (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		proceso(request,response);
	}
}