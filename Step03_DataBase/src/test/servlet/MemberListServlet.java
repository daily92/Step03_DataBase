package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.MemberDao;
import test.dto.MemberDto;
/*
 * 회원 목록을 출력할 서블릿
 */

//서블릿은 / 하는 순간 그 프로젝트 (여기서는 Step03_DataBase) 안에 들어와 있다는 가정
//그러므로 서블릿에 절대 경로 처럼  Step03_DataBase 쓰면 안됨여!!!
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MemberDao 객체를 이용해서 회원 목록을 얻어온다.
			//MemberDao.getInstance()=> Dao 의 참조값 얻어와서
			//.getList() => list 얻어오기
		List<MemberDto> list= MemberDao.getInstance().getList();
		// 클라이언트에게 출력하기
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'/>");
		pw.println("<title>회원 목록 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		//  /member/insertform.html 로 가겟네
		//정적인 페이지 요청 => 서블릿 만들 것이 아니라, 저 위치(/member)에 insertform.html 만들면 돼
		//WebContent 에 member 폴더 만들고 그 안에 insertform.html 만들기
		pw.println("<a href='insertform.html'>회원 정보 추가</a>");	
		pw.println("<h3>회원 목록 입니다.</h3>");

		pw.println("<table>");
			pw.println("<thead>");
				pw.println("<tr>");
					pw.println("<th>번호</th>");
					pw.println("<th>이름</th>");
					pw.println("<th>주소</th>");
					pw.println("<th>삭제</th>");
					pw.println("<th>수정</th>");
				pw.println("</tr>");
			pw.println("</thead>");
			pw.println("<tbody>");
				// 반복문 이용해서 회원 목록 출력 하기
				for(MemberDto tmp:list) {
					pw.println("<tr>");
						pw.println("<td>"+tmp.getNum()+"</td>");
						pw.println("<td>"+tmp.getName()+"</td>");
						pw.println("<td>"+tmp.getAddr()+"</td>");
						//삭제 누르면 http://localhost:8888/Step03_DataBase/member/delete
						//즉, 만일 서블릿 만들면 @WebServlet("/member/delete") 로 맵핑!
						pw.println("<td><a href='delete?num="+tmp.getNum()+"'>삭제</a></td>");
						pw.println("<td><a href='updateform?num="+tmp.getNum()+"'>수정</a></td>");
					pw.println("</tr>");
				}
			pw.println("</tbody>");
		pw.println("</table>");
		
		pw.println("</body>");
		pw.println("</html>");
	}
}
