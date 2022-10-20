package com.reply1.model;

public class BoardDTO {
	private int bno;
	private String writer;
	private String title;
	private String cont;
	private String pwd;
	private String regdate;
	private String regupdate;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getRegupdate() {
		return regupdate;
	}
	public void setRegupdate(String regupdate) {
		this.regupdate = regupdate;
	}
	
	
}
