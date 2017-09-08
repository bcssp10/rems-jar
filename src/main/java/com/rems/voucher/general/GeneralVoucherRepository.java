package com.rems.voucher.general;

import org.springframework.stereotype.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface GeneralVoucherRepository extends CrudRepository<GeneralVoucher, Integer> {

	@Override
    @Query
    public Iterable<GeneralVoucher> findAll();

	public Iterable<GeneralVoucher> findAllGeneralVouchersByCashPaidTo(@Param("cashPaidTo")int cashPaidTo);
	
	public Iterable<GeneralVoucher> findAllGeneralVouchersByCashPaidBy(@Param("cashPaidBy")int cashPaidBy);
	
	public Iterable<GeneralVoucher> findGeneralVouchersForLedger(int mainPartyId, Date from, Date to);
	
	public Iterable<Object[]> findGLforCash(Date from, Date to);

	public Iterable<Object[]> findGL(int partyId, Date from, Date to);

	public Iterable<Object[]> findTrialBalance(Date from, Date to);
	
	public Iterable<Object[]> findTrialBalanceforCash(Date from, Date to);
	
	public Iterable<Object[]> findProfitLoss(int purchaseAccountId, int saleAccountId, Date from, Date to);

	/*
	 * Custom Query Example
	 * 
	 * @Query("from Auction a join a.category c where c.name=:categoryName")
	 * public Iterable<Receipt> findByCategory(@Param("categoryName") String
	 * categoryName);
	 * 
	 * public Iterable<Object[]> findtotalDebitAccount(int mainPartyId);
	 * 
	 * public Iterable<Object[]> findtotalCreditAccount(int mainPartyId);
	 */
}
