import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Generar extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();

		String[] opciones = request.getParameterValues("select");
		
		out.println("<!doctype html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset="+"utf-8"+">");
				out.println("<title>Formulario</title>");
			out.println("</head>");
			out.println("<body>");
				out.println("<form action='ProcesarFormulario'>");
					if (opciones!=null && opciones.length!=0) {
						for (int i=0;i!=opciones.length;i++) {
							out.println("<label>"+opciones[i]+"</label>");
							out.println("<input style='margin-left: 10px;' type='text' name='"+opciones[i]+"'>");
							out.println("<br>");
							out.println("<br>");
						}
					}
					out.println("<input type='submit' value='Enviar!'>");
				out.println("</form>");
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