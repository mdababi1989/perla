package com.perlaagriculture.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Optional<Image> image = imageRepository.findById(id);
		if (image.isPresent())
			return image.get();
		return null;
	}

	@Override
	public List<Image> listAllImages() {
		return (List<Image>) imageRepository.findAll();
	}

	@Override
	public List<Image> listTypeImages(ImageType imageType, int principal) {
		List<Image> carouselImages = (List<Image>) imageRepository.listImages(imageType, principal);
		return carouselImages;
	}

	@Override
	public Page<Image> findPaginated(Pageable pageable, ImageType imageType) {
		List<Image> images = imageRepository.listImages(imageType, 0);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Image> list;
		
		
		if (images.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, images.size());
			list = images.subList(startItem, toIndex);
		}

		Page<Image> imagePage = new PageImpl<Image>(list, PageRequest.of(currentPage, pageSize), images.size());

		return imagePage;
	}

}
