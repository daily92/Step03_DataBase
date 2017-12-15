package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.FriendsDao;
import test.dto.FriendsDto;

@WebServlet("/friends/updateform")
public class FriendsUpdateFormServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num= Integer.parseInt(request.getParameter("num"));
		
		FriendsDto dto= FriendsDao.getInstance().getData(num);
		
		//응답
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
			pw.println("<h3>친구 정보 수정 폼</h3>");
			pw.println("<form action='update' method='post'>");
				pw.println("<input type='hidden' name='num' value='"+dto.getNum()+"'/>");
				pw.println("<input type='text' name='name' value='"+dto.getName()+"'/>");
				pw.println("<input type='text' name='phone' value='"+dto.getPhone()+"'/>");
				pw.println("<input type='hidden' name='regdate' value='"+dto.getRegdate()+"'/>");
				pw.println("<button type='submit'>수정 완료</button>");
			pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
