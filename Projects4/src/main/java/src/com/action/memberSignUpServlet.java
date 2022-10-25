package src.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.controller.ActionForward;
import src.com.model.LoginDAO;
import src.com.model.LoginDTO;

/**
 * Servlet implementation class memberSignUpServlet
 */
@WebServlet("/signUp.do")
public class memberSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberSignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String last = request.getParameter("lastname");
		String first = request.getParameter("firstname");
		int phone = Integer.parseInt(request.getParameter("phone"));
		int birth = Integer.parseInt(request.getParameter("birth"));
		String mail = request.getParameter("email");
		String mail_Id = "", mail_Domain = "";
		
		StringTokenizer st = new StringTokenizer(mail, "@");
		while(st.hasMoreTokens()) {
			mail_Id = st.nextToken();
			mail_Domain = st.nextToken();
		}
		
		LoginDTO userInfor = new LoginDTO();
		userInfor.setPmember_name(last+first);
		
		userInfor.setPmember_email(mail_Id);
		userInfor.setPmember_domain(mail_Domain);
		
		userInfor.setPmember_code(usercodeMaker());
		
		userInfor.setFirstname(first);
		userInfor.setLastname(last);
		
		userInfor.setPmember_birth(birth);
		userInfor.setPmember_phone(phone);
		
		LoginDAO dao = LoginDAO.getInstance();
		int check = dao.memberSignUp(userInfor);
		
		PrintWriter out = response.getWriter();
		out.println(check); // memberSignUp.jsp
		
	}
	
	protected long usercodeMaker() {
		long result = 0;
		
		Calendar calendar = Calendar.getInstance();
		LoginDAO dao = LoginDAO.getInstance();
		
		while(true) {
			String year = Integer.toString(calendar.get(Calendar.YEAR));
			String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
			String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
			int ran = (int)(Math.random()*10000);
			
			result = Long.parseLong(year.substring(2) + month + day + ran);
			boolean check = dao.memberCodeCheck(result);
			if(check == true) {
				continue;
			} else {
				break;
			}
		}
		return result;
	}
}
