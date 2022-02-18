import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.Pregunta;

public class Finalizar extends HttpServlet {
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        String[] comentarios = new String[500];
        getServletContext().setAttribute("comentarios", comentarios);
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Encuestas AlejandroMendoza</title>");
		out.println("</head>");
		out.println("<body>");
        out.println("<h2>Has respuesto la encuesta</h2>");
        ArrayList<Integer> respuestasFinales = (ArrayList<Integer>)getServletContext().getAttribute("respuestas");
        HashMap<Integer,Pregunta> mapa = (HashMap<Integer,Pregunta>)getServletContext().getAttribute("mapaPreguntas");
        for (int i=0;i!=respuestasFinales.size();i++) {
			out.println(mapa.get(i).getTituloPregunta()+" .- "+respuestasFinales.get(i));
			out.println("<br>");
		}
        out.println("<form action='Finalizar' method='GET'>");
        out.println("Quieres dejarnos un comentarios?");
        out.println("<input type='text' name='comentario'><br>");
        out.println("<input type='submit' value='Enviar'>");
        out.println("</form>");

        out.println("<form action='index' method='POST'>");
        out.println("<input type='submit' value='Cancelar'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
	}
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		//processRequest(request, response);
        String[] comentarios = (String[])getServletContext().getAttribute("comentarios");
        HttpSession session=request.getSession();
        //FALTA PONER EL USUARIO EN LA SESSION Y COGERLO AQUI

        String coment = "Nombre Usuario: "+" .- "+request.getParameter("comentario");
        int k=0;
        while(k!=comentarios.length) {
            if (comentarios[k]==null) {
                comentarios[k]=coment;
                k=comentarios.length;
            } else {
                k++;
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Encuestas AlejandroMendoza</title>");
		out.println("</head>");
		out.println("<body>");
        out.println("<h1>Comentarios</h1>");
        for (int i=0;i!=comentarios.length;i++) {
            if (comentarios[i]!=null) {
                out.println("<p>"+comentarios[i]+"</p>");
            }
        }
        out.println("<h1>Medias</h1>");
        ArrayList<Integer> respuestasFinales = (ArrayList<Integer>)getServletContext().getAttribute("respuestas");
        HashMap<Integer,Pregunta> mapa = (HashMap<Integer,Pregunta>)getServletContext().getAttribute("mapaPreguntas");
        int[] media = new int[respuestasFinales.size()];
        for (int i=0;i!=respuestasFinales.size();i++) {
			media[i]=respuestasFinales.get(i);
		}
        int mediaa=0;
        for (int i=0;i!=media.length;i++) {
			mediaa=mediaa+i;
		}

        out.println("<p>Media total: "+mediaa/(respuestasFinales.size()+1)+"</p>");
        out.println("<a href='index'>Volver al inicio</a>");
        out.println("</body>");
        out.println("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
}
