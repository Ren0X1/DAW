import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class index extends HttpServlet {
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Prueba de cookies</title>");
		out.println("</head>");
		out.println("<body>");
		String codigo = request.getParameter("codigo");
		cargarCodigos(getServletConfig().getInitParameter("codigos"));
		out.println("<form action='jugar' method='post'>");
		out.println("Usuario <input type='text' name='usuario'/><br><br>");
		out.println("Codigo  <input type='text' name='codigo'/><br><br>");
		out.println("<input type='submit' value='Jugar!'/>");
		out.println("</form>");

		out.println("<form action='index' method='post'>");
		out.println("<input type='submit' name='codigo' value='Obtener codigo'/>");
		out.println("</form>");
		if (codigo!=null) {
			RequestDispatcher reqDispatch = request.getRequestDispatcher("crearCodigo");
			reqDispatch.include(request,response);
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

	private void cargarCodigos(String codes) {
		String[] parts = codes.split("\\/");
		HashMap mapa = new HashMap<Integer, String>();
		for (int i=0;i!=parts.length;i++) {
			mapa.put(i,parts[i]);
		}
		getServletContext().setAttribute("MapaCodigos", mapa);
	}
}