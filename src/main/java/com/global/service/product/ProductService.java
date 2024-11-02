package com.global.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.metamodel.model.domain.ManagedDomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.DTO.ImageDto;
import com.global.DTO.ProductDto;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Category;
import com.global.models.Product;
import com.global.repositories.CategoryRepository;
import com.global.repositories.ProductRepository;
import com.global.requests.AddProduct;


@Service
public class ProductService implements ProductInterface {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Product addProduct(AddProduct product) {

		Product saveProduct=new Product();
		
		Optional<Category> category=categoryRepository.findByName(product.getCategory().getName());
		if(category.isEmpty()) {
			Category category2=new Category(product.getCategory().getName());
			Category savedCategory=categoryRepository.save(category2);
			saveProduct.setCategory(savedCategory);
		}else {
			saveProduct.setCategory(category.get());
		}
		saveProduct.setBrand(product.getBrand());
		saveProduct.setDescription(product.getDescription());
		saveProduct.setName(product.getName());
		saveProduct.setPrice(product.getPrice());
		saveProduct.setQuantity(product.getQuantity());
		
		return productRepository.save(saveProduct);
	}

	@Override
	public Product updateProduct(AddProduct product)  {
		 Product checkProduct=productRepository.findById(product.getId()).get();
		 if(checkProduct != null) {
			 Optional<Category> category=categoryRepository.findByName(product.getCategory().getName());
				if(category.isEmpty()) {
					Category category2=new Category(product.getCategory().getName());
					Category savedCategory=categoryRepository.save(category2);
					checkProduct.setCategory(savedCategory);
				}else {
					checkProduct.setCategory(category.get());
				}
				checkProduct.setBrand(product.getBrand());
				checkProduct.setDescription(product.getDescription());
				checkProduct.setName(product.getName());
				checkProduct.setPrice(product.getPrice());
				checkProduct.setQuantity(product.getQuantity());
				return productRepository.save(checkProduct);
		 }else {
			 throw new ResourceNotFoundException("This product not found") ;
		 }
	}
	
	public Product findById(int id) {
		return productRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Product With This Id Not Found")
				);
	}
	
	@Override
	public List<ProductDto> getProductById(int id) {
		try {
			List<Object[]> products= productRepository.getProductById(id);
			return mapResults(products);
			
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Product With This Id Not Found");
		}
			
	}
	
	@Override
	public void deleteProductById(int id) {
		 productRepository.deleteById(id);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Object[]> products= productRepository.finAllProducts();
		return mapResults(products);
	}

	@Override
	public List<ProductDto> getProductByName(String name) {
		List<Object[]> products=productRepository.findByName(name);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Name");
		}else {
			return mapResults(products);
		}
		
		
	}

	@Override
	public List<ProductDto> getProductByCategory(String category) {
		List<Object[]> products=productRepository.findByCategoryName(category);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Category");
		}else {
			return mapResults(products);
		}
	}

	@Override
	public List<ProductDto> getProductByBrand(String brand) { 
		
			List<Object[]> products=productRepository.findByBrand(brand);
			if(products.size()==0) {
				throw new ResourceNotFoundException("Not Found Products With This Brand");
			}else {
				return mapResults(products);
			}
	}

	@Override
	public List<ProductDto> getProductByCategoryAndBrand(String category, String brand) {
		List<Object[]> products=productRepository.findByCategoryNameAndBrand(category,brand);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Brand And Category");
		}else {
			 return mapResults(products);
		}
		
	}

	@Override
	public List<ProductDto> getProductByNameAndBrand(String name, String brand) {
		List<Object[]> products=productRepository.findByNameAndBrand(name,brand);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Brand And Name");
		}else {
			return mapResults(products);
		}
	}

	@Override
	public int countAllProducts() {
		return (int) productRepository.count();
	}

	@Override
	public int coundProductByNameAndBrand(String name, String brand) {
		return productRepository.countByNameAndBrand(name,brand);
	}
	
	@Override
	public int coundProductByCategory(String category) {
		return productRepository.countByCategoryName(category);
	}
	
	@Override
	public int coundProductByBrand(String brand) {
		return productRepository.countByBrand(brand);
	}
	
	public List<ProductDto> mapResults(List<Object[]> products){
		
		Map<Integer,ProductDto> productsMap=new HashMap<>();
		
		for (Object[] row : products) {
	        Integer productId = (Integer) row[0];
	        String productName = (String) row[1];
	        String description = (String) row[2];
	        String brand = (String) row[3];
	        Float price = (Float) row[4];
	        Integer quantity = (Integer) row[5];
 
	        ImageDto imageDto = new ImageDto(
	            (Integer) row[6],     
	            (String) row[7],     
	            (String) row[8]       
	        );
	        
	        Category category = (Category) row[9];

	        // Fetch or create ProductDto, then add image
	        //If the productId already exists in the productsMap, the computeIfAbsent method will not create a new ProductDto instance; it will simply return the existing ProductDto mapped to that productId. The lambda expression (id -> /* ... */) will only be executed when productId is not already present in the productsMap.
	        ProductDto productDto = productsMap.computeIfAbsent(productId, id -> 
	            new ProductDto(id, productName, description, brand, price, quantity, new ArrayList<>(), category)
	        );
	        
	        productDto.getImage().add(imageDto);	
			
		}
		
		return new ArrayList<>(productsMap.values());
		
	}


	
	
	
	
	
	

}
