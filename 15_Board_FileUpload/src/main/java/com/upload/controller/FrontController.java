package com.upload.controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.action.Action;
import com.upload.action.ActionForward;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length() + 1);
		
		Action action = null;
		ActionForward forward = null;
		
		// java.util.Properties 클래스
		// - HashTable의 하위 클래스.
		// 보통은 환경변수 및 속성 값을 Properties 파일에
		// 저장하여 쉽게 접근할 수 있는 장점이 있다.
		// [- Properties 파일은 일련의 키(key) - 값(value)의 한 쌍으로 이루어져 있음.]
		// [- 보통 파일에 저장 하여 사용함, 파일 이름은 *.properties 이름을 사용]
		// - FileInputStream 클래스에 Properties 파일을 인자로 넣어서 스트림으로 부터 파일을 가져올 때 많이 사용
		// 인자로 들어온 Properties 파일을 읽게 됨
		// - 읽어 들일 때 사용하는 메소드는 load() 라는 메서드를 이용하여 파일을 읽어 들이게 됨
		Properties pro = new Properties();
		
		// mapping.properties File 선택
		FileInputStream fis = new FileInputStream("C:\\NCS\\git\\kh_JSP\\15_Board_FileUpload\\src\\main\\java\\com\\upload\\controller\\mapping.properties");
		// .properties 파일을 읽어드림
		pro.load(fis);
		// bbs_list.do와 같은 property 값을 읽어드림
		String value = pro.getProperty(command); 
		System.out.println("Value= " + value);
		
		if(value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value, "|");
				String url_1 = st.nextToken(); // "execute"
				String url_2 = st.nextToken(); // "패키지명.클래스명"
			
				/*
				 * 동적 객체 생성: newInstance()
				 * 	- Class 객체를 이용하면 new 연산자의 사용 없이 동적으로 객체 생성을 할 수 있다
				 * 	- 코드 작성 시에 클래스의 이름을 결정할 수 없고, 런타임(실행) 시에 클래스의 이름이 결정되는
				 * 	- 경우에 유용하게 사용이 됨
				 * 
				 * 	- newInstance() 메소드는 기본생성자를 호출해서 객체를 생성하기 때문에 반드시 클래스에 기본생성자가
				 * 	존재해야 한다.
				 *  - 예외가 발생할 수 있는데 해당 클래스가 추상클래스이거나 인터페이스일 경우 발생하고 예외는 클래스의
				 *  생성자가 접근제한자로 인해 접근할 수 없는 경우에 발생을 함. 따라서 예외 처리가 필요함
				 *  
				 *  - 반환 타입은 Object 타입이라서 형변환 해주면 되지만, 클래스의 타입을 모르는 상태이므로 형변환을
				 *  해주기 어렵다. 그래서 인터페이스를 사용해야 한다.
				 *  
				 *  - Class.forName(class 이름)은 파라미터로 받은 class 이름에 해당하는 클래스로 로딩한 후 클래스에
				 *  해당하는 인스턴스를 리턴 해줌
				 *  newInstance() 메서드는 로딩한 클래스의 객체를 생성하는 메서드이고, 생성된 객체를 동적 객체 생성이라고 한다.
				 *  
				 */
				// 동적으로 객체 생성
				//ActionForward forward = null;
				try {
					Class<?> url = Class.forName(url_2);
					// 첫번째 방법 (deptracted - 더 이상 사용되지 않는)
					//action = (Action)url.newInstance();
					// getContructor() 메서드는 파라미터가 없는 기본생성자를 가져오는 메서드
					Constructor<?> constructor = url.getConstructor(); // 매개변수타입을 넣어줘야하는데 빈칸으로 주면 Constructor 객체 타입으로 반환됨
					action = (Action)constructor.newInstance();
					//url.getConstructor() 기본 생성자를 가져와서 Constructor객체 타입으로 받은 다음 newInstance()로 가져와서 action에 넣어줌
					
					//ActionForward forward = null;
					// call by reference 개념
					forward = action.execute(request, response); // 비지니스 로직 호출
					// null 값인데 public ActionForward execute < BbsListAction 참조
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		} else { // value 값이 "execute"가 아닌 경우
			// view page로 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
		}
		
		//ActionForward forward = null; false, list.jsp
		if(forward != null) {
			if(forward.isRedirect()) { // true인 경우
				response.sendRedirect(forward.getPath());
			} else { // false 인 경우 view page로 이동
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		} 
	} // service 
}
