import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class verCookies extends HttpServlet{
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		Cookie [] vectorCookies=request.getCookies();
		if (vectorCookies!=null) {
			out.println("<h3>La cookies son: <h3>");
			for (Cookie c:vectorCookies) {
				out.println(c.getName()+ "    "+c.getValue()+"</br>");
			}
		} else {
			out.println("<h3>No existen cookies para mostrar<h3>");
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