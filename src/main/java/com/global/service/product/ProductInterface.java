package com.global.service.product;

import java.util.List;

import com.global.DTO.ProductDto;
import com.global.models.Product;
import com.global.requests.AddProduct;


public interface ProductInterface {

	
	public Product addProduct(AddProduct product);
	public void deleteProductById(int id);
	public Product updateProduct(AddProduct product) throws Exception;
	
	public List<ProductDto> getProductById(int id);
	public List<ProductDto> getAllProducts();
	public List<ProductDto> getProductByName(String name);
	public List<ProductDto> getProductByCategory(String category);
	public List<ProductDto> getProductByBrand(String brand);
	public List<ProductDto> getProductByCategoryAndBrand(String category,String brand);
	public List<ProductDto> getProductByNameAndBrand(String name,String brand);
	
	public int countAllProducts();
	public int coundProductByBrand(String brand);
	public int coundProductByCategory(String category);
	public int coundProductByNameAndBrand(String name,String brand);
	
	
	
	
}
