package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.MemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 삭제할 회원의 번호를 읽어온다
			//request.getParameter() 하면 무조건 String type 불러오므로,
			//Integer.parseInt() 해 주는 것!
			//ex. "5"  => 5
		int num= Integer.parseInt(request.getParameter("num"));
		
		// 2. MemberDao 를 이용해서 회원정보를 삭제하고
		MemberDao.getInstance().delete(num);
		
		// 3. 응답한다.
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'/>");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
			pw.println("<p>회원정보를 삭제 했습니다</p>");
			//<a href='list'> => 앞에 / 없네? 상대경로 => 현재위치(/member)에서 list로
			// => 	/member/list	로 주소 이동
			pw.println("<a href='list'>회원 목록보기</a>");
		pw.println("</body>");
		pw.println("</html>");
		
	}
}
