package com.perlaagriculture.service;

import java.util.List;

import com.perlaagriculture.bean.Image;
import com.perlaagriculture.bean.ImageType;

public interface ImageService {
	
	public void createImage(Image image);
	public void removeImage(Image image);
	public void updateImage(Image image);
	public Image getImageById(int id);
	public List<Image> listAllImages();
	public List<Image> listTypeImages(ImageType imageType);

}
