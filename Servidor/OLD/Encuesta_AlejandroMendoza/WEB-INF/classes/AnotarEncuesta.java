import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnotarEncuesta extends HttpServlet {
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
        ArrayList<Integer> respuestasNuevas = (ArrayList<Integer>)getServletContext().getAttribute("respuestas");
        respuestasNuevas.add(Integer.parseInt(request.getParameter("puntuacion")));
        getServletContext().setAttribute("respuestas", respuestasNuevas);
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
}
