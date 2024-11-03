package com.global.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.DTO.ProductDto;
import com.global.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public void deleteById(int id);
	 
	//List for images(rows with the same product id)
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat where p.id=:id")
	public List<Object[]> getProductById(int id);
	
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat")
	public List<Object[]> finAllProducts();
	 
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat where p.name=:name")
	public List<Object[]> findByName(String name);
	
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat where p.name=:name and p.brand=:brand")
	public Optional<List<Object[]>> findByNameAndBrand(String name,String brand);
	
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat where cat.name=:category and p.brand=:brand")
	public List<Object[]> findByCategoryNameAndBrand(String category, String brand);
	
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat where p.brand=:brand")
	public List<Object[]> findByBrand(String brand);
	
	@Query("select p.id,p.name,p.description,p.brand,p.price,p.quantity,i.id,i.fileName,i.downloadUrl,cat from Product p join p.image i join p.category cat where cat.name=:category")
	public List<Object[]> findByCategoryName(String category);
	
	public int countByCategoryName(String category);
	public int countByNameAndBrand(String name, String brand);
	public int countByBrand(String brand);
	

}
