package com.perlaagriculture.service;

import java.util.List;
import java.util.Optional;

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
	public Image getImageById(int id) {
		Optional<Image> image= imageRepository.findById(id);
		if(image.isPresent()) return image.get();
		return null;
	}

	@Override
	public List<Image> listAllImages() {
		return (List<Image>) imageRepository.findAll();
	}

	@Override
	public List<Image> listTypeImages(ImageType imageType) {
		List<Image> carouselImages= (List<Image>) imageRepository.listCarouselImages(imageType);		
		return carouselImages;
	}

}
