package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * Provides methods necessary for book store scrappers to function properly.
 */
interface ScrappingProvider {
	void connect(String url) throws IOException;
	List<Row> getTableRows(String tableQuery);
	String getTitle(String titleQuery, Element row);
	String getAuthor(String authorQuery, Element row);
	String getPrice(String priceQuery, Element row);
	String getPromoDetails(String promoDetailsQuery, Element row);
}
