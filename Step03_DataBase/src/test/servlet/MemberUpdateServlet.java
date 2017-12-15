package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.MemberDao;
import test.dto.MemberDto;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 깨지지 않게
		request.setCharacterEncoding("utf-8");
		// 1. 폼 전송되는 수정할 회원의 번호, 이름, 주소를 읽어온다
		int num= Integer.parseInt(request.getParameter("num"));
		String name= request.getParameter("name");
		String addr= request.getParameter("addr");
		
		// 2. MemberDao 를 이용해서 회원 정보를 수정하고
		MemberDto dto= new MemberDto(num, name, addr);
		MemberDao.getInstance().update(dto);
		
		// 3. 응답한다
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'/>");
		pw.println("<title>알림</title>");
		pw.println("</head>");
		pw.println("<body>");
			pw.println("<script>");
				pw.println("alert('수정했습니다.');");
				pw.println("location.href='list';");
			pw.println("</script>");
			
			//pw.println("<p>회원 정보를 수정하였습니다.</p>");
			//pw.println("<a href='list'>회원 목록보기</a>");
		pw.println("</body>");
		pw.println("</html>");
		
	}
}
