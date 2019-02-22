package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JSoupScrapper implements ScrappingProvider{

	Document document = null;

	@Override
	public void connect(String url) throws IOException {
		this.document = Jsoup.connect(url).get();
		if(document == null)
			throw new NullPointerException("Page"+url+"might not have been initialized.");
	}

	@Override
	public Elements getTableRows(String tableQuery) {
		return document.select(tableQuery);
	}

	@Override
	public String getTitle(String titleQuery, Element row) {
		return row.select(titleQuery).text();
	}

	@Override
	public String getAuthor(String authorQuery, Element row) {
		return row.select(authorQuery).text();
	}

	@Override
	public String getPrice(String priceQuery, Element row) {
		return row.select(priceQuery).text();
	}

	@Override
	public String getPromoDetails(String promoDetailsQuery, Element row) {
		return row.select(promoDetailsQuery).text();
	}


}