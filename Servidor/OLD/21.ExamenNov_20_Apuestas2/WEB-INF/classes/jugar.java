import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class jugar extends HttpServlet {
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String usuario = request.getParameter("usuario");
		String codigo = request.getParameter("codigo");
		Boolean puedeJugar = false;
		String mensaje = "";
		HttpSession session=request.getSession();
		if (session.isNew()) {
			session.setAttribute("usuario", usuario);
			session.setAttribute("codigo", codigo);
			HashMap mapa = (HashMap)getServletContext().getAttribute("MapaCodigos");
			puedeJugar = true;
			//ComprobarCodigo y Asignar codigo antes de borrarlo (Falta)
			mapa.values().remove(codigo);
		} else {
			usuario=(String)session.getAttribute("usuario");
			codigo=(String)session.getAttribute("codigo");
			puedeJugar = false;
			mensaje = "Ya has jugado desde esta maquina prueba de nuevo dentro de un tiempo.";
		}

		if (puedeJugar) {
			out.println("<h1>Juego</h1>");
			out.println("<form action='jugar'>");
				out.println("<input type='checkbox' name'apuesta1'/>");
				out.println("<input type='checkbox' name'apuesta1'/>");
				out.println("<input type='checkbox' name'apuesta1'/>");
				out.println("<input type='checkbox' name'apuesta1'/>");
				out.println("<input type='checkbox' name'apuesta1'/>");
				out.println("<input type='checkbox' name'apuesta1'/>");
				out.println("<input type='checkbox' name'apuesta1'/>");
			out.println("</form>");

			out.println("<form action='jugar'>");
				out.println("<input type='checkbox' name'apuesta2'/>");
				out.println("<input type='checkbox' name'apuesta2'/>");
				out.println("<input type='checkbox' name'apuesta2'/>");
				out.println("<input type='checkbox' name'apuesta2'/>");
				out.println("<input type='checkbox' name'apuesta2'/>");
				out.println("<input type='checkbox' name'apuesta2'/>");
				out.println("<input type='checkbox' name'apuesta2'/>");
			out.println("</form>");

			out.println("<form action='jugar'>");
				out.println("<input type='checkbox' name'apuesta3'/>");
				out.println("<input type='checkbox' name'apuesta3'/>");
				out.println("<input type='checkbox' name'apuesta3'/>");
				out.println("<input type='checkbox' name'apuesta3'/>");
				out.println("<input type='checkbox' name'apuesta3'/>");
				out.println("<input type='checkbox' name'apuesta3'/>");
				out.println("<input type='checkbox' name'apuesta3'/>");
			out.println("</form>");
			
		} else {
			out.println("<h1>"+mensaje+"</h1>");
		}
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