
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import pojos.*;

public class Fin extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();
		ServletContext contexto = getServletContext();
		HttpSession sesion = request.getSession();
		
		((Chat)contexto.getAttribute("chat")).getObtenerSala((String)sesion.getAttribute("salaElegida")).setRemoveUsuario((Usuario)sesion.getAttribute("usuario"));
		sesion.invalidate();
		
		RequestDispatcher rd = contexto.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		proceso(request,response);
		
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		proceso(request,response);
	}
}