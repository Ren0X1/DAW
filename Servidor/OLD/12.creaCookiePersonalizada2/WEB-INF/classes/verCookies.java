import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class verCookies extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='css/style.css'>");
		out.println("</head>");
		out.println("<body>");
		Cookie [] vectorCookies=request.getCookies();
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Nombre</th>");
		out.println("<th>Valor</th>");
		out.println("<th>Vida</th>");
		out.println("<th>Ruta</th>");
		out.println("</tr>");
		out.println("</thead>");
		if (vectorCookies!=null) {
			for (Cookie c:vectorCookies) {
				out.println("<tr>");
					out.println("<td>");
						out.println(URLDecoder.decode(c.getName(), "utf-8"));
					out.println("</td>");
					out.println("<td>");
						out.println(URLDecoder.decode(c.getValue(), "utf-8"));
					out.println("</td>");
					out.println("<td>");
						out.println(c.getMaxAge());
					out.println("</td>");
					out.println("<td>");
						out.println(c.getPath());
					out.println("</td>");
				out.println("</tr>");
			}
		}
		out.println("<table>");
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