import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.Pregunta;

public class index extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cargarPreguntas(getServletContext().getInitParameter("Preguntas"));
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Encuestas AlejandroMendoza</title>");
		out.println("</head>");
		out.println("<body>");
		HttpSession session=request.getSession();
		String numjuegos = "0";
		if (!session.isNew()) {
			numjuegos=(String)session.getAttribute("numJuegos");
			int x = Integer.parseInt(numjuegos)+1;
			numjuegos = ""+x;
			session.setAttribute("numJuegos", numjuegos);
			Cookie[] vectorCookies=request.getCookies();
			if (vectorCookies!=null) {
				for (Cookie c:vectorCookies) {
					if (c.getName().equals("numJuegos")) {
						numjuegos=c.getValue();
					}
				}
			} else {
				out.println("<h3>No existen cookies para mostrar<h3>");
			}
		} else {
			session.setAttribute("numJuegos", "0");
			Cookie c = new Cookie("numJu","0");
			c.setMaxAge(30000);
			response.addCookie(c);
		}
		
		out.println("<h1>Se han realizado "+numjuegos+" encuestas desde este terminal</h1>");
		out.println("<h2> Encuestas Hotel Ronda</h2><br>");
		out.println("<form action='RealizarEncuesta' method='POST'>");
		out.println("Usuario <input type='text' name='usuario'><br><br>");
		out.println("Numero Habitacion <input type='text' name='habitacion'><br><br>");
		out.println("<input type='submit' value='Hacer encuesta'>");
		out.println("</form>");
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

	private void cargarPreguntas(String preguntas) {
		String[] parts = preguntas.split("\\|");
		HashMap<Integer, Pregunta> mapa = new HashMap<Integer, Pregunta>();
		for (int i=0;i!=parts.length;i++) {
			String[] v = parts[i].split("\\:");
			String titulo = v[0];
			String[] respuestas = v[1].split("\\/");
			int[] valoresRespuestas = cargarValores(respuestas.length);
			mapa.put(i, new Pregunta(i+1, titulo, respuestas, valoresRespuestas));
		}
		getServletContext().setAttribute("mapaPreguntas", mapa);
	}

	private int[] cargarValores(int l) {
		int[] x = new int[l];
		for (int i=0;i!=l;i++) {
			x[i]=i+1;
		}
		return x;
	}
}