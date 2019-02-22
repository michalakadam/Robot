package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

public interface ScrappingProvider {
	void connect(String url) throws IOException;
	Elements getTableRows(String tableQuery);
	String getTitle(String titleQuery, Element row);
	String getAuthor(String authorQuery, Element row);
	String getPrice(String priceQuery, Element row);
	String getPromoDetails(String promoDetailsQuery, Element row);
}
