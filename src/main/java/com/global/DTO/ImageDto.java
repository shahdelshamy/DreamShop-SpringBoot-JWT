package com.global.DTO;

public class ImageDto {

	private int id;
	private String fileName;
	private String downloadUrl;
	

	public ImageDto(int id, String fileName, String downloadUrl) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.downloadUrl = downloadUrl;
	}
	
	
	public ImageDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	
	
}
