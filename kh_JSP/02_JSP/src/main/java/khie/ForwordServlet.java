package khie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FowrdServlet
 */
@WebServlet("/forword")
public class ForwordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id").trim();
		String userPwd = request.getParameter("pwd").trim();
		
		// 기존에는 DB의 회원 관리 테이블에서 입력한 아이디와 비밀번호가 맞는지
		// 확인 하여 >> 회원이면 메인 페이지로 이동
		String dbId = "hong";
		String dbPwd = "1234";
		
		if(userId.equals(dbId)) {
			if(userPwd.equals(dbPwd)) {
				// 회원인 경우 메인 페이지로 이동 ==> 페이지 이동
				// 정보를 이동하는 페이지로 전달하는 방법
				request.setAttribute("name", "홍길동"); // setAttribute("key값", "value");
				request.setAttribute("addr", "서울시 중랑구 면목로");
				
				RequestDispatcher rd = request.getRequestDispatcher("./ex10.jsp"); // 페이지 경로지정
				rd.forward(request, response); // 페이지 이동 구문
			}
		}
		
		
		
		
	}

}
