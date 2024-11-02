package com.global.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.global.DTO.ImageDto;
import com.global.models.Image;

public interface ImageInterface {

	public Image getImageById(int id);
	public void deleteImageById(int id);
	public List<ImageDto>saveImages(int productId,List<MultipartFile>files)throws SerialException, SQLException, IOException;
	public ImageDto updateImage(MultipartFile file,int id)throws SerialException, SQLException, IOException;
	
}
