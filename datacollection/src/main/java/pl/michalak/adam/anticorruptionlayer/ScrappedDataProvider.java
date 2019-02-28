package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.nodes.Element;
import pl.michalak.adam.scrapping.ScrapperQuery;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Provides methods necessary for book store scrappers to function properly.
 */
interface ScrappedDataProvider {
	//TODO: each method documentation. Why it returns list of rows? Why it takes URL?
	void connect(URL url) throws IOException;
	List<Row> getTableRows(ScrapperQuery tableQuery);
	String getTitle(ScrapperQuery titleQuery, Element row);
	String getAuthor(ScrapperQuery authorQuery, Element row);
	String getPrice(ScrapperQuery priceQuery, Element row);
	String getPromoDetails(ScrapperQuery promoDetailsQuery, Element row);
}
