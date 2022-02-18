package recursosAux;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Salida extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String salida1 = request.getParameter("salidaUrl");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>INDEX</title>");
		out.println("</head>");
		out.println("<body>");
		if (salida1!=null && salida1!="") {
			out.println("<h1>Has salido desde URL</h1>");
		} else {
			out.println("<h1>Has salido desde Cabecera</h1>");
		}
		out.println("<br>");
		out.println("<a href="+"index.html"+">Inicio</a>");
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