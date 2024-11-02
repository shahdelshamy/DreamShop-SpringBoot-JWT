package com.global.DTO;

import java.util.List;

import com.global.models.Category;
import com.global.models.Image;


public class ProductDto {

	private int id;
	private String name;
	private String description;
	private String brand;
	private float price;
	private int quantity;
	private List<ImageDto> image;
	
	private Category category;

	public ProductDto(int id, String name, String description, String brand, float price, int quantity,
			List<ImageDto> image, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.category = category;
	}

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public List<ImageDto> getImage() {
		return image;
	}

	public void setImage(List<ImageDto> image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
