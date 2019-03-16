package com.perlaagriculture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;

public interface ImageRepository extends CrudRepository<Image, Integer> {
	
	@Query("from Image p where p.imageType = ?1 and p.principal = ?2")
	public List<Image> listImages(ImageType imageType, int principal);

}
