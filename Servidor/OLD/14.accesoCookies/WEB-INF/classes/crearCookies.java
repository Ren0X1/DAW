import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class crearCookies extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		String nomCook=URLEncoder.encode(request.getParameter("nomCook"), "UTF-8");
		String valCook=URLEncoder.encode(request.getParameter("valCook"), "UTF-8");
		if (nomCook!=null && valCook!=null) {
			Cookie c = new Cookie (nomCook,valCook);
			response.addCookie(c);
			out.println("<h1>El ultimo usuario en entrar es "+URLDecoder.decode(c.getName(), "utf-8")+"</h1>");
		} else {
			out.println("Erro al crear la cookie");
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