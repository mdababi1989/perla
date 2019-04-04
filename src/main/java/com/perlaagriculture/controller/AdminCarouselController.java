package com.perlaagriculture.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;
import com.perlaagriculture.service.ImageService;

@Controller
public class AdminCarouselController {
	@Autowired
	ImageService imageService;
	@Autowired
	ServletContext context;

	@GetMapping("/admin")
	public String rootAdmin(Model model) {
		model.addAttribute("carousellist", imageService.listTypeImages(ImageType.CAROUSEL,0));
		model.addAttribute("image", new Image());

		return "admin/admincarousel";
	}

	@PostMapping("/admin")
	public ModelAndView rootAdminPost(Model model, @RequestParam("file") MultipartFile file, Image image) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:admin");
		modelAndView.addObject("image", new Image());
		if (file.isEmpty()) {
			model.addAttribute("message", "Veuillez choisir une image");
			return modelAndView;
		}
		try {
			String absolutePath = context.getRealPath("/");
			byte[] bytes = file.getBytes();
			Path path = Paths.get(absolutePath + "images/carousel/"+ file.getOriginalFilename());
			image.setPath(file.getOriginalFilename());
			image.setImageType(ImageType.CAROUSEL);
			imageService.createImage(image);
			Files.createDirectories(path.getParent());
			Files.write(path, bytes);
			model.addAttribute("message", "Image uploaded" + path);
			return modelAndView;

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(image);

		return modelAndView;

	}

	@GetMapping("admin/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
		System.out.println(imageName);

		String absolutePath = context.getRealPath("/") + "images/carousel/"+ imageName;

		File serverFile = new File(absolutePath);

		return Files.readAllBytes(serverFile.toPath());

	}

	@GetMapping("/admin/remove/{id}")
	public ModelAndView removeImage(@PathVariable(value = "id") int id) {
		Image image = imageService.getImageById(id);
		if(image!=null) {
			String absolutePath = context.getRealPath("/")+ "images/carousel/"+ image.getPath();
			imageService.removeImage(image);
			File file = new File(absolutePath);
			file.delete();
		}
				
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin");
		modelAndView.addObject("image", new Image());

		return modelAndView;

	}

}