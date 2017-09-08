package com.rems.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/party")
public class PartyController {

	@Autowired
	private PartyService partyService;

	
	// view all parties
	@RequestMapping
	public String getAllParties(Model model) {
		model.addAttribute("parties", partyService.getAllParties());
		return "party/party_list";
	}
	
	/*
	 ================================
	 == 		FORM DISPLAY 	   ==
	 ================================
	 */

	// edit party form
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getParty(@PathVariable int id, Model model) {

		model.addAttribute("party", partyService.getPartyById(id));
		return "party/party_form";
	}

	// add party form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String partyForm(Model model) {
		model.addAttribute("party", new Party());
		return "party/party_form";
	}

	/*
	 ================================
	 == 	  CRUD OPERATION 	   ==
	 ================================
	 */
	
	// save new party
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveParty(Model model, @ModelAttribute("party") Party party) {
		partyService.save(party);
		return "redirect:/party";
	}

	// update party
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateParty(@ModelAttribute Party party, Model model, @PathVariable int id) {
		partyService.updatePartyById(id, party);
		return "redirect:/party";
	}

	// delete party
	@RequestMapping(value = "/delete/{id}")
	public String deleteParty(Model model, @PathVariable int id) {
		
		if(id != 1)
			partyService.deleteParty(id);
		
		return "redirect:/party";
	}

}
