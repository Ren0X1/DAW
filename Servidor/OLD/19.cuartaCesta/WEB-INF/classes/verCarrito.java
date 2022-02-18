import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class verCarrito extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		Cookie [] vectorCookies=request.getCookies();
		if (vectorCookies!=null) {
			Cookie c = vectorCookies[vectorCookies.length-1];
			out.println("<h1>Hola "+request.getSession(false).getAttribute("usuario")+" tu carrito es: </h1>");
			String val = URLDecoder.decode(c.getValue(), "utf-8");
			String[] parts = val.split("\\|");
			out.println("<ul>");
			String texto="";
			for (int k=0;k!=parts.length;k++) {
					out.println("<li>"+parts[k]+"</li>");
			}
			out.println("</ul>");
		}
		out.println("<br>");
		out.println("<a href='index.html'>Volver</a>");
		out.println("<br>");
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