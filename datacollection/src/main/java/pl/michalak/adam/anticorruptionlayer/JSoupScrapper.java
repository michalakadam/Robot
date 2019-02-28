package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pl.michalak.adam.scrapping.ScrapperQuery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A {@link ScrappedDataProvider} using org.jsoup.Jsoup library to scrap data from a webpage.
 * @see ScrappedDataProvider
 */
public class JSoupScrapper implements ScrappedDataProvider {

	private Document document = null;

	@Override
	public void connect(URL url) throws IOException {
		this.document = Jsoup.connect(url.toString()).get();
		if(document == null)
			throw new NullPointerException("Page"+url+"might not have been initialized.");
	}

	@Override
	public List<Row> getTableRows(ScrapperQuery tableQuery) {
		List<Row> listOfRows = new ArrayList<>();
		for(Element jsoupElement : document.select(tableQuery.toString())){
			listOfRows.add(new Row(jsoupElement));
		}
		return listOfRows;
	}

	@Override
	public String getTitle(ScrapperQuery titleQuery, Element row) {
		return row.select(titleQuery.toString()).text();
	}

	@Override
	public String getAuthor(ScrapperQuery authorQuery, Element row) {
		return row.select(authorQuery.toString()).text();
	}

	@Override
	public String getPrice(ScrapperQuery priceQuery, Element row) {
		return row.select(priceQuery.toString()).text();
	}

	@Override
	public String getPromoDetails(ScrapperQuery promoDetailsQuery, Element row) {
		return row.select(promoDetailsQuery.toString()).text();
	}
}
