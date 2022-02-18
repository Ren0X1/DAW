package imagen.respuesta.metodo3;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta3 extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ServletOutputStream out = null;
		try {
			response.setContentType("image/jpg");
			//línea que se modifica
			response.setBufferSize(100);
			RandomAccessFile raf = new RandomAccessFile(new File(getServletContext().getRealPath("/imagen/img.jpg")), "r" );
			response.setContentLength((int)raf.length());
			out = response.getOutputStream();
			int b;
			while((b=raf.read())!=-1 ) {
				out.write((byte)b);
				response.flushBuffer();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		proceso(request,response);
	}

	@Override
	protected void doPost (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		proceso(request,response);
	}
}