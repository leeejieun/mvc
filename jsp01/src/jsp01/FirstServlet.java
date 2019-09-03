package jsp01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doHandle(request, response);
		System.out.println("doGet방식 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("doPost방식 호출");
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doHandle방식 호출");
		
		response.setContentType("text/html;charset=utf-8");//한글 인코딩 설정
		
		PrintWriter out = response.getWriter();//웹브라우저가 출력 대상
		out.print("<html><body><table><tr><td></td><td></td></tr></table>");
		out.print("<hr>안녕하세요 Servlet으로 작성한 웹페이지입니다.");
		out.print("</body></html>");
	}

}