package com.rems.account;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	@Override
    public Iterable<Account> findAll();
	
	public Iterable<Object[]> calculateProfitLoss(int accountId, Date from, Date to);
}
