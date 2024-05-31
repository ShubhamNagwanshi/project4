package com.rays.pro4.Bean;

import java.util.Date;

public class OrderBean extends BaseBean {

	private String name;
//	private Date dob;
	private String dob;
	
	private String orderType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return orderType;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return orderType;
	}
}
