package com.rems.party;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.rems.receipt.Receipt;
import com.rems.voucher.cash.CashVoucher;


@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_id")
	private int partyId = -1;
	
	private String name;
	
	private String phone;

	private String address;
	
	@OneToMany(mappedBy="party",fetch = FetchType.LAZY)
	private List<Receipt> receipts;
	
	@OneToMany(mappedBy="party",fetch = FetchType.LAZY)
	private List<CashVoucher> cashVouchers;
	
	
	
	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}
	
	public List<CashVoucher> getCashVouchers() {
		return cashVouchers;
	}

	public void setCashVouchers(List<CashVoucher> cashVouchers) {
		this.cashVouchers = cashVouchers;
	}

	public boolean isNew() {
		return this.partyId == -1;
	}
	
	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}
	
}
