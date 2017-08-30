package com.rems.voucher.cash;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface CashVoucherRepository extends CrudRepository<CashVoucher, Integer> {

	@Override
    @Query
    public Iterable<CashVoucher> findAll();

	public Iterable<CashVoucher> findAllCashVouchersByParty(@Param("partyId")int partyId);
	
	/*
	 * Custom Query Example
	 * 
	@Query("from Auction a join a.category c where c.name=:categoryName")
	public Iterable<Receipt> findByCategory(@Param("categoryName") String categoryName);
	*/
}
