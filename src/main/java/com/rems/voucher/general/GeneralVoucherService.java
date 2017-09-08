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
	
	public List<Object[]> findGeneralVouchersForLedger(int partyId, Date from, Date to) {
		
		List<Object[]> data = new ArrayList<>();
		
		if (partyId == 1) 
			generalVoucherRepository.findGLforCash(from,to).forEach(data::add);	
		else 
			generalVoucherRepository.findGL(partyId,from,to).forEach(data::add);

		return data;
	}
	

	public List<Object[]> calculateTrialBalance(Date from, Date to) {
		List<Object[]> objects = new ArrayList<>();
		generalVoucherRepository.findTrialBalance(from,to).forEach(objects::add);
		
		List<Object[]> cash = new ArrayList<>(1); 
		generalVoucherRepository.findTrialBalanceforCash(from, to).forEach(cash::add);
		
		objects.add(objects.size(),getCashRow(cash.get(0)));
			
		return objects;
	}
	
	private Object[] getCashRow (Object[] Data) {
		
		Object[] cashRow = new Object[4];
		cashRow[0] = "P-01";
		cashRow[1] = Data[0];
		
		if((double)Data[1] <0)
			cashRow[2] = (double)Data[1] * -1;
		else
			cashRow[3] = Data[1];
		
		return cashRow;
	}
	
	public List<Object[]> findProfitLoss(int purchaseAccountId, int saleAccountId, Date from, Date to){
		List<Object[]> profitLoss = new ArrayList<>(); 
		generalVoucherRepository.findProfitLoss(purchaseAccountId,saleAccountId,from,to).forEach(profitLoss::add);
		return profitLoss;
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
