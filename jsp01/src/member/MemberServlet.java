package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 수행중");
		doHandle(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 수행중");
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller 수행중");
		
		//한글 인코딩 설정
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		PrintWriter out = response.getWriter(); // out : 웹브라우저를 의미
		memberDAO dao = new memberDAO();
		
		//작업 구분하는 인자 : command=>입력, 수정, 삭제, 검색, 조회 등...
		String command = request.getParameter("command");
		
		if (command !=null && command.contentEquals("addMember")) { // 입력 처리
			
			// form으로부터 넘어온 인자값을 받아 처리
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			// 인자값을 DTO 객체에 저장
			memberDTO dto2 = new memberDTO();
			dto2.setId(_id);
			dto2.setPwd(_pwd);
			dto2.setName(_name);
			dto2.setEmail(_email);
			
			// DB연결하여 자료 추가
			dao.addMember(dto2);
		}else if(command != null && command.equals("delMember")) {
			// 넘어온 인자id값을 저장
			String id = request.getParameter("id");
			
			// DB연결하여 자료 삭제
			dao.delMember(id);
		}else if(command != null && command.equals("getMember")) {
			String id = request.getParameter("id");
			
			//DB연결하여 자료 얻기
			memberDTO dto2 = dao.getMember(id);
			request.setAttribute("dto", dto2);
			
			String url = "/member2/updateMemberForm.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
		else if(command != null && command.equals("updateMember")) {
			String id = request.getParameter("id");
			// DB연결하여 자료 수정
			// form으로부터 넘어온 인자값을 받아 처리
			
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			// 인자값을 DTO 객체에 저장
			memberDTO dto2 = new memberDTO();
			dto2.setId(_id);
			dto2.setPwd(_pwd);
			dto2.setName(_name);
			dto2.setEmail(_email);
			
			dao.updateMember(dto2);
		}
		// 회원 정보 조회
		memberDAO dto = new memberDAO();
		List list = dto.listMembers();
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center'>");
		out.print("<td>아이디</td>");
		out.print("<td>비밀번호</td>");
		out.print("<td>이름</td>");
		out.print("<td>이메일</td>");
		out.print("<td>가입일</td><td>삭제</td><td>수정</td></tr>");
		
		for(int i=0; i<list.size(); i++) {
			memberDTO dto1 = (memberDTO) list.get(i);
			
			String id = dto1.getId();
			String pwd = dto1.getPwd();
			String name = dto1.getName();
			String email  = dto1.getEmail();
			Date joinDate = dto1.getJoinDate();
			
			out.print("<tr><td>"+id+"</td>");
			out.print("<td>"+pwd+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+email+"</td>");
			out.print("<td>"+joinDate+"</td>");
			out.print("<td><a href='/jsp01/member?command=delMember&id="+id+"'>삭제</a>");
			out.print("<td><a href='/jsp01/member?command=getMember&id="+id+"'>수정</a></tr>");
		}
		out.print("</table></body></html>");
		out.print("<a href='/jsp01/member/MemberForm.jsp'>새 회원 등록하기</a>");
	}

}