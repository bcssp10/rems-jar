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
@NamedNativeQuery(name = "GeneralVoucher.findTrialBalance", query = "SELECT (select name from party where cash_paid_to=party_id) Account,null as Debit,SUM(amount) Credit FROM `general_voucher` WHERE cash_paid_by=?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null)"
		+ " GROUP BY cash_paid_to" + " union all"
		+ " SELECT (select name from party where cash_paid_by=party_id),SUM(amount),null FROM `general_voucher` WHERE cash_paid_to=?1 and (date>=?2 or ?2 is null) and (date<=?3 or ?3 is null)"
		+ " GROUP BY cash_paid_by" + " order by Account,Debit desc")
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
