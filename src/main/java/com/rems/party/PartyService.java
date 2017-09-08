package com.rems.party;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PartyService {
	
	@Autowired
	private PartyRepository partyRepository;

	public List<Party> getAllPartiesWithoutCashAccount() {
		List<Party> parties = new ArrayList<>();
		partyRepository.getAllPartiesWithoutCashAccount().forEach(parties::add);
		return parties;
	}
	
	public List<Party> getAllParties() {
		List<Party> parties = new ArrayList<>();
		partyRepository.findAll().forEach(parties::add);
		return parties;
	}

	public Party getPartyById(int id) {
		return partyRepository.findOne(id);
	}

	public void save(Party party) {
		partyRepository.save(party);
	}

	public void updatePartyById(int id, Party party) {
		partyRepository.save(party);
	}

	public void deleteParty(int id) {
		partyRepository.delete(id);
	}

}
