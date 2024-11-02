package com.global.repositories;

import java.util.Optional;

import org.hibernate.sql.results.graph.instantiation.internal.ArgumentDomainResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Optional<Category> findByName(String name);
	
	@Query("delete from Category c where c.id=:id ")
	public int deleteById(int id);

}
