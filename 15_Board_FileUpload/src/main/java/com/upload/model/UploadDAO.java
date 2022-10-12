package com.upload.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.upload.model.UploadDAO;

public class UploadDAO {
	private static UploadDAO instance;

	private UploadDAO() {

	}

	public static UploadDAO getInstance() {
		if(instance == null) {
			instance = new UploadDAO();	
		}
		return instance;
	}


	public Connection con = null;
	public ResultSet rs = null;
	public PreparedStatement pstmt = null;

	String sql = null;

	// DB 연동 작업을 진행하는 메소드
	public void openConn() {
		try {
			// 1단계: JNDI 서버 객체 생성
			Context ctx = new InitialContext();

			// 2단계: lookup() 메소드를 이용하여 매칭되는 커넥션을 찾는다
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");

			// 3단계: DataSource 객체를 이용하여 커넥션을 하나 가져옴
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if(rs != null) {
				rs.close();
			}

			if(pstmt != null) {
				pstmt.close();
			}

			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // closeConn

	public void closeConn(PreparedStatement pstmt, Connection con) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}

			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // closeConn
	
	public List<UploadDTO> getUploadList() {
		List<UploadDTO> list = new ArrayList<UploadDTO>();
		openConn();
		
		try {
			sql = "select * from upload order by upload_no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UploadDTO dto = new UploadDTO();
				dto.setNo(rs.getInt("upload_no"));
				dto.setWriter(rs.getString("upload_writer"));
				dto.setTitle(rs.getString("upload_title"));
				dto.setCont(rs.getString("upload_cont"));
				dto.setPwd(rs.getString("upload_pwd"));
				dto.setFile(rs.getString("upload_file"));
				dto.setHit(rs.getInt("upload_hit"));
				dto.setDate(rs.getString("upload_date"));
				dto.setUpdate(rs.getString("upload_update"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
		
	} // getUploadList()
	
	// upload 테이블에 게시글을 추가하는 메서드
	public int insertUpload(UploadDTO dto) {
		int result = 0, count = 0;
		openConn();
		
		try {
			sql = "select count(upload_no) from upload";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into upload values (?, ?, ?, ?, ?, ?, default, sysdate, '')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getCont());
			pstmt.setString(5, dto.getPwd());
			pstmt.setString(6, dto.getFile());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertUpload
	
	public void uploadHit(int no) {
		openConn();
		
		try {
			sql = "update upload set upload_hit = upload_hit + 1 where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	} // uploadHit
	
	public UploadDTO uploadContent(int no) {
		UploadDTO dto = null;
		
		openConn();
		try {
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new UploadDTO();
				dto.setNo(rs.getInt("upload_no"));
				dto.setWriter(rs.getString("upload_writer"));
				dto.setTitle(rs.getString("upload_title"));
				dto.setCont(rs.getString("upload_cont"));
				dto.setPwd(rs.getString("upload_pwd"));
				dto.setFile(rs.getString("upload_file"));
				dto.setHit(rs.getInt("upload_hit"));
				dto.setDate(rs.getString("upload_date"));
				dto.setUpdate(rs.getString("upload_update"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // uploadContent()
	
	public int modifyUpload(UploadDTO dto) {
		int result = 0;
		openConn();
		
		try {
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString("upload_pwd"))) {
					if(dto.getFile() == null) {
						sql = "update upload set upload_title =?, upload_cont = ?, upload_update = sysdate where upload_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getTitle());
						pstmt.setString(2, dto.getCont());
						pstmt.setInt(3, dto.getNo());
			
					} else { // 수정폼 페이지에서 첨부파일을 선택한 경우
						sql = "update upload set upload_title =?, upload_cont = ?, upload_file = ?, upload_update = sysdate where upload_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getTitle());
						pstmt.setString(2, dto.getCont());
						pstmt.setString(3, dto.getFile());
						pstmt.setInt(4, dto.getNo());						
					}
					
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // modifyUpload
	
	// upload 테이블의 게시글을 번호에 해당하는 게시글을 삭제하는 메서드
	public int deleteUpload(int no) {
		int result = 0;
		openConn();
		
		try {
			sql = "delete from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				sql ="update upload set upload_no = upload_no - 1 where upload_no > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
				// result = pstmt.executeUpdate(); //  0이 반환?
			} else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // deleteUpload
}
