package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UploadDTO dto = new UploadDTO();
		
		// 파일 업로드 시에는 설정 내용이 있음
		// 1. 파일 저장 경로 지정
		String saveFolder = "C:\\NCS\\git\\kh_JSP\\15_Board_FileUpload\\src\\main\\webapp\\upload";
		// 2. 첨부 파일 크기 지정
		int fileSize = 10 * (1024 * 1024); // kb >> mb
		// 3. MultipartRequest 객체
		//		==> 파일 업로드를 진행하기 위한 객체 생성
		// 일반적인 request 객체, 첨부파일 저장 경로, 업로드 파일 크기, 문자 인코딩 방식, 파일 이름 중복 방지(a, a(1)) 생성자
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		String ti = multi.getParameter("title");
		String wr = multi.getParameter("write");
		String co = multi.getParameter("cont");
		String pw = multi.getParameter("pwd");
		
		// 자료실 폼페이지에서 넘어온 첨부 파일 받기
		// type="file"  로 되어 있으면 getFile() 메서드를 활용
		
		File upload_file = multi.getFile("upload_file");
		
		
		if(upload_file != null) { // 첨부 파일이 존재하는 경우
			// 첨부파일의 이름을 알아야 함
			// getName() 메서드를 이용하여 이름을 알 수 있음
			String fileName = upload_file.getName();
			System.out.println("파일이름: " + fileName);
			// 날짜 객체 생성
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// ....../upload/2022-10-11(폴더생성)/
			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;
			
			// 날짜 폴더 생성
			File path1 = new File(homedir); // 날짜 폴더 생성
			// exists 존재 유무 파악 (true/false)
			if(!path1.exists()) { // 폴더가 존재하지 않는 경우
				path1.mkdir(); // 폴더 생성 메서드
			}
			
			// 파일 생성 ==> 예) 작성자_파일명
			// ..../upload/2022-10-11/홍길동_파일명
			String reFileName = wr + "_" + fileName;
			//  파일 이름을 변경해서 해당 폴더에 파일 저장
			upload_file.renameTo(new File(homedir + "/" + reFileName));
			
			// 실제로 DB에 저장되는 파일 이름
			// "/2022-10-11/작성자_파일명" 으로 저장 예정
			String fileDBName =  "/" + year + "-" + month + "-" + day + "/" + reFileName;
			
			
			// DB에 파일저장
			//dto.setFile(fileDBName);
			dto.setFile(fileDBName);
		} else {
			dto.setFile(null);
		}
		
		String []arr = multi.getParameterValues("all");
		dto.setTitle(ti);
		dto.setWriter(wr);
		dto.setCont(co);
		dto.setPwd(pw);
		
		UploadDAO dao = UploadDAO.getInstance();
		int check = dao.insertUpload(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('등록 완료');"
					+ "location.href='upload_list.do';"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('등록 실패');"
					+ "history.bakc();"
					+ "</script>");
		}
		
		return null;
	}

}
