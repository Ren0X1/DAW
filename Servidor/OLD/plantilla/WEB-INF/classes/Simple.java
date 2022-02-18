import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Simple extends HttpServlet {
	public void proceso(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter out =response.getWriter();
		out.println("<!doctype html>")
		out.println("<html>")
			out.println("<head>")
				out.println("<meta charset="+"utf-8"+">")
				out.println("<title>Documento sin título</title>")
			out.println("</head>")
			out.println("<body>")
			
			out.println("</body>")
		out.println("</html>")
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		proceso(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		proceso(request,response);
	}
}