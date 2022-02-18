import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Index extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//Leemos los codigos del web xml y creamos un mapa con ellos
		//y su disponibilidad
		Map<String,String> listaCodigosAsignados = new HashMap();
		ArrayList<String> codLibres = new ArrayList();
		String codigosInit = getServletContext().getInitParameter("Codigos");
		String[] codigos = codigosInit.split(";");
		for(String cod : codigos){
			String[] partesCod = cod.split(",");
			if(partesCod[0].equals("libre")){
				codLibres.add(partesCod[1]);
			}else{
				//				          codigo	   disponibilidad
				listaCodigosAsignados.put(partesCod[1],partesCod[0]);				
			}

		}
		getServletContext().setAttribute("codigosAsignados", listaCodigosAsignados);
		getServletContext().setAttribute("codigosLibres", codLibres);
		
		//Combinacion ganadora...............
		getServletContext().setAttribute("combinacionGanadora",  new ArrayList<Integer>(List.of(2, 7, 8)));
	}
	
	public void proceso (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		response.setContentType ("text/html");
		PrintWriter out =response.getWriter();
		ServletContext contexto = getServletContext();
		
		//CABECERA.......................
		RequestDispatcher rdCab = contexto.getRequestDispatcher("/cabecera.html");
		rdCab.include(request, response);
		
		//Variables..............................................................................
		Map<String,String> listaCodigosAsignados = (HashMap<String,String>)contexto.getAttribute("codigosAsignados");
		String mensaje = ""; 
		String procedencia = "";
		
		//En caso de que la llamada venga desde este servlet, llamamos a ComprobarCodigo.java
		if(request.getParameter("userComprobar") != null || request.getParameter("nombreNuevo") != null){
			procedencia = (String)request.getAttribute("procedencia");
			mensaje = (String) request.getAttribute("mensajeCodigo");
		}
		
		//Si viene desde el formulario de comprobar................................................................
		if(procedencia.equals("comprobar")){
			String estado = (String) request.getAttribute("estado");
			//Pintamos los datos:
			out.println("<p>Codigo: " + request.getParameter("codigo") + " Usuario: " + request.getParameter("userComprobar")+"</p>");
			out.println("<p>**Estado del c�digo: "+ estado +"</p>");
			out.println("<h1>"+ mensaje +"</h1>");	
		}
	
		//Pintamos seg�n si el usuario es correcto o no
		if(mensaje.contains("correcto")){
				out.println("<h1>Click en el bot�n para empezar a jugar: </h1>");
				out.println("<form action='Jugar'>");
				out.println("<input type='hidden' name='nomUsu' value='"+request.getParameter("userComprobar") +"'>");
				out.println("<input type='hidden' name='codUsado' value='"+ request.getParameter("codigo") +"'>");
				out.println("<input type='submit' value='A Jugar!!'></form>");
		}else{
			//Pintamos los formularios........................................
			out.println("<h2>Tengo un c�digo para jugar</h2>");
			out.println("<form action='Index' method='POST'><label>C�digo: </label>");
			out.println("<input type='text' name='codigo'>");
			out.println("<label>Usuario: </label>");
			out.println("<input type='text' name='userComprobar'>");
			out.println("<input type='submit' value='Comprobar'></form>");
		
			out.println("<h2>Obtener un c�digo</h2>");
			out.println("<form action='Index' method='POST'><label>Usuario: </label>");
			out.println("<input type='text' name='nombreNuevo'>");
			out.println("<input type='submit' value='Obtener'></form>");
		}
		
		//La llamada viene desde form nuevoUsuario
		if(procedencia.equals("nuevoUsuario")){
			out.println("<h2>"+ mensaje +"</h2>");
		}
			
		//Creamos la cookie de usuario Repetido
		Cookie c1 = new Cookie("usuarioRep","valueCookie");
		c1.setMaxAge(180);
		response.addCookie(c1);
		
		//PIE DE PAGINA.......................
		RequestDispatcher rdPie = contexto.getRequestDispatcher("/pie.html");
		rdPie.include(request, response);	
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		proceso(request,response);
		
		/*Cookie[] listaCookies = request.getCookies();
		for(Cookie c : listaCookies){
			if(c.getValue().equals("valueCookie")){
				out.println("<h1>Lo sentimos, pero ya se ha jugado desde esta m�quina...</h1>");
			}else{
				
			}
		}*/
			
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		RequestDispatcher rdComprobar = getServletContext().getRequestDispatcher("/ComprobarCodigo");
		rdComprobar.include(request, response);
		proceso(request,response);
	}
}