package com.entrada;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SaludoMio extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();
		
		String nombrePersona = request.getParameter("nombre");
		String apellidoPersona = request.getParameter("apellido");
		String edadPersona = request.getParameter("edad");
		
		out.println("<!doctype html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset="+"utf-8"+">");
				out.println("<title>Saludar con Nombre</title>");
			out.println("</head>");
			out.println("<body>");
				out.println("<p>Hola me llamo "+nombrePersona+" "+apellidoPersona+" y tengo "+edadPersona+" años.</p>");
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