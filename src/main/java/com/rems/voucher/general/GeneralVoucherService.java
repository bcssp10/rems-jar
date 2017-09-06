package com.rems.voucher.general;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rems.party.Party;

@Service
public class GeneralVoucherService {

	@Autowired
	private GeneralVoucherRepository generalVoucherRepository;

	public List<GeneralVoucher> getAllGeneralVouchers() {
		List<GeneralVoucher> generalVouchers = new ArrayList<>();
		generalVoucherRepository.findAll().forEach(generalVouchers::add);
		return generalVouchers;
	}

	public GeneralVoucher getGeneralVoucherById(int id) {
		return generalVoucherRepository.findOne(id);
	}

	public void updateGeneralVoucherById(int id, GeneralVoucher generalVoucher) {
		 generalVoucherRepository.save(generalVoucher);
	}
	
	public void save(GeneralVoucher generalVoucher) {
		 generalVoucherRepository.save(generalVoucher);
	}

	public void deleteGeneralVoucher(int id) {
		generalVoucherRepository.delete(id);
	}
	public List<GeneralVoucher> findAllGeneralVouchersByPaidToParty(int partyId) {
		List<GeneralVoucher> generalVouchers = new ArrayList<>();
		generalVoucherRepository.findAllGeneralVouchersByCashPaidTo(partyId).forEach(generalVouchers::add);
		return generalVouchers;
	}
	
	public List<GeneralVoucher> findAllGeneralVouchersByPaidByParty(int partyId) {
		List<GeneralVoucher> generalVouchers = new ArrayList<>();
		generalVoucherRepository.findAllGeneralVouchersByCashPaidBy(partyId).forEach(generalVouchers::add);
		return generalVouchers;
	}
	
	public double calculateTotalAmount(List<GeneralVoucher> generalVouchers) {
		return generalVouchers.stream().mapToDouble(r -> r.getAmount()).sum();
	}
/*	
	public List<GeneralVoucher> findGeneralVouchersForLedger(int mainPartyId, int referencePartyId, Date from, Date to) {
		List<GeneralVoucher> generalVouchers = new ArrayList<>();
		generalVoucherRepository.findGeneralVouchersForLedger(mainPartyId, referencePartyId, from, to).forEach(generalVouchers::add);
		return generalVouchers;
	}
*/	

	public List<Object[]> calculateLedger(int partyId,Date from, Date to) {
		List<Object[]> objects = new ArrayList<>();
		generalVoucherRepository.calculateLedger(partyId, from, to).forEach(objects::add);
		return objects;
	}
	
	public List<Object[]> calculateTrialBalance(int mainPartyId,Date from, Date to) {
		List<Object[]> objects = new ArrayList<>();
		generalVoucherRepository.findTrialBalance(mainPartyId,from,to).forEach(objects::add);
		return objects;
	}

	/*
	 * public void calculateAccountLedger(int mainPartyId, int referencePartyId,
	 * Date from, Date to) { List<GeneralVoucher> generalVouchers = new
	 * ArrayList<>();
	 * 
	 * generalVoucherRepository.findGeneralVouchersForLedger(mainPartyId,
	 * referencePartyId, from, to).forEach(generalVouchers::add);
	 * 
	 * String mainPartyName,referencePartyName;
	 * 
	 * if(generalVouchers.isEmpty()) return;
	 * 
	 * if(generalVouchers.get(0).getCashPaidTo().getPartyId() == mainPartyId) {
	 * mainPartyName = generalVouchers.get(0).getCashPaidTo().getName();
	 * referencePartyName = generalVouchers.get(0).getCashPaidBy().getName(); }else
	 * { referencePartyName = generalVouchers.get(0).getCashPaidTo().getName();
	 * mainPartyName = generalVouchers.get(0).getCashPaidBy().getName(); }
	 * 
	 * // ledger calculations // to be removed // these calculations must be taken
	 * into front end in future builds System.out.println("Main Party\t: "
	 * +mainPartyName+"\nReference Party : "+referencePartyName);
	 * 
	 * double total=0.00, totalDebit=0.00, totalCredit=0.00;
	 * System.out.println("\nID \t\t Dr \t\t\t Cr \t\t\t Balance");
	 * 
	 * for (GeneralVoucher voucher : generalVouchers) {
	 * 
	 * System.out.print(voucher.getGeneralVoucherId());
	 * if(voucher.getCashPaidTo().getPartyId() == mainPartyId) {
	 * total+=voucher.getAmount(); totalDebit+=voucher.getAmount();
	 * System.out.print(" \t\t "+voucher.getAmount()+" \t\t ------ \t\t "); }else {
	 * total-=voucher.getAmount(); totalCredit+=voucher.getAmount();
	 * System.out.print(" \t\t ------ \t\t "+voucher.getAmount()+" \t\t "); }
	 * System.out.println((total < 0 ? total*-1+"Cr" :total+"Dr"));
	 * 
	 * } System.out.println("Total \t\t "+totalDebit+" \t\t "+totalCredit+" \t\t "+
	 * (total < 0 ? total*-1+"Cr" :total+"Dr"));
	 * System.out.println("\n================================\n"); }
	 */
}
