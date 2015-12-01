package uk.co.aps.sainsburys.dto;

import java.util.ArrayList;
import java.util.List;

public class ScrapeResultDto {

	private List<ProductDto> results = new ArrayList<ProductDto>();
	private double total;
	
	public List<ProductDto> getResults() {
		return results;
	}
	public void setResults(List<ProductDto> results) {
		this.results = results;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
