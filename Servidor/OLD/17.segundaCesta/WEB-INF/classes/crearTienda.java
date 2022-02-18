import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import pojos.Articulo;

public class crearTienda extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		cargarTienda(getServletConfig().getInitParameter("articulos"));
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		HttpSession session=request.getSession();
		Date fechaInicioSesion = new Date(miSesion.getCreationTime());
		HashMap cesta = new HashMap<Integer, Articulo>();
		HashMap mapa = (HashMap)getServletContext().getAttribute("MapaArt");
		String n;
		if (session.isNew()) {
			n=request.getParameter("usuario");
			session.setAttribute("nombreUser", n);
			session.setAttribute("cesta", cesta);
		} else {
			n=(String)session.getAttribute("nombreUser");
			cesta=(HashMap)session.getAttribute("cesta");
		}

		String articulos[] = request.getParameterValues("articulos");
		if (articulos != null) {
			for (int i=0;i<articulos.length;i++) {
				mapa.get(i).reducir(articulos[i]);
				cesta.put(mapa.get(i));
			}

			session.setAttribute("cesta",cesta);
		}

        out.print("Bienvenido "+n+"<br><br><br>");    
		out.println("<form action='crearTienda'>");
		Iterator it = mapa.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Object x = pair.getKey();
			Articulo Art = (Articulo)mapa.get(x);
			int cod = Art.getCodigo();
			String nombre = Art.getNombre();
			String uni = ""+Art.getUnidades();
			if (uni=="0") {
				uni = "Sin Stock disponible en este momento";
			}
			out.println("<input type='number' value='0' name='articulos'> "+cod+".-  "+nombre+" - Unidades: "+uni+"<br><br>");
			it.remove();
		}		
		out.println("<input type='submit' value='Comprar'>");
		out.println("<input type='submit' value='Ver Cesta'>");
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

	private void cargarTienda(String articulos) {
		String[] parts = articulos.split("\\/");
		HashMap mapa = new HashMap<Integer, Articulo>();
		for (int i=0;i!=parts.length;i++) {
			String[] v = parts[i].split("\\:");
			String nombre = v[0];
			int unidades = Integer.parseInt(v[1]);
			double precio = Integer.parseInt(v[2]);
			String img = v[3];
			mapa.put(i+1, new Articulo(i+1, nombre, unidades, precio, img));
		}
		getServletContext().setAttribute("MapaArt", mapa);
	}
}