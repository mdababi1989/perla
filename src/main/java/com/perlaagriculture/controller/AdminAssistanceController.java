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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;
import com.perlaagriculture.service.ImageService;

@Controller
public class AdminAssistanceController {
	@Autowired
	ImageService imageService;
	@Autowired
	ServletContext context;

	@GetMapping("/adminassistance")
	public String rootAdmin(Model model) {
		model.addAttribute("carousellist", imageService.listTypeImages(ImageType.ASSISTANCE, 0));
		if (imageService.listTypeImages(ImageType.ASSISTANCE, 1).size() > 0)
			model.addAttribute("imagePrincipal1", imageService.listTypeImages(ImageType.ASSISTANCE, 1).get(0));
		else
			model.addAttribute("imagePrincipal1", new Image());
		if (imageService.listTypeImages(ImageType.ASSISTANCE, 1).size() > 1)
			model.addAttribute("imagePrincipal2", imageService.listTypeImages(ImageType.ASSISTANCE, 1).get(1));
		else
			model.addAttribute("imagePrincipal2", new Image());
		return "admin/adminassistance";
	}

	@PostMapping("/adminassistance")
	public ModelAndView rootAdminPost(Model model, @RequestParam("file") MultipartFile[] files,
			RedirectAttributes attributes) throws IOException {
		String absolutePath = context.getRealPath("/");
		for (MultipartFile file : files) {
			Image image = new Image();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(absolutePath + "images/assistance/" + file.getOriginalFilename());
			int index = 0;
			String filename = file.getOriginalFilename();
			while (Files.exists(path)) {
				index++;
				filename = "r" + index + file.getOriginalFilename();
				path = Paths.get(absolutePath + "images/assistance/" + filename);
			}

			image.setPath(filename);
			image.setImageType(ImageType.ASSISTANCE);
			imageService.createImage(image);
			Files.createDirectories(path.getParent());
			Files.write(path, bytes);
		}
		ModelAndView modelAndView = new ModelAndView();
		attributes.addFlashAttribute("message", "Images ajoutées avec succès");
		modelAndView.setViewName("redirect:adminassistance");
		return modelAndView;
	}

	@GetMapping("adminassistance/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
		System.out.println(imageName);

		String absolutePath = context.getRealPath("/") + "images/assistance/" + imageName;

		File serverFile = new File(absolutePath);
		if (serverFile.exists())
			return Files.readAllBytes(serverFile.toPath());
		else
			return new byte[0];

	}

	@PostMapping("/adminassistance/update/{id}")
	public ModelAndView updateImage(@PathVariable(value = "id") int id, @RequestParam("file") MultipartFile file)
			throws IOException {
		Image image = imageService.getImageById(id);
		if (image != null) {
			String absolutePath = context.getRealPath("/") + "images/assistance/" + image.getPath();
			imageService.removeImage(image);
			File file1 = new File(absolutePath);
			file1.delete();
		}

		String absolutePath = context.getRealPath("/");
		image = new Image();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(absolutePath + "images/assistance/" + file.getOriginalFilename());
		int index = 0;
		String filename = file.getOriginalFilename();
		while (Files.exists(path)) {
			index++;
			filename = "r" + index + file.getOriginalFilename();
			path = Paths.get(absolutePath + "images/assistance/" + filename);
		}

		image.setPath(filename);
		image.setImageType(ImageType.ASSISTANCE);
		image.setPrincipal(1);
		imageService.createImage(image);
		Files.createDirectories(path.getParent());
		Files.write(path, bytes);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/adminassistance");
		return modelAndView;
	}

	@GetMapping("/adminassistance/remove/{id}")
	public ModelAndView removeImage(@PathVariable(value = "id") int id) {
		Image image = imageService.getImageById(id);
		if (image != null) {
			String absolutePath = context.getRealPath("/") + "images/assistance/" + image.getPath();
			imageService.removeImage(image);
			File file = new File(absolutePath);
			file.delete();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/adminassistance");
		return modelAndView;
	}

}