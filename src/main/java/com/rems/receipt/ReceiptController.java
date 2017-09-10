package com.rems.receipt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rems.enumeration.PaymentType;
import com.rems.party.PartyService;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private PartyService partyService;

	// view all receipts
	@RequestMapping
	public String getAllReceipts(Model model) {
		model.addAttribute("receipts", receiptService.getAllReceipts());
		model.addAttribute("total",receiptService.calculateTotalAmount(receiptService.getAllReceipts()));
		return "receipt/list";
	}

	// edit receipt form
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getRecipet(@PathVariable int id, Model model) {

		model.addAttribute("receipt", receiptService.getReceiptById(id))
			 .addAttribute("paymentTypes",PaymentType.findAll())
			 .addAttribute("partyList",partyService.getAllPartiesWithoutCashAccount());

		return "receipt/form";
	}

	// add receipt form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String receiptForm(Model model) {

		model.addAttribute("receipt", new Receipt())
			 .addAttribute("paymentTypes", PaymentType.findAll())
			 .addAttribute("partyList",partyService.getAllPartiesWithoutCashAccount());

		return "receipt/form";
	}

	// save new receipt
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveReceipt(Model model, @ModelAttribute("receipt") Receipt receipt, RedirectAttributes redirectAttributes) {
		receiptService.save(receipt);
		redirectAttributes.addFlashAttribute("successMsg","Receipt Added Successfully");
		return "redirect:/receipt";
	}

	// update receipt
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateReceipt(@ModelAttribute Receipt receipt, Model model, @PathVariable int id,RedirectAttributes redirectAttributes) {
		receiptService.updateReceiptById(id, receipt);
		redirectAttributes.addFlashAttribute("successMsg","Receipt Updated Successfully");
		return "redirect:/receipt";
	}

	// delete receipt
	@RequestMapping(value = "/delete/{id}")
	public String deleteRecipet(Model model, @PathVariable int id,RedirectAttributes redirectAttributes) {
		receiptService.deleteReceipt(id);
		redirectAttributes.addFlashAttribute("successMsg","Receipt Deleted Successfully");
		return "redirect:/receipt";
	}

	// print receipt
	@RequestMapping(value = "/print/{id}")
	public String printRecipet(@ModelAttribute Receipt receipt, Model model, @PathVariable int id) {
		model.addAttribute("receipt", receiptService.getReceiptById(id));
		return "receipt/preview";
	}
	
	// generate receipts for specific party
	@RequestMapping(value = "/party/{partyId}")
	public String generatePartyReceipt(@ModelAttribute Receipt receipt, Model model, @PathVariable int partyId) {
		List<Receipt> receipts = receiptService.findAllReceiptsByPartyId(partyId);
		model.addAttribute("receipt",receipts);
		model.addAttribute("total",receiptService.calculateTotalAmount(receipts));
		
		return "receipt/party_receipt_list";
	}

}
