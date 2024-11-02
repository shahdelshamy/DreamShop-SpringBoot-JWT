package com.global.requests;


import com.global.models.Category;

public class AddProduct {
	private int id;
	private String name;
	private String description;
	private String brand;
	private float price;
	private int quantity;
	private Category category;
	
	
	public AddProduct(String name, String description, String brand, float price, int quantity, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
