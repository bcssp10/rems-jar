package com.rems.voucher.general;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.format.annotation.DateTimeFormat;

import com.rems.account.Account;
import com.rems.party.Party;

@Entity
@NamedQueries({
		@NamedQuery(name = "GeneralVoucher.findAll", query = "select g from  GeneralVoucher g order by g.generalVoucherId desc"),
		@NamedQuery(name = "GeneralVoucher.findAllGeneralVouchersByCashPaidTo", query = "from GeneralVoucher g where g.cashPaidTo.partyId=:cashPaidTo order by g.generalVoucherId desc"),
		@NamedQuery(name = "GeneralVoucher.findAllGeneralVouchersByCashPaidBy", query = "from GeneralVoucher g where g.cashPaidBy.partyId=:cashPaidBy order by g.generalVoucherId desc"),
		@NamedQuery(name = "GeneralVoucher.findGeneralVouchersForLedger", query = "from GeneralVoucher g where g.cashPaidBy.partyId in (?1,?2) and g.cashPaidTo.partyId in (?1,?2) and (g.date>=?3 or ?3 is null) and (g.date<=?4 or ?4 is null)  order by g.generalVoucherId"),
		/*
		 * @NamedQuery(name = "GeneralVoucher.findtotalDebitAccount",
		 * query="SELECT g.cashPaidBy,SUM(g.amount),g.cashPaidTo FROM GeneralVoucher g WHERE g.cashPaidTo.partyId=?1 GROUP BY g.cashPaidBy.partyId ORDER BY g.cashPaidBy.partyId"
		 * ),
		 * 
		 * @NamedQuery(name = "GeneralVoucher.findtotalCreditAccount",
		 * query="SELECT g.cashPaidBy,SUM(g.amount),g.cashPaidTo FROM GeneralVoucher g WHERE g.cashPaidBy.partyId=?1 GROUP BY g.cashPaidTo.partyId ORDER BY g.cashPaidTo.partyId"
		 * )
		 */
})
@NamedNativeQueries({
		@NamedNativeQuery(name = "GeneralVoucher.findTrialBalance",
				query = /*"select concat('P-',LPAD(party_Id, 2, '0')) id,Account, null as debit,sum(credit) as credit from ( \r\n" + 
						"select cash_paid_to as party_Id,(select name from party where cash_paid_to=party_id) Account,SUM(amount) credit FROM `general_voucher` where cash_paid_by=1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)  \r\n" + 
						"group by cash_paid_to\r\n" + 
						"union all\r\n" + 
						"select party_Id, (select name from party p where p.party_id=c.party_id) Account, sum(amount) from cash_voucher c \r\n" + 
						"where party_Id !=1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"group by party_Id\r\n" + 
						") as t1 group by party_Id\r\n" + 
						"union all\r\n" + 
						"select concat('P-',LPAD(party_Id, 2, '0')) id,Account,sum(debit),null from (\r\n" + 
						"SELECT cash_paid_by as party_Id,(select name from party where cash_paid_by=party_id) Account,SUM(amount) debit FROM `general_voucher` where cash_paid_to=1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"GROUP BY cash_paid_by\r\n" + 
						"union all\r\n" + 
						"select party_Id,(select name from party p where p.party_id=r.party_id) Account,sum(amount) from receipt r \r\n" + 
						"where party_Id !=1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"group by party_Id\r\n" + 
						") as t2 group by party_id\r\n" + 
						"union all\r\n" +*/ 
						"select concat('AC-',LPAD(account_id, 4, '0')) id,Account,null as debit, sum(credit) as credit from (\r\n" + 
						"SELECT account_id,(select name from account a where a.account_id=g.account_id) Account,SUM(amount) credit FROM `general_voucher` g where cash_paid_by=1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"GROUP BY account_id\r\n" + 
						"union all\r\n" + 
						"select account_id,(select name from account a where a.account_id=c.account_id) Account, sum(amount) from cash_voucher c where party_id != 1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"group by account_id\r\n" + 
						")as t3 group by account_id\r\n" + 
						"union all\r\n" + 
						"select concat('AC-',LPAD(account_id, 4, '0')),Account,sum(debit),null from (\r\n" + 
						"SELECT account_id,(select name from account a where a.account_id=g.account_id) Account,SUM(amount) as debit, null as Credit FROM `general_voucher` g where cash_paid_to=1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"group by account_id\r\n" + 
						"union all\r\n" + 
						"select account_id,(select name from account a where a.account_id=r.account_id) Account,sum(amount),null from receipt r where party_id != 1 and (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n" + 
						"group by account_id) as t4\r\n" + 
						"group by account_id\r\n" + 
						"order by id desc,Account,debit desc"
				),
		@NamedNativeQuery(name = "GeneralVoucher.calculateLedger", query = "select id,date,details,Dr,Cr,(@balance \\:= @balance + (Dr - Cr)) as Balance from \r\n"
				+ "(SELECT @balance \\:= 0) AS dummy cross join\r\n" + "(\r\n"
				+ "select concat('JV-',LPAD(general_voucher_id, 4, '0')) id,date,(case when cash_paid_to=1 then amount else '' end) as Dr,(case when cash_paid_by=1 then amount else '' end) as Cr,details from general_voucher where cash_paid_to in (1,?1) and cash_paid_by in (1,?1) and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) \r\n"
				+ "union all\r\n"
				+ "select concat('CR-',LPAD(receipt_id,4,'0')),date,amount Dr,'' Cr,for_payment_of from receipt where party_id = ?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) \r\n"
				+ "union all\r\n"
				+ "select concat('CP-',LPAD(cash_voucher_id,4,'0')),date,'' Dr,amount Cr,for_payment_of from cash_voucher where party_id = ?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) \r\n"
				+ ") GL")
})
public class GeneralVoucher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "general_voucher_id")
	private int generalVoucherId = -1;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "cash_paid_to", nullable = true)
	private Party cashPaidTo;
	
	@ManyToOne
	@JoinColumn(name="account_id", foreignKey = @ForeignKey(name = "FK_GENERAL_VOUCHER_ACCOUNT"), nullable=true)
	private Account account;

	@Column(name = "amount")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "cash_paid_by", nullable = true)
	private Party cashPaidBy;

	@Column(name = "details")
	private String details;

	public int getGeneralVoucherId() {
		return generalVoucherId;
	}

	public void setGeneralVoucherId(int generalVoucherId) {
		this.generalVoucherId = generalVoucherId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Party getCashPaidTo() {
		return cashPaidTo;
	}

	public void setCashPaidTo(Party cashPaidTo) {
		this.cashPaidTo = cashPaidTo;
	}

	public Party getCashPaidBy() {
		return cashPaidBy;
	}

	public void setCashPaidBy(Party cashPaidBy) {
		this.cashPaidBy = cashPaidBy;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isNew() {
		return this.generalVoucherId == -1;
	}

	@Override
	public String toString() {
		return "GeneralVoucher [generalVoucherId=" + generalVoucherId + ", date=" + date + ", cashPaidTo=" + cashPaidTo
				+ ", account=" + account + ", amount=" + amount + ", cashPaidBy=" + cashPaidBy + ", details=" + details
				+ "]";
	}
	
}
