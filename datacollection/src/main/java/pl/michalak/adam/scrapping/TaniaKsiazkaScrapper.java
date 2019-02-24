package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.Row;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class TaniaKsiazkaScrapper implements PageScrapper {

	private ScrapperAPI scrapper;

	TaniaKsiazkaScrapper(ScrapperAPI scrapper){
		this.scrapper = scrapper;
	}

	@Override
	public Set<ScrappedBook> scrapData() {
		Set<ScrappedBook> scrappedBooks = new HashSet<>();
		try {
			scrapper.connect(TaniaKsiazkaQueries.URL.getQuery());
		} catch (IOException e) {
			System.err.println("Could not connect to "+TaniaKsiazkaQueries.URL);
		}
		for (Row row : scrapper.getTableRows(TaniaKsiazkaQueries.TABLE.getQuery())) {
			String title = scrapper.getTitle(TaniaKsiazkaQueries.TITLEROW.getQuery(), row);
			String author = scrapper.getAuthor(TaniaKsiazkaQueries.AUTHORROW.getQuery(), row);
			double price = DataFormatter.parseStringToDouble(scrapper.getPrice(TaniaKsiazkaQueries.PRICEROW.getQuery(), row));
			String promoDetails = scrapper.getPromoDetails(TaniaKsiazkaQueries.PROMODETAILSROW.getQuery(), row);
			scrappedBooks.add(ScrappedBook.BookBuilder.create(title).setAuthor(author).setPrice(price).setPromoDetails(promoDetails).build());
		}
		return scrappedBooks;
	}
}
