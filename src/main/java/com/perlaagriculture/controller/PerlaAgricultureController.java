package com.perlaagriculture.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perlaagriculture.bean.Contact;
import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;
import com.perlaagriculture.service.ImageService;

@Controller
public class PerlaAgricultureController {
	@Autowired
	ImageService imageService;
	@Autowired
	ServletContext context;

	@GetMapping("/")
	public String rootPage() {
		return "redirect:accueil";
	}

	@GetMapping("accueil/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

		String absolutePath = context.getRealPath("/") + "images/carousel/"+ imageName;

		File serverFile = new File(absolutePath);

		return Files.readAllBytes(serverFile.toPath());

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

		Page<Image> imagePage = imageService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
				ImageType.ASSISTANCE);

		model.addAttribute("imagePage", imagePage);

		int totalPages = imagePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "assistance";
	}

	@GetMapping("accompagnement")
	public String accompagnement(Model model, @RequestParam("page") Optional<Integer> page) {

		int currentPage = page.orElse(1);
		int pageSize = 9;

		Page<Image> imagePage = imageService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
				ImageType.ACCOMPAGNEMENT);

		model.addAttribute("imagePage", imagePage);

		int totalPages = imagePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "accompagnement";
	}

	@GetMapping("cleenmain")
	public String cleenmain(Model model, @RequestParam("page") Optional<Integer> page) {

		int currentPage = page.orElse(1);
		int pageSize = 9;

		Page<Image> imagePage = imageService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
				ImageType.CLEENMAIN);

		model.addAttribute("imagePage", imagePage);

		int totalPages = imagePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "cleenmain";
	}

	@GetMapping("autreservices")
	public String autreservices(Model model, @RequestParam("page") Optional<Integer> page) {

		int currentPage = page.orElse(1);
		int pageSize = 9;

		Page<Image> imagePage = imageService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
				ImageType.AUTRESSERVICES);

		model.addAttribute("imagePage", imagePage);

		int totalPages = imagePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "autreservices";
	}

	@GetMapping("/services")
	public String noServices(Model model) {
		model.addAttribute("assistancelist", imageService.listTypeImages(ImageType.ASSISTANCE, 1));
		model.addAttribute("accompagnementlist", imageService.listTypeImages(ImageType.ACCOMPAGNEMENT, 1));
		model.addAttribute("cleenmainlist", imageService.listTypeImages(ImageType.CLEENMAIN, 1));
		model.addAttribute("autresserviceslist", imageService.listTypeImages(ImageType.AUTRESSERVICES, 1));

		if (imageService.listTypeImages(ImageType.SERVICE, 1).size() > 0)
			model.addAttribute("imagePrincipal", imageService.listTypeImages(ImageType.SERVICE, 1).get(0));
		else
			model.addAttribute("imagePrincipal", new Image());
		return "services";
	}

	@GetMapping("contact")
	public String contact(Model model) {
		if (imageService.listTypeImages(ImageType.CONTACT, 1).size() > 0)
			model.addAttribute("imagePrincipal", imageService.listTypeImages(ImageType.CONTACT, 1).get(0));
		else
			model.addAttribute("imagePrincipal", new Image());

		return "contact";
	}

	@ModelAttribute("contact")
	public Contact newContact() {
		return new Contact();
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