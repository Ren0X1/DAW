
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ComprobarCombinacion extends HttpServlet{
	
	public void proceso (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		//Variables........................................................................................
		HttpSession miSesion = request.getSession();
		Map<Integer,String> estadoApuestas = (Map<Integer,String>) miSesion.getAttribute("estadoApuestas");
		ArrayList<Integer> combGanadora = (ArrayList<Integer>) getServletContext().getAttribute("combinacionGanadora");
		
		int numApuesta = Integer.parseInt(request.getParameter("numApuesta"));
		boolean ganador = true;
		String mensaje = "";
		
		//Recorremos los valores seleccionados para ver si todos están en la combinacionGanadora
		int[] valoresSeleccionados;
		String[] valores = request.getParameterValues("apuesta");
		if(valores.length >= 3){
			for(int i = 0; i < 3; i++){
				int valorEntero = Integer.parseInt(valores[i]);
				if(!combGanadora.contains(valorEntero)){
					ganador = false;
				}
			}
		}else{
			mensaje = "Por favor, seleccione tres números.";
		}
		
		//Cambiamos el estado de la apuesta en el mapa
		if(!ganador){
			estadoApuestas.put(numApuesta,"Perdida");
			mensaje = "APUESTA PERDIDA. Otra vez será.";
		}else{
			estadoApuestas.put(numApuesta,"Ganada");
			mensaje = "APUESTA GANADA!! ENHORABUENA!!";
		}
		
		request.setAttribute("mensaje", mensaje);
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