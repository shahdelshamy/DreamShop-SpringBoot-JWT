package com.global.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.global.DTO.ImageDto;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Image;
import com.global.response.ApiResponse;
import com.global.service.image.ImageService;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	
	@Autowired
	private ImageService imageService;
	
	
	@PostMapping("/upload")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> addImages(@RequestParam int productId,@RequestParam List<MultipartFile> files){
		List<ImageDto> imagesDtos;
		try {
			imagesDtos = imageService.saveImages(productId, files);
			return ResponseEntity.ok(new ApiResponse("Upload Sucess!",imagesDtos));
		} catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!", e.getMessage()));
        }
		
	}
	
	 @GetMapping("/image/download/{imageId}")
	    public ResponseEntity<Resource> downloadImage(@PathVariable int imageId) throws SQLException {
	        Image image = imageService.getImageById(imageId);
	        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
	        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName() + "\"")
	                .body(resource);
	    }
	
	 @PutMapping("/updateImage")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public ResponseEntity<ApiResponse>update(@RequestParam MultipartFile file,@RequestParam int imageId){
		 Image image=imageService.getImageById(imageId);
		 if(image !=null) {
			 ImageDto imageDto;
			try {
				imageDto = imageService.updateImage(file, imageId);
				return ResponseEntity.ok(new ApiResponse("Update Success!",imageDto));
			}catch (Exception e) {
				return ResponseEntity.ok(new ApiResponse("Update failed!",e.getMessage()));
			}
		 }else {
			 return ResponseEntity.ok(new ApiResponse("Image Not Found!",null));
		 }
	 }
	 
	 @GetMapping("productId/{id}")
	 public ResponseEntity<ApiResponse>findByProductId(@PathVariable int id){
		 try {
			 List<ImageDto> image=imageService.getImageByProductId(id);
			 return ResponseEntity.ok(new ApiResponse("Success!",image));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
		}
		 
	 }
	 
	 @DeleteMapping("/image/{imageId}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 public ResponseEntity<ApiResponse>delete(@PathVariable int imageId){
		 Image image=imageService.getImageById(imageId);
		 if(image !=null) {
			    imageService.deleteImageById(imageId);
				return ResponseEntity.ok(new ApiResponse("Update Success!",null));
		 }else {
			 return ResponseEntity.ok(new ApiResponse("Image Not Found!",null));
		 }
	 }
	 
	 
	 
	 
	 
	 
}

	 
	 
	 