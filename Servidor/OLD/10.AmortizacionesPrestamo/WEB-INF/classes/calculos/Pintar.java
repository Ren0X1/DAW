package calculos;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Pintar extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset="+"utf-8"+">");
				out.println("<title>Formulario</title>");
				out.println("<link rel='stylesheet' href='css/style.css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table>");
			out.println("<thead>");
			out.println("<tr>");
			out.println("<th>Indice</th>");
			out.println("<th>Interes</th>");
			out.println("<th>Capital Amortizado</th>");
			out.println("<th>Cuota</th>");
			out.println("<th>Capital Pendiente</th>");
			out.println("</tr>");
			out.println("</thead>");
			
				double cap = (double)request.getAttribute("cap");
				double a = (double)request.getAttribute("a");
				double per = (double)request.getAttribute("per");
				double inte = (double)request.getAttribute("inte");
				double i = (double)request.getAttribute("i");
				double np = (double)request.getAttribute("np");
				double[][] tabla = (double[][])request.getAttribute("tabla");
				double cuota = (double)request.getAttribute("cuota");
				double capPendiente = (double)request.getAttribute("capPendiente");
				
				for (int f=0;f!=np;f++) {
					double interesRecibo = capPendiente*i;
					double capAmortizado = cuota-interesRecibo;
					capPendiente = capPendiente-capAmortizado;
					tabla[f][0] = interesRecibo;
					tabla[f][1] = capAmortizado;
					tabla[f][2] = capPendiente;
					out.println("<tr>");
					out.println("<td>");
					out.println(f+1);
					out.println("</td>");
					out.println("<td>");
					out.println(String.format("%.2f", tabla[f][0])+"$");
					out.println("</td>");
					out.println("<td>");
					out.println(String.format("%.2f", tabla[f][1])+"$");
					out.println("</td>");
					out.println("<td>");
					out.println(String.format("%.2f", cuota)+"$");
					out.println("</td>");
					out.println("<td>");
					out.println(String.format("%.2f", tabla[f][2])+"$");
					out.println("</td>");
					out.println("</tr>");
				}
			out.println("</table>");
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