package calculos;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Calculo extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();
		
		String capital = request.getParameter("capital");
		String interes = request.getParameter("interes");
		String anos = request.getParameter("anos");
		String periodo = request.getParameter("periodo");
		
		out.println("<!doctype html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset="+"utf-8"+">");
				out.println("<title>Formulario</title>");
				out.println("<link rel='stylesheet' href='css/style.css'>");
			out.println("</head>");
			out.println("<body>");
			if (capital!=null && capital!="" && interes!=null && interes!="" && anos!=null && anos!="" && periodo!=null && periodo!="" && periodo!="elegir") {
				double cap = Integer.parseInt(capital);
				double a = Integer.parseInt(anos);
				double per = Integer.parseInt(periodo);
				double inte = Integer.parseInt(interes);
				double i = (inte/100)/per;
				double np = a*per;
				double[][] tabla = new double[(int)np][3];
				double cuota = cap*i*Math.pow((1+i),np)/(Math.pow((1+i),np)-1);
				double capPendiente = cap;
				
				RequestDispatcher rd=null;
				rd =getServletContext().getRequestDispatcher("/Pintar");
				request.setAttribute("cap", cap);
				request.setAttribute("a", a);
				request.setAttribute("per", per);
				request.setAttribute("inte", inte);
				request.setAttribute("i", i);
				request.setAttribute("np", np);
				request.setAttribute("tabla", tabla);
				request.setAttribute("cuota", cuota);
				request.setAttribute("capPendiente", capPendiente);
				rd.forward(request, response);
			} else {
				out.println("<h1>Datos Erroneos, Imposible de realizar los calculos</h1>");
			}
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