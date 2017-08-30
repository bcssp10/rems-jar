package com.rems.voucher.cash;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashVoucherService {

	@Autowired
	private CashVoucherRepository cashVoucherRepository;

	public List<CashVoucher> getAllCashVouchers() {
		List<CashVoucher> cashVouchers = new ArrayList<>();
		cashVoucherRepository.findAll().forEach(cashVouchers::add);
		return cashVouchers;
	}

	public CashVoucher getCashVoucherById(int id) {
		return cashVoucherRepository.findOne(id);
	}

	public void updateCashVoucherById(int id, CashVoucher cashVoucher) {
		 cashVoucherRepository.save(cashVoucher);
	}
	
	public void save(CashVoucher cashVoucher) {
		 cashVoucherRepository.save(cashVoucher);
	}

	public void deleteCashVoucher(int id) {
		cashVoucherRepository.delete(id);
	}
	public List<CashVoucher> findAllCashVouchersByPartyId(int partyId) {
		List<CashVoucher> cashVouchers = new ArrayList<>();
		cashVoucherRepository.findAllCashVouchersByParty(partyId).forEach(cashVouchers::add);
		return cashVouchers;
	}
	
	public double calculateTotalAmount(List<CashVoucher> cashVouchers) {
		return cashVouchers.stream().mapToDouble(r -> r.getAmount()).sum();
	}

}
