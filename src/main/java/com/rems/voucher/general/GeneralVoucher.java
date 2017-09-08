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
		
		// find Trail Balance
		@NamedNativeQuery(name = "GeneralVoucher.findTrialBalance", query = "select id,name,(case when coalesce(sum(debit),0) - coalesce(sum(credit),0) >=0 then coalesce(sum(debit),0) - coalesce(sum(credit),0) else null end) debit,(case when coalesce(sum(debit),0) - coalesce(sum(credit),0) <0 then (coalesce(sum(debit),0) - coalesce(sum(credit),0))*-1 else null end) credit from \r\n" + 
				"(\r\n" + 
				"select party_id,concat('P-',LPAD(party_id, 2, '0')) id,(select name from party p where p.party_id = c.party_id) name, sum(amount) as debit, null as credit from cash_voucher c where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)\r\n" + 
				"group by party_id\r\n" + 
				"union all\r\n" + 
				"select party_id,concat('P-',LPAD(party_id, 2, '0')), (select name from party p where p.party_id = r.party_id), null ,sum(amount) from receipt r where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)\r\n" + 
				"group by party_id\r\n" + 
				"union all \r\n" + 
				"select cash_paid_to,concat('P-',LPAD(cash_paid_to, 2, '0')), (select name from party p where p.party_id = g.cash_paid_to),sum(amount), null from general_voucher g where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)\r\n" + 
				"group by cash_paid_to\r\n" + 
				"union all\r\n" + 
				"select cash_paid_by,concat('P-',LPAD(cash_paid_by, 2, '0')) id,(select name from party p where p.party_id = g.cash_paid_by),null,sum(amount) from general_voucher g where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)\r\n" + 
				"group by cash_paid_by\r\n" +
				"union all\r\n" + 
				"select party_id,null,null,debitOB,creditOB from party where party_id != 1"+
				") t1 where t1.id is not null \r\n" + 
				"group by party_id"),
		
		@NamedNativeQuery(name = "GeneralVoucher.findTrialBalanceforCash", query = "select (select name from party where party_id = 1) name,coalesce((select sum(amount) from cash_voucher where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)) - (select sum(amount) from receipt where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)),0) amount from dual"),
		
		// find General Ledger 
		@NamedNativeQuery(name = "GeneralVoucher.findGLforCash", query = "select concat('CR-',LPAD(receipt_id,4,'0')) as id,date,for_payment_of,amount as debit,null as credit from receipt where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null) \r\n"
				+ "union all\r\n"
				+ "select concat('CP-',LPAD(cash_voucher_id,4,'0')),date,for_payment_of,null,amount as credit from cash_voucher where (date>=?1 or ?1 is null) and (date<=?2 or ?2 is null)"),
		
		@NamedNativeQuery(name = "GeneralVoucher.findGL", query = 
				  "select null as id,null as date,'Opening Balance' as detail,(case when debitOB = 0 then null else debitOB end)  as debit,(case when creditOB = 0 then null else creditOB end) as credit from party where party_id = ?1\r\n" 
				+ "union all\r\n" 
				+ "select concat('CR-',LPAD(receipt_id,4,'0')) ,date,for_payment_of,null,amount from receipt where party_id=?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null) "
				+ "union all\r\n"
				+ "select concat('CP-',LPAD(cash_voucher_id,4,'0')),date,for_payment_of,amount,null from cash_voucher where party_id=?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null)\r\n"
				+ "union all\r\n"
				+ "select concat('JV-',LPAD(general_voucher_id, 4, '0')),date,details,(case when cash_paid_to = ?1 then amount else null end) as debit, (case when cash_paid_by = ?1 then amount else null end) as credit from general_voucher\r\n"
				+ "where (cash_paid_to = ?1 OR cash_paid_by = ?1) and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null)"),
		
		// find Profit Loss
		@NamedNativeQuery(name = "GeneralVoucher.findProfitLoss", query = 
				"select concat('JV-',LPAD(general_voucher_id, 4, '0')) id,date,details,null as debit,amount as credit from general_voucher where cash_paid_by = ?1 and (date>=?3 or ?3 is null) and (date<=?4 or ?4 is null)\r\n" + 
				"union all\r\n" + 
				"select concat('JV-',LPAD(general_voucher_id, 4, '0')),date,details,amount,null from general_voucher where cash_paid_to = ?2 and (date>=?3 or ?3 is null) and (date<=?4 or ?4 is null)")
})

public class GeneralVoucher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "general_voucher_id",columnDefinition="INT(10) UNSIGNED")
	private int generalVoucherId = -1;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "cash_paid_to",foreignKey = @ForeignKey(name = "FK_GENERAL_VOUCHER_PAID_TO"),nullable = true)
	private Party cashPaidTo;

	@Column(name = "amount")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "cash_paid_by",foreignKey = @ForeignKey(name = "FK_GENERAL_VOUCHER_PAID_BY"),nullable = true)
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

	public boolean isNew() {
		return this.generalVoucherId == -1;
	}
}
