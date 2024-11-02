package com.global.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.exceptions.AlreadyExistExeption;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Category;
import com.global.repositories.CategoryRepository;

@Service
public class CategoryService implements CategoryInterface {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category)  {
		Optional<Category> category2=categoryRepository.findByName(category.getName());
		if(category2.isEmpty()) {
			return categoryRepository.save(category);
		}else {
			throw new AlreadyExistExeption("This Category Alredy Exist");
		}
		
	}

	@Override
	public Category updateCategory(Category category)  {
		Optional<Category> category2=categoryRepository.findByName(category.getName());
		if(category2.isPresent()) {
			category2.get().setName(category.getName());
			return categoryRepository.save(category2.get());
		}else {
			throw new ResourceNotFoundException("The Category Not Found");
			
		}
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public int deleteCategory(int id) {
		try {
			return categoryRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("The Category Not Found");
		}

	}

	@Override
	public Category getCategoryByName(String name) {
		try {
			return categoryRepository.findByName(name).get();
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("This Category Not Found");
		}
		
	}

}
