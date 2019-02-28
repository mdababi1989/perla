package com.perlaagriculture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;
import com.perlaagriculture.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageRepository imageRepository;

	@Override
	public void createImage(Image image) {
		imageRepository.save(image);
	}

	@Override
	public void removeImage(Image image) {
		imageRepository.delete(image);
	}

	@Override
	public void updateImage(Image image) {
		imageRepository.save(image);
	}

	@Override
	public void getImageById(int id) {
		imageRepository.findById(id);
	}

	@Override
	public List<Image> listAllImages() {
		return (List<Image>) imageRepository.findAll();
	}

	@Override
	public List<Image> listCarouselImages() {
		@SuppressWarnings("unchecked")
		List<Image> carouselImages= (List<Image>) imageRepository.listCarouselImages(ImageType.CAROUSEL);		
		return carouselImages;
	}

}
