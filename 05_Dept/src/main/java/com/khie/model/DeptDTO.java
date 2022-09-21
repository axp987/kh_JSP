package com.khie.model;

// DB상의 DEPT 테이블의 컬럼명과 

public class DeptDTO {
	private int deptno;
	private String dname;
	private String LOC;
	
	public DeptDTO() {
		
	}
	
	//getter setter
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLOC() {
		return LOC;
	}
	public void setLOC(String lOC) {
		LOC = lOC;
	}
	
	
}
