package com.khie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SelectServlet() {
        
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// index.jsp 페이지에서 요청
		// ==> DEPT 테이블의 전체 부서 목록을
		//		화면에 보여달라고 요청(비지니스 로직)
		// 해당 요청에 대해서 응답.
		
		// 1단계: DB와 연결 작업을 진행
		// 1단계 시 준비 과정
		// - DAO(Data Access Object) 객체 준비.
		// - DTO(Data Transfer Object) 객체 준비.
		DeptDAO dao = new DeptDAO();
		
		// 2단계: DB에서 DEPT 테이블의 전체 목록 조회
		List<DeptDTO> deptList = dao.selectList(); // sql로 가져온 dao의 객체를 List<DeptDTO>가 공유한다.
		
		// 3단계: deptList 데이터(DB)를 View 페이지로 보내주기
		request.setAttribute("List", deptList);
		
		// 4단계: 실제로 페이지로 이동을 진행
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp"); // 페이지 경로 지정
		rd.forward(request, response); // 실제로 페이지 이동이 진행되는 코드
		
	}

}
