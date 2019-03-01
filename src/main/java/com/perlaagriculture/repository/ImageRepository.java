package com.perlaagriculture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;

public interface ImageRepository extends CrudRepository<Image, Integer> {
	
	@Query("from Image p where p.imageType = ?1")
	public List<Image> listCarouselImages(ImageType imageType);

}