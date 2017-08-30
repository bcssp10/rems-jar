package com.rems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rems.party.PartyService;

@Controller
public class MainController {
	
	@Autowired
	private PartyService partyService;

	  @RequestMapping("/")
	  public String index(Model model){
		  model.addAttribute("parties", partyService.getAllParties());
	      return "/party/party_list";
	  }

}
