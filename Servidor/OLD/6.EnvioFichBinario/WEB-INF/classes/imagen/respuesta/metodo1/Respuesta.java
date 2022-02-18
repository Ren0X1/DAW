package imagen.respuesta.metodo1;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Respuesta extends HttpServlet {
	public void proceso (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ServletOutputStream out = null;
		try {
			response.setContentType("image/jpg");
			RandomAccessFile raf = new RandomAccessFile(new
			File(getServletContext().getRealPath("/imagen/img.jpg")), "r" );
			response.setContentLength((int) raf.length());
			out = response.getOutputStream();
			byte [] datas = new byte [(int)raf.length()];
			while ( (raf.read( datas )) > 0 ) {
				out.write(datas);
			}
			out.flush();
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