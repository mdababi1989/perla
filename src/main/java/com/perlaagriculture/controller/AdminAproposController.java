package com.perlaagriculture.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;
import com.perlaagriculture.service.ImageService;

@Controller
public class AdminAproposController {
	@Autowired
	ImageService imageService;
	@Autowired
	ServletContext context;

	@GetMapping("/adminapropos")
	public String rootAdmin(Model model, @RequestParam("page") Optional<Integer> page) {
		model.addAttribute("carousellist", imageService.listTypeImages(ImageType.APROPOS, 0));
		if (imageService.listTypeImages(ImageType.APROPOS, 1).size() > 0)
			model.addAttribute("imagePrincipal", imageService.listTypeImages(ImageType.APROPOS, 1).get(0));
		else
			model.addAttribute("imagePrincipal", new Image());
		
		
		int currentPage = page.orElse(1);
		int pageSize = 9;

		Page<Image> imagePage = imageService.findPaginated(PageRequest.of(currentPage - 1, pageSize), ImageType.APROPOS);
		model.addAttribute("imagePage", imagePage);
		int totalPages = imagePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		
		return "admin/adminapropos";
	}

	@PostMapping("/adminapropos")
	public ModelAndView rootAdminPost(Model model, @RequestParam("file") MultipartFile[] files,
			RedirectAttributes attributes) throws IOException {
		String absolutePath = context.getRealPath("/");
		for (MultipartFile file : files) {
			Image image = new Image();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(absolutePath + "images/apropos/" + file.getOriginalFilename());
			int index = 0;
			String filename = file.getOriginalFilename();
			while (Files.exists(path)) {
				index++;
				filename = "r" + index + file.getOriginalFilename();
				path = Paths.get(absolutePath + "images/apropos/" + filename);
			}

			image.setPath(filename);
			image.setImageType(ImageType.APROPOS);
			imageService.createImage(image);
			Files.createDirectories(path.getParent());
			Files.write(path, bytes);
		}
		ModelAndView modelAndView = new ModelAndView();
		attributes.addFlashAttribute("message", "Images ajoutées avec succès");
		modelAndView.setViewName("redirect:adminapropos");
		return modelAndView;
	}

	@GetMapping("adminapropos/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
		System.out.println(imageName);

		String absolutePath = context.getRealPath("/") + "images/apropos/" + imageName;

		File serverFile = new File(absolutePath);
		if (serverFile.exists())
			return Files.readAllBytes(serverFile.toPath());
		else
			return new byte[0];

	}

	@PostMapping("/adminapropos/update/{id}")
	public ModelAndView updateImage(@PathVariable(value = "id") int id, @RequestParam("file") MultipartFile file)
			throws IOException {
		Image image = imageService.getImageById(id);
		if (image != null) {
			String absolutePath = context.getRealPath("/") + "images/apropos/" + image.getPath();
			imageService.removeImage(image);
			File file1 = new File(absolutePath);
			file1.delete();
		}

		String absolutePath = context.getRealPath("/");
		image = new Image();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(absolutePath + "images/apropos/" + file.getOriginalFilename());
		int index = 0;
		String filename = file.getOriginalFilename();
		while (Files.exists(path)) {
			index++;
			filename = "r" + index + file.getOriginalFilename();
			path = Paths.get(absolutePath + "images/apropos/" + filename);
		}

		image.setPath(filename);
		image.setImageType(ImageType.APROPOS);
		image.setPrincipal(1);
		imageService.createImage(image);
		Files.createDirectories(path.getParent());
		Files.write(path, bytes);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/adminapropos");
		return modelAndView;
	}

	@GetMapping("/adminapropos/remove/{id}")
	public ModelAndView removeImage(@PathVariable(value = "id") int id) {
		Image image = imageService.getImageById(id);
		if (image != null) {
			String absolutePath = context.getRealPath("/") + "images/apropos/" + image.getPath();
			imageService.removeImage(image);
			File file = new File(absolutePath);
			file.delete();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/adminapropos");
		return modelAndView;
	}

}