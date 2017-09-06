package com.rems.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	
	// view all accounts
	@RequestMapping
	public String getAllAccounts(Model model) {
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "party/party_list";
	}
	
	/*
	 ================================
	 == 		FORM DISPLAY 	   ==
	 ================================
	 */

	// edit account form
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getAccount(@PathVariable int id, Model model) {

		model.addAttribute("account", accountService.getAccountById(id));
		return "account/account_form";
	}

	// add account form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String accountForm(Model model) {
		model.addAttribute("account", new Account());
		return "account/account_form";
	}

	/*
	 ================================
	 == 	  CRUD OPERATION 	   ==
	 ================================
	 */
	
	// save new account
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAccount(Model model, @ModelAttribute("account") Account account) {
		accountService.save(account);
		return "redirect:/party";
	}

	// update account
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateAccount(@ModelAttribute Account party, Model model, @PathVariable int id) {
		accountService.updateAccountById(id, party);
		return "redirect:/party";
	}

	// delete account
	@RequestMapping(value = "/delete/{id}")
	public String deleteAccount(Model model, @PathVariable int id) {
		accountService.deleteAccount(id);
		return "redirect:/party";
	}

}
