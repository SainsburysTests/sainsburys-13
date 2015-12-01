package uk.co.aps.sainsburys.dto;

import com.google.gson.annotations.SerializedName;

public class ProductDto {

	private String title;
	
	private String size;
	
	@SerializedName("unit_price")
	private String unitPrice;
	
	private String description;

	public ProductDto(String title, String size, String unitPrice, String description){
		this.title = title;
		this.size = size;
		this.unitPrice = unitPrice;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
