package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ScrapperAPI {

	ScrappingProvider scrappingProvider;

	public ScrapperAPI(ScrappingProvider scrappingProvider){
		this.scrappingProvider = scrappingProvider;
	}

	public void connect(String url) throws IOException {
		scrappingProvider.connect(url);
	}

	public Elements getTableRows(String tableQuery){
		return scrappingProvider.getTableRows(tableQuery);
	}

	public String getTitle(String titleQuery, Element row) {
		return scrappingProvider.getTitle(titleQuery, row);
	}

	public String getAuthor(String authorQuery, Element row) {
		return scrappingProvider.getAuthor(authorQuery, row);
	}

	public String getPrice(String priceQuery, Element row) {
		return scrappingProvider.getPrice(priceQuery, row);
	}

	public String getPromoDetails(String promoDetailsQuery, Element row) {
		return scrappingProvider.getPromoDetails(promoDetailsQuery, row);
	}
}
