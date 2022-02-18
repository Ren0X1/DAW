import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();
		
		Enumeration elementos = request.getParameterNames();
		
		out.println("<!doctype html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset="+"utf-8"+">");
				out.println("<title>Respuesta Formulario</title>");
			out.println("</head>");
			out.println("<body>");
			while (elementos.hasMoreElements()) {
				Object x = elementos.nextElement();
				String parametro = (String)x;
				String value = request.getParameter(parametro);
				if (value!=null && value!="") {
					out.println(parametro+": "+value);
					out.println("<br>");
					out.println("<br>");	
				}
			}
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