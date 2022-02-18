import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class crearCodigo extends HttpServlet {
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String newcode = generarCodigos();
		out.println("<p>"+newcode+"</p>");
		HashMap mapa = (HashMap)getServletContext().getAttribute("MapaCodigos");
		mapa.put(mapa.size()+1,newcode);
	}

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}

	private String generarCodigos() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
		.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		.limit(targetStringLength)
		.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		.toString();

		return generatedString;
	}
}