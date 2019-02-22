package pl.michalak.adam.scrapping;

import org.jsoup.nodes.Element;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class CzytamPLScrapper implements PageScrapper {
	private ScrapperAPI scrapper;

	CzytamPLScrapper(ScrapperAPI scrapper){
		this.scrapper = scrapper;
	}

	@Override
	public Set<ScrappedBook> scrapData() {
		Set<ScrappedBook> scrappedBooks = new HashSet<>();

		try {
			scrapper.connect(CzytamPLQueries.URL.getQuery());
		} catch (IOException e) {
			System.err.println("Could not connect to "+TaniaKsiazkaQueries.URL);
		}
		for (Element row : scrapper.getTableRows(CzytamPLQueries.TABLE.getQuery())) {
			String title = scrapper.getTitle(CzytamPLQueries.TITLEROW.getQuery(), row);
			String author = scrapper.getAuthor(CzytamPLQueries.AUTHORROW.getQuery(), row);
			double price = Double.parseDouble(scrapper.getPrice(CzytamPLQueries.PRICEROW.getQuery(), row));
			double previousPrice = Double.parseDouble(scrapper.getPromoDetails(CzytamPLQueries.PROMODETAILSROW.getQuery(), row));
			scrappedBooks.add(ScrappedBook.BookBuilder.create(title).setAuthor(author).setPrice(price).setPromoDetails(getPromoInPercents(price, previousPrice)).build());
		}
		return scrappedBooks;
	}

	private String getPromoInPercents(double price, double previousPrice){
		int promoInPercents = (int) (price/previousPrice);
		return "-"+promoInPercents+"%";
	}
}