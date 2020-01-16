package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS ")
public class UserRegistrationDetailsEntity {
	@Column(name="User_Id")
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer userId;
	@Column(name = "User_Name")
	private String uName;
	@Column(name = "User_Email")
	private String uEmail;
	@Column(name = "User_PhoneNo")
	private Long phNo;
	@Column(name = "User_Status")
	private String status;
	@Column(name="User_Pwd")
	private String uPwd;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public Long getPhNo() {
		return phNo;
	}
	public void setPhNo(Long phNo) {
		this.phNo = phNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	@Override
	public String toString() {
		return "UserRegistrationDetailsEntity [userId=" + userId + ", uName=" + uName + ", uEmail=" + uEmail + ", phNo="
				+ phNo + ", status=" + status + ", uPwd=" + uPwd + "]";
	}
	
}
