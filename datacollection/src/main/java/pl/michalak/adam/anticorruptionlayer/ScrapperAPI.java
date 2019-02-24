package pl.michalak.adam.anticorruptionlayer;

import java.io.IOException;
import java.util.List;

public class ScrapperAPI {

	ScrappingProvider scrappingProvider;

	public ScrapperAPI(ScrappingProvider scrappingProvider){
		this.scrappingProvider = scrappingProvider;
	}

	public void connect(String url) throws IOException {
		scrappingProvider.connect(url);
	}

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
