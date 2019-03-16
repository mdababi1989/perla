package com.perlaagriculture.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;



@Entity
public class Image implements Serializable {
	private static final long serialVersionUID = 7901310903814330380L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;
	
	private String path;
	private String title;
	private String Description;
	private ImageType imageType;
	private int principal;	

	@Override
	public String toString() {
		return "Image [id=" + id + ", path=" + path + ", title=" + title + ", Description=" + Description
				+ ", imageType=" + imageType + "]";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getId() {
		return id;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}
}
