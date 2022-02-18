import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("horaArranque", new GregorianCalendar());
	}
	
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ServletContent = getServletContext();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>INDEX</title>");
		out.println("</head>");
		cogerHora();
		String x;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int mes = calendar.get(Calendar.MONTH);
		if (mes>5 && mes <9) {
			x=ServletContent.getAttribute("fondoVerano");
		} else if (mes>-1 && mes <3) {
			x=ServletContent.getAttribute("fondoInvierno");;
		} else if (mes>8 && mes <12) {
			x=ServletContent.getAttribute("fondoOtoño");;
		} else if (mes>2 && mes <6) {
			x=ServletContent.getAttribute("fondoPrimavera");;
		}
		out.println("<body style='"+"background-image: url("+x+");'"+">");
		out.println("Hora de arranque: "+((GregorianCalendar)ServletContent.getAttribute("horaArranque")).getTime());
		out.println("<br>");
		out.println("Hora de entrada: "+((GregorianCalendar)ServletContent.getAttribute("horaEntrada")).getTime());
		out.println("</body>");
		out.println("</html>");
	}
	
	private void cogerHora() {
		getServletContext().setAttribute("horaEntrada", new GregorianCalendar());
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