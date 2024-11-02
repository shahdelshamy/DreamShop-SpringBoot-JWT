package com.global.service.category;

import java.util.List;

import com.global.models.Category;

public interface CategoryInterface {
	
	public Category addCategory(Category category)throws Exception;
	public Category updateCategory(Category category)throws Exception;
	public Category getCategoryById(int id);
	public Category getCategoryByName(String name);
	public List<Category> getAllCategory();
	public int deleteCategory(int id);
	

}
