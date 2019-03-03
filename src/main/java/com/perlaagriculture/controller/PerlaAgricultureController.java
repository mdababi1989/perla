package com.perlaagriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perlaagriculture.service.ImageService;

@Controller
public class PerlaAgricultureController {
	@Autowired
	ImageService imageService;

	@GetMapping("/")
	public String rootPage() {
		return "redirect:accueil";
	}

	@GetMapping("accueil")
	public String homePage(Model model) {
		if(imageService.listCarouselImages().size()>0)
		model.addAttribute("carouselsize", imageService.listCarouselImages().size()-1);
		else 
			model.addAttribute("carouselsize", 0);
		model.addAttribute("carousellist", imageService.listCarouselImages());
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
