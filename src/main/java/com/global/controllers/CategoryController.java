package com.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.exceptions.AlreadyExistExeption;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Category;
import com.global.response.ApiResponse;
import com.global.service.category.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/allCategory")
	public ResponseEntity<ApiResponse>AllCategory(){
		return ResponseEntity.ok(new ApiResponse("Sucess",categoryService.getAllCategory()));
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<ApiResponse>getByName(@PathVariable String name){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",categoryService.getCategoryByName(name)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<ApiResponse>addCategory(@RequestBody Category cat){
		try {
			Category category=categoryService.addCategory(cat);
			return ResponseEntity.ok(new ApiResponse("Sucess",category));
		} catch (AlreadyExistExeption e) {
			return ResponseEntity.ok(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category cat){
		try {
			Category category=categoryService.updateCategory(cat);
			return ResponseEntity.ok(new ApiResponse("Sucess",category));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable int id){
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.ok(new ApiResponse("Success",null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
}
