package com.rems.receipt;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {

	@Override
    @Query
    public Iterable<Receipt> findAll();
	
	@Query
	public Iterable<Receipt> findAllReceiptsByParty(@Param("partyId") int partyId);
	
	/*
	 * Custom Query Example
	 * 
	@Query("from Auction a join a.category c where c.name=:categoryName")
	public Iterable<Receipt> findByCategory(@Param("categoryName") String categoryName);
	*/
}
