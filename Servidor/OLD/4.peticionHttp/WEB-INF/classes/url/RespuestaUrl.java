package url;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class RespuestaUrl extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>INDEX</title>");
		out.println("</head>");
		out.println("<body>");
		out.println( "<table border="+1+">");
		out.println("<tbody>");
			out.println("<tr>");
				out.println("<td>Nombre del servidor</td>");
				out.println("<td>"+request.getServerName()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>Puerto del servidor</td>");
				out.println("<td>"+request.getServerPort()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>Ubicacion del recurso</td>");
				out.println("<td>"+request.getContextPath()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>Ubicacion del servlet</td>");
				out.println("<td>"+request.getServletPath()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>Metodo usado</td>");
				out.println("<td>"+request.getMethod()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>Parametros usados</td>");
				out.println("<td>"+request.getQueryString()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>URL completa</td>");
				out.println("<td>"+request.getRequestURL()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>direccion IP del servidor</td>");
				out.println("<td>"+request.getLocalAddr()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>nombre del servidor</td>");
				out.println("<td>"+request.getLocalName()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>Puerto donde se ha recivido la peticion</td>");
				out.println("<td>"+request.getLocalPort()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>direccion IP del cliente</td>");
				out.println("<td>"+request.getRemoteAddr()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>nombre del cliente</td>");
				out.println("<td>"+request.getRemoteHost()+"</td>");
			out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("<br>");
        out.println("<form action="+"index.html"+">");
		out.println("<input type="+"submit"+" value="+"Volver"+">");
        out.println("</form>");
		out.println("<form action="+"Salida"+">");
		out.println("<input type="+"submit"+" name="+"salidaUrl"+" value="+"Salir!"+">");
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