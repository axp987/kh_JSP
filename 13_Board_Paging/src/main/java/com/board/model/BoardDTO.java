package com.board.model;

// board 테이블의 컬럼과 동일하게 멤버벼수 구성
public class BoardDTO {
	private int no;
	private String writer;
	private String title;
	private String cont;
	private String pwd;
	private int hit;
	private String date;
	private String update;
	
	public BoardDTO() {
	
	}
	
	public BoardDTO(String writer, String title, String cont, String pwd) {
		this.writer = writer;
		this.title = title;
		this.cont = cont;
		this.pwd = pwd;
	} // insert 생성자
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	
	private Object []a = { no, writer, title, cont, pwd };

	public Object[] getA() {
		return a;
	}
	public void setA(Object[] a) {
		this.a = a;
	}
}
