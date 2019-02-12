package com.perlaagriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerlaAgricultureController {
	
	
	@GetMapping("/")
	public String rootPage() {
		return "redirect:accueil";
	}
	
	@GetMapping("/accueil")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/2")
	public String homePag() {
		return "index2";
	}
	
	@GetMapping("/aFaire")
	public String aFairePag() {
		return "afaire";
	}
	
	@GetMapping("/services")
	public String noServices() {
		return "services";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
}
