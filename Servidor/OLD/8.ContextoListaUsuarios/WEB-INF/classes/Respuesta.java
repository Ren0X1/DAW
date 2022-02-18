import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta extends HttpServlet {	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("listaUsers", new ArrayList<Usuario>());
		getServletContext().setAttribute("horaArranque", new GregorianCalendar());
	}
	
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServletContent = getServletContext();
		ArrayList<Usuario> users = ServletContent.getAttribute("listaUsers").getTime();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>INDEX</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Hora de arranque: "+((GregorianCalendar)ServletContent.getAttribute("horaArranque")).getTime());
		out.println("<br>");
		out.println("<h1>Usuario Conectados</h1>");
		out.println("<br>");
		String nombre = request.getParameter("nombre");
		if (nombre!=null && nombre!="") {
			users.add(new Usuario(users.size()+1,nombre,new GregorianCalendar()));
		}
		for (int i=0;i!=users.size();i++) {
			out.println(users.get(i).getPuesto()+".- "+users.get(i).getNombreUsuario()+"  ---  "+users.get(i).getHoraConex().getTime());
			out.println("<br>");
		}
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