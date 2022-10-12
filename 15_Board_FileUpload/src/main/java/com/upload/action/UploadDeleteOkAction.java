package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 글번호와 비밀번호를 가지고
		// DB에서 게시글을 삭제하는 비지니스 로직
		
		int no = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pwd");
		
		UploadDAO dao = UploadDAO.getInstance();
		
		// 글번호에 해당하는 게시글을 상세내역 조회
		UploadDTO dto = dao.uploadContent(no);
		
		// upload 폴더에 업로드된 파일까지 삭제
		String upload = "C:\\NCS\\git\\kh_JSP\\15_Board_FileUpload\\src\\main\\webapp\\upload";
		
		String fileName = dto.getFile();
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		if(pw.equals(dto.getPwd())) {
			int res = dao.deleteUpload(no);
			if(fileName != null) { //첨부파일 존재시
				File file = new File(upload + fileName);
				file.delete();
				
//				Path fileN = Paths.get(upload);
//				if(!fileName.equals(null)) {
//					Files.deleteIfExists(fileN);
//				} 게시판 삭제시 메서드 사용시 이런식으로 해야 할듯
			}
			System.out.println("res: " + res);
			if(res > 0) {
				forward.setRedirect(true);
				forward.setPath("upload_list.do");
				
			} else {
				out.println("<script>"
						+ "alert('삭제 실패');"
						+ "history.back();';"
						+ "</script>");
			}
		} else {
			out.println("<script>"
					+ "alert('패스워드를 확인해 주세요.');"
					+ "history.back();"
					+ "</script>");
		}
		
		return forward;
	}

}
