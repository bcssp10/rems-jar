package com.rems.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "Account.calculateProfitLoss", query = "select id,date,details,Dr,Cr,(@balance \\:= @balance + (Dr - Cr)) as Balance from \r\n"
		+ "(SELECT @balance \\:= 0) AS dummy cross join\r\n" + "(\r\n"
		+ "select concat('JV-',LPAD(general_voucher_id, 4, '0')) id,date,(case when cash_paid_to=1 then amount else '' end) as Dr,(case when cash_paid_by=1 then amount else '' end) as Cr,details from general_voucher where account_id = ?1 and ((cash_paid_to = 1 and cash_paid_by != 1) or (cash_paid_by = 1 and cash_paid_to !=1) ) and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) \r\n"
		+ "union all\r\n"
		+ "select concat('CR-',LPAD(receipt_id,4,'0')),date,amount Dr,'' Cr,for_payment_of from receipt where account_id = ?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) \r\n"
		+ "union all\r\n"
		+ "select concat('CP-',LPAD(cash_voucher_id,4,'0')),date,'' Dr,amount Cr,for_payment_of from cash_voucher where account_id = ?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) \r\n"
		+ ") GL")
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
