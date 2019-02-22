package pl.michalak.adam.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class TaniaKsiazkaScrapper implements PageScrapper {

	Set<ScrappedBook> scrappedBooks;

	@Override
	public Set<ScrappedBook> scrapData() {
		scrappedBooks = new HashSet<>();
		Document document = null;
		try {
			document = Jsoup.connect(TaniaKsiazkaQueries.URL.getQuery()).get();
		} catch (IOException e) {


		}
		if(document == null)
			throw new NullPointerException("Page"+TaniaKsiazkaQueries.URL.toString()+"might not have been initialized.");
		for (Element row : document.select(TaniaKsiazkaQueries.TABLE.getQuery())) {
			String title = row.select(TaniaKsiazkaQueries.TITLEROW.getQuery()).text();
			String author = row.select(TaniaKsiazkaQueries.AUTHORROW.getQuery()).text();
			double price = Double.parseDouble(row.select(TaniaKsiazkaQueries.PRICEROW.getQuery()).text());
			String promoDetails = row.select(TaniaKsiazkaQueries.PROMODETAILSROW.getQuery()).text();
			scrappedBooks.add(ScrappedBook.BookBuilder.create(title).setAuthor(author).setPrice(price).setPromoDetails(promoDetails).build());
		}
		return scrappedBooks;
	}
}
