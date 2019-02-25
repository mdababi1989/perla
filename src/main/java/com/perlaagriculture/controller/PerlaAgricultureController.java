package com.perlaagriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerlaAgricultureController {

	@GetMapping("/")
	public String rootPage() {
		return "redirect:accueil";
	}

	@GetMapping("accueil")
	public String homePage() {
		return "index";
	}

	@GetMapping("aFaire")
	public String aFairePag() {
		return "afaire";
	}

	@GetMapping("assistance")
	public String Assistance() {
		return "assistance";
	}

	@GetMapping("accompagnement")
	public String accompagnement() {
		return "accompagnement";
	}
	
	@GetMapping("cleenmain")
	public String cleenmain() {
		return "cleenmain";
	}
	
	@GetMapping("autreservices")
	public String autreservices() {
		return "autreservices";
	}

	@GetMapping("/services")
	public String noServices() {
		return "services";
	}

	@GetMapping("contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("apropos")
	public String apropos() {
		return "apropos";
	}

}
