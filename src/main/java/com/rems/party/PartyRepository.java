package com.rems.party;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, Integer> {

	@Query("from Party p where p.partyId != 1")
	public List<Party> getAllPartiesWithoutCashAccount();
	
}
