package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EmpDAO {
	private static EmpDAO instance;


	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	String sql = null;


	private EmpDAO() {

	}

	public static EmpDAO getInstance() {
		if(instance == null) {
			instance = new EmpDAO();
		}
		return instance;
	}

	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
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

	public List<EmpDTO> getEmpList() {
		List<EmpDTO> list = new ArrayList<EmpDTO>();

		openConn();
		try {
			sql = "select * from emp order by empno desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setDate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));

				list.add(dto);
			}


		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}

	// EMP 테이블에서 담당업무
	public List<String> getJobList() {
		List<String> jobList = new ArrayList<String>();

		openConn();
		try {
			sql = "select distinct(job) from emp order by job";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String job = rs.getString("job");

				jobList.add(job);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return jobList;
	} //getJobList

	// EMP 테이블에서 담당업무가 "MANAGER" 인 사원을 조회하는 메소드
	public List<EmpDTO> getMgrList() {
		List<EmpDTO> mgrList = new ArrayList<EmpDTO>();

		openConn();

		try {
			sql = "select empno, ename from emp where job = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "MANAGER");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();

				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));

				mgrList.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return mgrList;
	} // getMgrList

	// DEPT 테이블의 전체 리스트를 조회하는 메소드
	public List<DeptDTO> getDeptList() {
		List<DeptDTO> deptList = new ArrayList<DeptDTO>();

		openConn();

		try {
			sql = "select * from dept order by deptno";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();

				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));

				deptList.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return deptList;
	}
	
	public int setInsertEmp(EmpDTO dto) {
		
		int result = 0;
		openConn();
		try {
			sql = "insert into emp values (?, ?, ?, ?, sysdate, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			pstmt.setInt(5, dto.getSal());
			pstmt.setInt(6, dto.getComm());
			pstmt.setInt(7, dto.getDeptno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		return result;
	} //setInsertEmp
	
	public EmpDTO getContentList(int num) {
		EmpDTO dto = new EmpDTO();
		openConn();
		try {
			sql = "select * from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setDate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return dto;
	} //getContentList
	
	public List<EmpDTO> getManager() { // 이게 관리자 찾는거임 위에 이상한거 보지마!
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		
		try {
			sql = "select distinct(e2.empno), e2.ename from emp e1 join emp e2 on e1.mgr = e2.empno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} //getManager()
	
	
	public int setUpdateList(String arr, int no) {
		StringTokenizer st = new StringTokenizer(arr, " ");
		int result = 0;
		
		openConn();
		try {
			sql = "update emp set ename = ?, job = ?, mgr = ?, sal = ?, comm = ?, deptno = ? where empno = ?";
			pstmt = con.prepareStatement(sql);
			while(st.hasMoreTokens()) {
				pstmt.setString(1, st.nextToken());
				pstmt.setString(2, st.nextToken());
				pstmt.setInt(3, Integer.parseInt(st.nextToken()));
				pstmt.setInt(4, Integer.parseInt(st.nextToken()));
				pstmt.setInt(5, Integer.parseInt(st.nextToken()));
				pstmt.setInt(6, Integer.parseInt(st.nextToken()));
			}
			pstmt.setInt(7, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} //setUpdateList()
	
	public int setDelete(int no) {
		openConn();
		int result = 0;
		try {
			sql = "delete from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		return result;
	}
}
