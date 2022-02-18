import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import pojos.*;

public class ComprobarJugador extends HttpServlet{
	
	public void proceso (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		ServletContext contexto = getServletContext();
		
		Lista lista = (Lista) contexto.getAttribute("juegoAhorcado");
		HashMap<String,Jugador> jugadores = lista.getJugadores();
		String nombreUsuario = request.getParameter("nombreUsu");
		int puntuacion; 
		
		if(jugadores.containsKey(nombreUsuario)){
			puntuacion = jugadores.get(nombreUsuario).getPuntuacion();
		}else{
			Jugador jug = new Jugador(nombreUsuario, request.getParameter("passwd"), 0);
			jugadores.put(nombreUsuario, jug);
			puntuacion = 0;
		}
		
		request.setAttribute("puntuacion", puntuacion);
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		proceso(request,response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		proceso(request,response);
	}
}