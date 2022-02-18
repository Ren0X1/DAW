import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class verCarrito extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		Cookie [] vectorCookies=request.getCookies();
		if (vectorCookies!=null) {
			Cookie c = vectorCookies[vectorCookies.length-1];
			out.println("<h1>Hola "+URLDecoder.decode(c.getName().substring(2,c.getName().length()), "utf-8")+" tu lita de compra es: </h1>");
			String val = URLDecoder.decode(c.getValue(), "utf-8");
			String[] parts = val.split("");
			out.println("<ul>");
			String texto="";
			for (int k=0;k!=parts.length;k++) {
				if (parts[k].equals("|")) {
					out.println("<li>"+texto+"</li>");
					texto="";
				} else {
					texto+=parts[k];
				}
			}
			out.println("</ul>");
		}
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