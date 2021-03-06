package com.rems.receipt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ForeignKey;

import org.springframework.format.annotation.DateTimeFormat;

import com.rems.enumeration.PaymentType;
import com.rems.party.Party;


@Entity
@NamedQueries({
@NamedQuery(name = "Receipt.findAll", query="from Receipt r order by r.receiptId desc"),
@NamedQuery(name = "Receipt.findAllReceiptsByParty", query="from Receipt r where r.party.partyId=:partyId order by r.receiptId desc")
})
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receipt_id",columnDefinition="INT(10) UNSIGNED")
	private int receiptId = -1;

	@ManyToOne
	@JoinColumn(name="party_id",foreignKey = @ForeignKey(name = "FK_RECEIPT_PARTY"),nullable=true)
	private Party party;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="date")
	private Date date;
	
	@Column(name="amount")
	private Double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type")
	private PaymentType paymentType;

	@Column(name="for_payment_of")
	private String forPaymentOf;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_branch")
	private String bankBranch;
	
	@Column(name = "cheque_no")
	private String chequeNo;

	@Column(name = "cash_received_by")
	private String cashReceivedBy;

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getforPaymentOf() {
		return forPaymentOf;
	}

	public void setforPaymentOf(String forPaymentOf) {
		this.forPaymentOf = forPaymentOf;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	
	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getCashReceivedBy() {
		return cashReceivedBy;
	}

	public void setCashReceivedBy(String cashReceivedBy) {
		this.cashReceivedBy = cashReceivedBy;
	}

	public boolean isNew() {
		return this.receiptId == -1;
	}
	
	@Override
	public String toString() {
		return "Receipt [receiptId=" + receiptId + ", date=" + date + ", cashReceivedFrom=" + party
				+ ", amount=" + amount + ", paymentType=" + paymentType + ", forPaymentOf=" + forPaymentOf + ", bankName=" + bankName
				+ ", bankBranch=" + bankBranch + ", cashReceivedBy=" + cashReceivedBy + "]";
	}
}
