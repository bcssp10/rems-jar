package com.rems.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(accounts::add);
		return accounts;
	}

	public Account getAccountById(int id) {
		return accountRepository.findOne(id);
	}

	public void save(Account party) {
		accountRepository.save(party);
	}

	public void updateAccountById(int id, Account party) {
		accountRepository.save(party);
	}

	public void deleteAccount(int id) {
		accountRepository.delete(id);
	}

}
