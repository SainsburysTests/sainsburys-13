package uk.co.aps.sainsburys;

import uk.co.aps.sainsburys.dto.ScrapeResultDto;
import uk.co.aps.sainsburys.service.Scraper;
import uk.co.aps.sainsburys.service.impl.SeleniumScraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {
	private static final String URL = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?"
			+ "listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&"
			+ "langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&"
			+ "storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&"
			+ "parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&"
			+ "beginIndex=0&hideFilters=true";

	private static Scraper scraper;

	public static void main(String[] args) throws InterruptedException {
		// Will use the selenium implementation
		scraper = new SeleniumScraper();

		// scrape the page and return ScrapeResultDto
		ScrapeResultDto result = scraper.scrapePage(URL);

		// Display the results in JSON format
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()
				.create();
		System.out.println(gson.toJson(result));
	}
}
