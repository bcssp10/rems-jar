package com.rems.account;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rems.util.ParamFactory;

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

	// show profitLoss form
	@RequestMapping(value = "/profit_loss", method = RequestMethod.GET)
	public String profitLossForm(Model model) {
		model.addAttribute("accountList", accountService.getAllAccounts());
		return "account/profit_loss_form";
	}
	
	// calculate profitLoss
	@RequestMapping(value = "/profit_loss", method = RequestMethod.POST)
	public String profitLoss(HttpServletRequest request , Model model){

		int accountId = ParamFactory.getInt(request, "accountId");
		Date from = ParamFactory.getDate(request, "from");
		Date to = ParamFactory.getDate(request, "to");
		
		model.addAttribute("ledgerList",accountService.calculateprofitLoss(accountId,from,to))
			 .addAttribute("account",accountService.getAccountById(accountId));
		return "account/profit_loss";
	}
}
