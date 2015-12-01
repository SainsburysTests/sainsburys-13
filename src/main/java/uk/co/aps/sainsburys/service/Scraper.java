package uk.co.aps.sainsburys.service;

import uk.co.aps.sainsburys.dto.ScrapeResultDto;

public interface Scraper {
	
	/**
	 * Given a url for the sainsburys website, this method will populate the ScrapeResultDto
	 * to represent the products on the page.
	 * @param url
	 * @return
	 */
	public ScrapeResultDto scrapePage(String url);
	
	/**
	 * Calculate the total cost based on the products on the page
	 * @param result
	 * @return
	 */
	public double calculateTotalFromResult(ScrapeResultDto result);
}
