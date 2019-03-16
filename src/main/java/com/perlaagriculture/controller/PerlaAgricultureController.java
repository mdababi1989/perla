package com.perlaagriculture.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;
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
		if (imageService.listTypeImages(ImageType.CAROUSEL, 0).size() > 0)
			model.addAttribute("carouselsize", imageService.listTypeImages(ImageType.CAROUSEL, 0).size() - 1);
		else
			model.addAttribute("carouselsize", 0);
		model.addAttribute("carousellist", imageService.listTypeImages(ImageType.CAROUSEL, 0));
		model.addAttribute("accueillist", imageService.listTypeImages(ImageType.ACCUEIL, 0));

		return "index";
	}

	@GetMapping("aFaire")
	public String aFairePag() {
		return "afaire";
	}

	@GetMapping("assistance")
	public String Assistance(Model model, @RequestParam("page") Optional<Integer> page) {

		int currentPage = page.orElse(1);
		int pageSize = 9;

		Page<Image> imagePage = imageService.findPaginated(PageRequest.of(currentPage - 1, pageSize), ImageType.ASSISTANCE);

		model.addAttribute("imagePage", imagePage);

		int totalPages = imagePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

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
	public String noServices(Model model) {
		model.addAttribute("assistancelist", imageService.listTypeImages(ImageType.ASSISTANCE, 1));
		return "services";
	}

	@GetMapping("contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("apropos")
	public String apropos(Model model) {
		model.addAttribute("aproposlist", imageService.listTypeImages(ImageType.APROPOS, 0));
		if (imageService.listTypeImages(ImageType.APROPOS, 1).size() > 0)
			model.addAttribute("imagePrincipal", imageService.listTypeImages(ImageType.APROPOS, 1).get(0));
		else
			model.addAttribute("imagePrincipal", new Image());

		return "apropos";
	}

}
