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
		String pelota=request.getParameter("pelota");
		String bicicleta=request.getParameter("bicicleta");
		String coche=request.getParameter("coche");
		String raqueta=request.getParameter("raqueta");
		
		if (nomCook!=null && nomCook!="") {
			String valCook = "";
			if (pelota!=null && pelota!="") {
				valCook+="pelota"+"|";
			}
			
			if (bicicleta!=null && bicicleta!="") {
				valCook+="bicicleta"+"|";
			}
			
			if (coche!=null && coche!="") {
				valCook+="coche"+"|";
			}
			
			if (raqueta!=null && raqueta!="") {
				valCook+="raqueta"+"|";
			}
			
			String x = URLEncoder.encode(valCook, "UTF-8");
			
			Cookie c = new Cookie ("u_"+nomCook,x);
			c.setMaxAge(3600);
			c.setPath("/");
			response.addCookie(c);
			response.sendRedirect("verCarrito");
			//out.println(URLDecoder.decode(c.getName(), "utf-8");
		} else {
			out.println("Erro al crear la compra");
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