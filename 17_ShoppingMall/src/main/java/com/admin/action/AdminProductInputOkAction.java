package com.admin.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 등록 폼 페이지에서 넘어온 데이터들을
		// DB에 저장하는 비지니스 로직
		
		// 첨부파일이 저장될 위치(경로) 설정
		String saveFolder = "C:\\NCS\\git\\kh_JSP\\17_ShoppingMall\\src\\main\\webapp\\image";
		int filesize = (1024 * 1024) * 50;	
		// 이미지 파일을 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(request, saveFolder, filesize, "UTF-8", new DefaultFileRenamePolicy());
		String []arr = multi.getParameterValues("all");
		File file = multi.getFile("image");
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		// 상품명, 코드, 제조사, 이미지, 수량, 가격, 사양, 설명, 포인트
		ProductDTO dto = new ProductDTO();
		dto.setName(arr[0]);
		dto.setCode(arr[1]);
		dto.setCompany(arr[2]);
		dto.setQty(Integer.parseInt(arr[3]));
		dto.setPrice(Integer.parseInt(arr[4]));
		dto.setSpec(arr[5]);
		dto.setCont(arr[6]);
		dto.setPoint(Integer.parseInt(arr[7]));
		
		
		File upload_file = multi.getFile("image"); 
		String fileName = upload_file.getName(); // 파일명 찾기
		String reFile = arr[0] + "[" + fileName + "]";
		
		upload_file.renameTo(new File(saveFolder + "/" + reFile));
		dto.setImage(reFile);
		
		
//		if(upload_file != null) {
//			String fileName = upload_file.getName();
//			String reFileName = arr[0] + "[" + arr[1] + "]" + "사진";
//			upload_file.renameTo(new File(arr[0] + "/" + arr[1]));
//			
//		} else {
//			dto.setImage(null);
//		}
		
		ProductDAO dao = ProductDAO.getInstnace();
		int check = dao.insertProduct(dto);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do");
		} else {
			out.println("<script>"
					+ "alert('등록 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
		
		return forward;
	}

}
