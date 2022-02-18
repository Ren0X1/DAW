
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Jugar extends HttpServlet{
	
	public void proceso (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		response.setContentType ("text/html");
		PrintWriter out =response.getWriter();
		
		//CABECERA.......................
		RequestDispatcher rdCab = getServletContext().getRequestDispatcher("/cabecera.html");
		rdCab.include(request, response);
		
		//Variables........................................
		HttpSession miSesion = request.getSession();
		String codigo = request.getParameter("codUsado");
		Map<String,String> listaCodigosAsignados = (HashMap<String,String>)getServletContext().getAttribute("codigosAsignados");
		
		//Añadimos el estado de las apuestas a la sesión
		if(miSesion.isNew()){
			Map<Integer,String> estadoApuestas = new HashMap();
			estadoApuestas.put(1,"");
			estadoApuestas.put(2,"");
			estadoApuestas.put(3,"");
			miSesion.setAttribute("estadoApuestas", estadoApuestas);
			miSesion.setAttribute("nombreUsu", request.getParameter("nomUsu"));
		}
		
		String nombreUsuario = (String)miSesion.getAttribute("nombreUsu");
		Map<Integer,String> estadoApuestas = (Map<Integer,String>) miSesion.getAttribute("estadoApuestas");
		
		out.println("<h3>Buenas tardes "+ nombreUsuario +", tienes 3 oportunidades para el éxito. Recuerde que solo se aceptan los 3 primeros marcados.</h3>");
		//Pinta cada apuesta
		for(int j = 0; j < 3; j++){
			out.println("<form action='Jugar' method='POST'>");
			out.println("<input type='hidden' name='numApuesta' value='"+ (j+1) +"'>");
			out.println("<table>");
			out.println("<tr><td rowspan='12'>Apuesta " + (j+1) +": </td></tr>");
			
			//Si el estado de la apuesta no está definido, se pintan los checks
			if(estadoApuestas.get(j+1).equals("")){
				//Pinta cada check de la apuesta y el boton apostar
				for(int i = 0; i < 11; i++){
					if(i == 10){
						out.println("<tr><td><input type='submit' value='Apostar'></td></tr>");
					}else{
						out.println("<tr><td>"+(i+1)+"<input type='checkbox' name='apuesta' value='"+ (i+1) +"'></td></tr>");
					}
				}
			}else{
				out.println("<tr><td rowspan='12' class='estado'>"+ estadoApuestas.get(j+1) +"</td></tr>");
			}
			out.println("</table>");
			out.println("</form>");
			
		}
		
		//Pintamos el mensaje que ha hecho ComprobarCombinacion, si existe
		if(request.getAttribute("mensaje") != null){
			out.println("<p id='msjPerso'>"+ ((String)request.getAttribute("mensaje")) +"</p>");
		}
		
		//Ponemos el codigo como usado
		listaCodigosAsignados.put(codigo,"usado");
				
		//PIE DE PAGINA.......................
		RequestDispatcher rdPie = getServletContext().getRequestDispatcher("/pie.html");
		rdPie.include(request, response);	
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		proceso(request,response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ComprobarCombinacion");
		rd.include(request, response);	
		
		proceso(request,response);
	}
}