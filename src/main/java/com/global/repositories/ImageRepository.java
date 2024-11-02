package com.global.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.DTO.ImageDto;
import com.global.models.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	@Query("select new com.global.DTO.ImageDto(i.id,i.fileName,i.downloadUrl) from Image i join i.product p where p.id=:id")
	public List<ImageDto>findByProductId(int id);
}
