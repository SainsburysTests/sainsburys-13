package uk.co.aps.sainsburys.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import uk.co.aps.sainsburys.dto.ProductDto;
import uk.co.aps.sainsburys.dto.ScrapeResultDto;
import uk.co.aps.sainsburys.service.Scraper;

public class SeleniumScraper implements Scraper {

	private static final String PRODUCT = "product";
	private static final String PRODUCT_INNER = "productInner";
	private static final String ANCHOR = "h3 > a";
	private static final String HREF = "href";

	/**
	 * Given a url for the sainsburys website, this method will populate the ScrapeResultDto
	 * to represent the products on the page. This implementation uses Selenium.
	 * @param url
	 * @return
	 */
	public ScrapeResultDto scrapePage(String url) {
		ScrapeResultDto result = new ScrapeResultDto();

		try {
			WebDriver driver = new FirefoxDriver();
			driver.get(url);

			// give the page some time to load
			Thread.sleep(3000);

			// get elements from page
			List<WebElement> elements = getProducts(driver);

			// populate a ProductDto for each product on the page
			for (WebElement we : elements) {
				WebElement productInner = we.findElement(By.className(PRODUCT_INNER));
				
				String unitPrice = getUnitPrice(productInner);
				
				WebElement anchor = productInner.findElement(By.cssSelector(ANCHOR));
				String title = anchor.getText();
				
				WebDriver driver2 = new FirefoxDriver();
				driver2.get(anchor.getAttribute(HREF));

				// give the page some time to load
				Thread.sleep(3000);

				String size = getFileSize(driver2);
				String description = driver2.findElement(
						By.className("productText")).getText();

				driver2.close();
				driver2.quit();
				
				result.getResults().add(new ProductDto(title, size, unitPrice, description));
				
			}

			driver.close();
			driver.quit();

			result.setTotal(calculateTotalFromResult(result));

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private List<WebElement> getProducts(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.className(PRODUCT));
		return elements;
	}

	public double calculateTotalFromResult(ScrapeResultDto result) {
		double total = result.getTotal();
		for (ProductDto p : result.getResults()) {
			try{
				total += Double.parseDouble(p.getUnitPrice());
			} catch (NumberFormatException e){
				throw new NumberFormatException();
			}
		}
		return total;
	}

	private String getUnitPrice(WebElement productInner) {
		String unitPriceFromSite = productInner.findElement(By.className("pricePerUnit")).getText();
		return unitPriceFromSite.substring(unitPriceFromSite.indexOf('£') + 1,
				unitPriceFromSite.indexOf("/unit"));
	}

	private String getFileSize(WebDriver driver) throws IOException,
			InterruptedException {

		File file = new File("filename.txt");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(driver.getPageSource());
		bw.close();

		double bytes = file.length();
		double kilobytes = (bytes / 1024);

		file.delete();
		return kilobytes + "kb";
	}

}
