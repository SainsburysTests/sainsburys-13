package uk.co.aps.sainsburys.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import uk.co.aps.sainsburys.dto.ProductDto;
import uk.co.aps.sainsburys.dto.ScrapeResultDto;
import uk.co.aps.sainsburys.service.Scraper;


public class SeleniumScraperTest {

	Scraper scraper = new SeleniumScraper();
	
	
	@Test
	public void testCalculatePriceForNoProducts(){
		// given - result object with no products
		ScrapeResultDto result = new ScrapeResultDto();
		
		// when - we calculate the total
		double total = scraper.calculateTotalFromResult(result);
		
		//then - expect unitPrice to be 0
		assertEquals(0 , total, 0);
	}
	
	@Test
	public void testCalculatePriceForTwoProducts(){
		// given - result object with two products
		ScrapeResultDto result = new ScrapeResultDto();
		
		ProductDto prod1 = new ProductDto("title", "89kb", "1.20", "description");
		ProductDto prod2 = new ProductDto("title", "89kb", "3.40", "description");
		
		result.getResults().addAll(Arrays.asList(prod1, prod2));
		
		// when - we calculate the total
		double total = scraper.calculateTotalFromResult(result);
		
		//then - expect unitPrice to be 0
		assertEquals(4.6, total, 0);
	}
	
	@Test(expected=NumberFormatException.class)
	public void testCalculatePriceForTwoProductsOneWithWordsInPrice(){
		// given - result object with two products
		ScrapeResultDto result = new ScrapeResultDto();
		
		ProductDto prod1 = new ProductDto("title", "89kb", "1.2www0", "description");
		ProductDto prod2 = new ProductDto("title", "89kb", "3.40", "description");
		
		result.getResults().addAll(Arrays.asList(prod1, prod2));
		
		// when - we calculate the total
		scraper.calculateTotalFromResult(result);
		
		//then - NumberFormatException
	}
	
	
	// test based on a sample html file
	@Test
	public void testScraper(){
		// given - the html files defined
		
		// when - we run the scrapePage method.
		String fileName = Thread.currentThread()
		        .getContextClassLoader()
		        .getResource("lister.html")
		        .getPath();
		ScrapeResultDto result = scraper.scrapePage("file://" +fileName);
		
		//then - expect to get 2 product 
		assertEquals(2, result.getResults().size());
		assertEquals(4.80, result.getTotal(),0);
		
		ProductDto prod1 = result.getResults().get(0);
		assertEquals("Product 1", prod1.getTitle());
		assertEquals("1.80", prod1.getUnitPrice());
		assertEquals("Product1 Desc", prod1.getDescription());
		assertEquals("0.1376953125kb", prod1.getSize());
		
		ProductDto prod2 = result.getResults().get(1);
		assertEquals("Product 2", prod2.getTitle());
		assertEquals("3.00", prod2.getUnitPrice());
		assertEquals("Product2 Desc", prod2.getDescription());
		assertEquals("0.1376953125kb", prod2.getSize());
	}
	
}
