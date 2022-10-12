package com.products.model;

// products 테이블의 컬럼명과 동일하게 멤버변수 구성
public class ProductDTO {
	private int pnum;
	private String category_fk;
	private String productName;
	private String ep_code_fk;
	private int input_price;
	private int output_price;
	private int trans_cost;
	private int mileage;
	private String company;
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getCategory_fk() {
		return category_fk;
	}
	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getEp_code_fk() {
		return ep_code_fk;
	}
	public void setEp_code_fk(String ep_code_fk) {
		this.ep_code_fk = ep_code_fk;
	}
	public int getInput_price() {
		return input_price;
	}
	public void setInput_price(int input_price) {
		this.input_price = input_price;
	}
	public int getOutput_price() {
		return output_price;
	}
	public void setOutput_price(int output_price) {
		this.output_price = output_price;
	}
	public int getTrans_cost() {
		return trans_cost;
	}
	public void setTrans_cost(int trans_cost) {
		this.trans_cost = trans_cost;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
