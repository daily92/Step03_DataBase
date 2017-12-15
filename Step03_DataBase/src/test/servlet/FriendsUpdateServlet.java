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

@WebServlet("/friends/update")
public class FriendsUpdateServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num= Integer.parseInt(request.getParameter("num"));
		String name= request.getParameter("name");
		String phone= request.getParameter("phone");
		String regdate= request.getParameter("regdate");
		
		FriendsDto dto= new FriendsDto(num,name,phone,regdate);
		FriendsDao.getInstance().update(dto);
		
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
			pw.println("<script>");
				pw.println("alert('수정 완료!');");
				pw.println("location.href='list';");
			pw.println("</script>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
