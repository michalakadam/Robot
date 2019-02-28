package pl.michalak.adam.anticorruptionlayer;

import pl.michalak.adam.scrapping.ScrapperQuery;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Wrapper for scrapping library. Provides methods
 * that are used by bookstore scrappers to obtain book information.
 */
public class ScrapperAPI {

	ScrappedDataProvider scrappedDataProvider;

	public ScrapperAPI(ScrappedDataProvider scrappedDataProvider){
		this.scrappedDataProvider = scrappedDataProvider;
	}

	/**
	 * Fetches and parses a HTML page.
	 * @param url defines address of a webpage. The protocol must be HTTP or HTTPS.
	 * @throws IOException if obtaining HTML source code is not possible.
	 */
	public void connect(URL url) throws IOException {
		scrappedDataProvider.connect(url);
	}

	/**
	 * Finds elements that match the CSS query, with this element as the starting context.
	 * Matched elements may include this element, or any of its children.
	 * @param tableQuery CSS-like query that defines location of a table in HTML source code.
	 * @return collection of Rows where book information is stored.
	 */
	public List<Row> getTableRows(ScrapperQuery tableQuery){
		return scrappedDataProvider.getTableRows(tableQuery);
	}

	public String getTitle(ScrapperQuery titleQuery, Row row) {
		return scrappedDataProvider.getTitle(titleQuery, row.convertToElement());
	}

	public String getAuthor(ScrapperQuery authorQuery, Row row) {
		return scrappedDataProvider.getAuthor(authorQuery, row.convertToElement());
	}

	public String getPrice(ScrapperQuery priceQuery, Row row) {
		return scrappedDataProvider.getPrice(priceQuery, row.convertToElement());
	}

	public String getPromoDetails(ScrapperQuery promoDetailsQuery, Row row) {
		return scrappedDataProvider.getPromoDetails(promoDetailsQuery, row.convertToElement());
	}
}
