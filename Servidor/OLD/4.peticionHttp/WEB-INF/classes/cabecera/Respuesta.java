package cabecera;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String nom=null;
		Enumeration nC,vC=null;
		out.println("<html>");
		out.println("<head>");
		out.println("<title>INDEX</title>");
		out.println("</head>");
		out.println("<body>");
		nC=request.getHeaderNames();
		out.println( "<table border="+1+">");
		out.println("<tbody>");
		while (nC.hasMoreElements()) {
			nom=(String)nC.nextElement();
			out.println("<tr>");
			out.println("<td>"+nom+"</td>"); 
			vC=request.getHeaders(nom);
			while (vC.hasMoreElements()) {
				out.println("<td>"+vC.nextElement()+"</td>");
			}
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<br>");
        out.println("<form action="+"index.html"+">");
		out.println("<input type="+"submit"+" value="+"Volver"+">");
        out.println("</form>");
		out.println("<form action="+"Salida"+">");
		out.println("<input type="+"submit"+" name="+"salidaCabecera"+" value="+"Salir!"+">");
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