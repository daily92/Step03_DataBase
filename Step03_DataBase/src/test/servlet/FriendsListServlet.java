package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.FriendsDao;
import test.dao.MemberDao;
import test.dto.FriendsDto;

@WebServlet("/friends/list")
public class FriendsListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FriendsDto> list= FriendsDao.getInstance().getList();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'/>");
		pw.println("<title>친구 목록 불러오기</title>");
		pw.println("</head>");
		pw.println("<body>");
		//친구 추가
		pw.println("<a href='insertform.html'>친구 추가</a>");
		pw.println("<h3>친구 목록 입니다.</h3>");
		pw.print("<table>");
			pw.print("<thead>");
				pw.print("<tr>");
					pw.print("<th>번호</th>");
					pw.print("<th>이름</th>");
					pw.print("<th>핸드폰</th>");
					pw.print("<th>날짜</th>");
				pw.print("</tr>");
			pw.print("</thead>");
			pw.print("<tbody>");
				for(FriendsDto tmp:list) {
					pw.println("<tr>");
						pw.println("<td>"+tmp.getNum()+"</td>");
						pw.println("<td>"+tmp.getName()+"</td>");
						pw.println("<td>"+tmp.getPhone()+"</td>");
						pw.println("<td>"+tmp.getRegdate()+"</td>");
						//친구 삭제
						pw.println("<td><a href='delete?num="+tmp.getNum()+"'>삭제</a></td>");
						//친구 수정
						pw.println("<td><a href='updateform?num="+tmp.getNum()+"'>수정</a></td>");
					pw.println("</tr>");
				}
			pw.print("</tbody>");
		pw.print("</table>");
		
		pw.println("</body>");
		pw.println("</html>");
	}
}
