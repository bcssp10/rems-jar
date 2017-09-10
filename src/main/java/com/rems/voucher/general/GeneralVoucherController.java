package com.rems.voucher.general;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rems.party.PartyService;
import com.rems.util.ParamFactory;

@Controller
@RequestMapping("/voucher/general")
public class GeneralVoucherController {

	@Autowired
	private GeneralVoucherService generalVoucherService;

	@Autowired
	private PartyService partyService;

	// view all receipts
	@RequestMapping
	public String getAllGeneralVouchers(Model model) {
		model.addAttribute("general_vouchers", generalVoucherService.getAllGeneralVouchers());
		return "voucher/general/general_voucher_list";
	}

	// edit receipt form
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getGeneralVoucher(@PathVariable int id, Model model) {

		model.addAttribute("general_voucher", generalVoucherService.getGeneralVoucherById(id)).addAttribute("partyList",
				partyService.getAllPartiesWithoutCashAccount());

		return "voucher/general/general_voucher_form";
	}

	// add receipt form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String generalVoucherForm(Model model) {

		model.addAttribute("general_voucher", new GeneralVoucher()).addAttribute("partyList",
				partyService.getAllPartiesWithoutCashAccount());

		return "voucher/general/general_voucher_form";
	}

	// save new receipt
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveGeneralVoucher(Model model, @ModelAttribute("general_voucher") GeneralVoucher generalVoucher
			,RedirectAttributes redirectAttributes) {
		generalVoucherService.save(generalVoucher);
		redirectAttributes.addFlashAttribute("successMsg","Voucher Added Successfully");
		return "redirect:/voucher/general";
	}

	// update receipt
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateGeneralVoucher(@ModelAttribute GeneralVoucher generalVoucher, Model model,
			@PathVariable int id,RedirectAttributes redirectAttributes) {
		generalVoucherService.updateGeneralVoucherById(id, generalVoucher);
		redirectAttributes.addFlashAttribute("successMsg","Voucher Updated Successfully");
		return "redirect:/voucher/general";
	}

	// delete receipt
	@RequestMapping(value = "/delete/{id}")
	public String deleteGeneralVoucher(Model model, @PathVariable int id,RedirectAttributes redirectAttributes) {
		generalVoucherService.deleteGeneralVoucher(id);
		redirectAttributes.addFlashAttribute("successMsg","Voucher Deleted Successfully");
		return "redirect:/voucher/general";
	}

	// print receipt
	@RequestMapping(value = "/print/{id}")
	public String printGeneralVoucher(Model model, @PathVariable int id) {
		model.addAttribute("general_voucher", generalVoucherService.getGeneralVoucherById(id));
		return "voucher/general/general_voucher_preview";
	}

	// generate cash vouchers for specific party
	@RequestMapping(value = "/paidTo/{partyId}")
	public String paidToPartyGeneralVouchers(Model model, @PathVariable int partyId) {
		List<GeneralVoucher> generalVouchers = generalVoucherService.findAllGeneralVouchersByPaidToParty(partyId);
		model.addAttribute("generalVoucher", generalVouchers);
		model.addAttribute("total", generalVoucherService.calculateTotalAmount(generalVouchers));
		return "voucher/general/party_general_voucher_list";
	}

	// generate cash vouchers for specific party
	@RequestMapping(value = "/paidBy/{partyId}")
	public String paidByPartyGeneralVouchers(Model model, @PathVariable int partyId) {
		List<GeneralVoucher> generalVouchers = generalVoucherService.findAllGeneralVouchersByPaidByParty(partyId);
		model.addAttribute("generalVoucher", generalVouchers);
		model.addAttribute("total", generalVoucherService.calculateTotalAmount(generalVouchers));
		return "voucher/general/party_general_voucher_list";
	}

	/*@RequestMapping(value = "/trialbalance/{mainPartyId}")
	public String trailBalance(Model model, @PathVariable int mainPartyId) {
		model.addAttribute("mainParty",partyService.getPartyById(mainPartyId));
		model.addAttribute("data",generalVoucherService.calculateTrialBalance(mainPartyId));
		return "voucher/general/trial_balance/trial_balance";
	}*/

	// show account ledger form
	@RequestMapping(value = "/ledger", method = RequestMethod.GET)
	public String accountLedgerForm(Model model) {
		model.addAttribute("partyList", partyService.getAllParties());
		return "voucher/general/ledger/account_ledger_form";
	}

	// show account ledger
	@RequestMapping(value = "/ledger", method = RequestMethod.POST)
	public String accountLedger(HttpServletRequest request , Model model){

		int partyId = ParamFactory.getInt(request, "partyId");
		Date from = ParamFactory.getDate(request, "from");
		Date to = ParamFactory.getDate(request, "to");
		
		model.addAttribute("data",generalVoucherService.findGeneralVouchersForLedger(partyId,from,to))
			 .addAttribute("party",partyService.getPartyById(partyId));
		return "voucher/general/ledger/account_ledger";
	}
	// show trialbalance form
		@RequestMapping(value = "/trialbalance", method = RequestMethod.GET)
		public String trialBalanceForm(Model model) {
			model.addAttribute("partyList", partyService.getAllParties());
			return "voucher/general/trial_balance/trialbalance_form";
		}

		// show trialbalance
		@RequestMapping(value = "/trialbalance", method = RequestMethod.POST)
		public String trialBalance(HttpServletRequest request , Model model){
			Date from = ParamFactory.getDate(request, "from");
			Date to = ParamFactory.getDate(request, "to");
			model.addAttribute("data",generalVoucherService.calculateTrialBalance(from,to));
			return "voucher/general/trial_balance/trial_balance";
		}
		
		// show Profit/Loss form
		@RequestMapping(value = "/profitloss", method = RequestMethod.GET)
		public String profitLossForm(Model model) {
			model.addAttribute("partyList", partyService.getAllPartiesWithoutCashAccount());
			return "voucher/general/profit_loss/profit_loss_form";
		}

		// show Profit/Loss
		@RequestMapping(value = "/profitloss", method = RequestMethod.POST)
		public String profitLoss(HttpServletRequest request , Model model){
			
			int purchasePartyId = ParamFactory.getInt(request, "purchasePartyId");
			int salePartyId = ParamFactory.getInt(request, "salePartyId");
			Date from = ParamFactory.getDate(request, "from");
			Date to = ParamFactory.getDate(request, "to");
			
			model.addAttribute("data",generalVoucherService.findProfitLoss(purchasePartyId,salePartyId,from,to));
			return "voucher/general/profit_loss/profit_loss";
		}
		
		

}
