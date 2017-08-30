package com.rems.receipt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;

	public List<Receipt> getAllReceipts() {
		List<Receipt> receipts = new ArrayList<>();
		receiptRepository.findAll().forEach(receipts::add);
		return receipts;
	}

	public Receipt getReceiptById(int id) {
		return receiptRepository.findOne(id);
	}

	public void updateReceiptById(int id, Receipt receipt) {
		 receiptRepository.save(receipt);
	}
	
	public void save(Receipt receipt) {
		 receiptRepository.save(receipt);
	}

	public void deleteReceipt(int id) {
		receiptRepository.delete(id);
	}
	
	public List<Receipt> findAllReceiptsByPartyId(int partyId) {
		List<Receipt> receipts = new ArrayList<>();
		receiptRepository.findAllReceiptsByParty(partyId).forEach(receipts::add);
		return receipts;
	}
	
	public double calculateTotalAmount(List<Receipt> receipts) {
		return receipts.stream().mapToDouble(r -> r.getAmount()).sum();
	}
}
