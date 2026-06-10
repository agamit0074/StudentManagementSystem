package com.sms.entity;

import java.sql.Date;

public class StudentEntity {
	private int id;
	private String name;
	private long mobile;
	private Date dob;
	private double per10;
	private java.util.Date created_at;
	private java.util.Date updated_at;
	
	
	
	public StudentEntity(String name, long mobile, Date dob, double per10) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.dob = dob;
		this.per10 = per10;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public double getPer10() {
		return per10;
	}
	public void setPer10(double per10) {
		this.per10 = per10;
	}
	public java.util.Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}
	public java.util.Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(java.util.Date updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", name=" + name + ", mobile=" + mobile + ", dob=" + dob + ", per10=" + per10
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
}
