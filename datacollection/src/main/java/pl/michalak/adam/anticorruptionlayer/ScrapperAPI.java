package pl.michalak.adam.anticorruptionlayer;

import java.io.IOException;
import java.util.List;

/**
 * Wrapper for scrapping library. Provides methods
 * that are used by bookstore scrappers to obtain book information.
 */
public class ScrapperAPI {

	ScrappingProvider scrappingProvider;

	public ScrapperAPI(ScrappingProvider scrappingProvider){
		this.scrappingProvider = scrappingProvider;
	}

	/**
	 * Fetches and parses a HTML page.
	 * @param url defines address of a webpage. The protocol must be HTTP or HTTPS.
	 * @throws IOException if obtaining HTML source code is not possible.
	 */
	public void connect(String url) throws IOException {
		scrappingProvider.connect(url);
	}

	/**
	 * Finds elements that match the CSS query, with this element as the starting context.
	 * Matched elements may include this element, or any of its children.
	 * @param tableQuery CSS-like query that defines location of a table in HTML source code.
	 * @return collection of Rows where book information is stored.
	 */
	public List<Row> getTableRows(String tableQuery){
		return scrappingProvider.getTableRows(tableQuery);
	}

	public String getTitle(String titleQuery, Row row) {
		return scrappingProvider.getTitle(titleQuery, row.convertToElement());
	}

	public String getAuthor(String authorQuery, Row row) {
		return scrappingProvider.getAuthor(authorQuery, row.convertToElement());
	}

	public String getPrice(String priceQuery, Row row) {
		return scrappingProvider.getPrice(priceQuery, row.convertToElement());
	}

	public String getPromoDetails(String promoDetailsQuery, Row row) {
		return scrappingProvider.getPromoDetails(promoDetailsQuery, row.convertToElement());
	}
}
