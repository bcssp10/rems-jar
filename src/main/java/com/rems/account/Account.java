package com.rems.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id",columnDefinition="TINYINT(3) UNSIGNED")
	private int accountId = -1;
	
	private String name;
	
	private String detail;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isNew() {
		return this.accountId == -1;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", name=" + name + ", detail=" + detail + "]";
	}
	
}
