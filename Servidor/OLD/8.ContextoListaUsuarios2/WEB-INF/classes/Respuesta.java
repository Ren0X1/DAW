import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import pojos.*;

public class Respuesta extends HttpServlet {	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("listaUsers",  new HashMap<Integer, Usuario>());
		getServletContext().setAttribute("horaArranque", new GregorianCalendar());
	}
	
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<Integer, Usuario> users = (HashMap)getServletContext().getAttribute("listaUsers");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>INDEX</title>");
		out.println("</head>");
		out.println("<body style='background-color: "+getInitParameter("fondoApp")+"'>");
		out.println("Hora de arranque: "+((GregorianCalendar)getServletContext().getAttribute("horaArranque")).getTime());
		out.println("<br>");
		out.println("<h1>Usuario Conectados</h1>");
		out.println("<br>");
		String nombre = request.getParameter("nombre");
		String desconectar = request.getParameter("desconectar");
		if (desconectar!=null && desconectar!="") {
			users.remove(Integer.parseInt(desconectar));
		}
		int k=0;
		if (nombre!=null && nombre!="") {
			k=users.size()+1;
			users.put(users.size()+1, new Usuario(k,nombre,new GregorianCalendar()));
		}
		
		Iterator it = users.keySet().iterator();
		while(it.hasNext()){
			Integer key = (Integer)it.next();
			Calendar calendar = users.get(key).getHoraConex();
			out.println(users.get(key).getPuesto()+".- "+users.get(key).getNombreUsuario()+" "+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE));
			out.println("<br>");
		}
		out.println("<form action='Respuesta'>");
		out.println("	<input style='display: none;' value='"+k+"' name='desconectar' type='text'>");
		out.println("	<input style='display: none;' name='nombre' type='text'>");
		out.println("	<input type='submit' value='Desconectar'>");
        out.println("</form>");
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