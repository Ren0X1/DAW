import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Pregunta;

public class RealizarEncuesta extends HttpServlet {

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        ArrayList<Integer> respuestas = new ArrayList<Integer>();
        getServletContext().setAttribute("respuestas", respuestas);
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
        ServletContext contexto = getServletContext();
        HashMap<Integer,Pregunta> mapa = (HashMap<Integer,Pregunta>)contexto.getAttribute("mapaPreguntas");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Encuestas AlejandroMendoza</title>");
		out.println("</head>");
		out.println("<body>");
        String puntuacion = request.getParameter("puntuacion");
        if (puntuacion!=null) {
            RequestDispatcher reqDispatch = request.getRequestDispatcher("AnotarEncuesta");
			reqDispatch.include(request,response);
        }
		Iterator it = mapa.entrySet().iterator();
        int kl=0;
        int cnt=0;
        String encuesta = request.getParameter("donde");
        if (encuesta!=null) {
            kl=Integer.parseInt(encuesta)+1;
        }
		while (it.hasNext()) {
			Map.Entry<Integer,Pregunta> pair = (Map.Entry<Integer,Pregunta>)it.next();
            Object obj = pair.getKey();
            Pregunta Preg = (Pregunta)mapa.get(obj);
            String nombre = Preg.getTituloPregunta();
            String[] respuestas = Preg.getRespuestas();
            int[] valoresRespuestas = Preg.getValoresRespuestas();
            if (kl==respuestas.length) {
                RequestDispatcher reqDispatch = request.getRequestDispatcher("Finalizar");
                reqDispatch.forward(request, response);
            }
			if (kl==cnt) {
                out.println("<h2>"+nombre+"</h2>");
                out.println("<table style='margin:auto;width:600px'>");
                for (int x=0;x!=respuestas.length;x++) {
                    out.println("<form action='RealizarEncuesta' method='POST'>");
                    out.println("<td>");
                    out.println("<input style='margin:auto;width:170px;height:157px' type='image' src='"+respuestas[x]+"' name='valorPregunta' value='"+respuestas[x]+"'>");
                    out.println("</td>");
                    out.println("<input type='hidden' name='donde' value='"+kl+"'>");
                    out.println("<input type='hidden' name='puntuacion' value='"+valoresRespuestas[x]+"'>");
                    out.println("</form>");
                }
                out.println("</table>");
            }
            cnt++;
		}
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
}