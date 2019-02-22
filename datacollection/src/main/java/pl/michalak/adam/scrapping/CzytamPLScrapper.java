package pl.michalak.adam.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class CzytamPLScrapper implements PageScrapper {
	Set<ScrappedBook> scrappedBooks;
	@Override
	public Set<ScrappedBook> scrapData() {
		scrappedBooks = new HashSet<>();
		Document document = null;
		try {
			document = Jsoup.connect(CzytamPLQueries.URL.getQuery()).get();
		} catch (IOException e) {
			System.out.println("Could not connect to "+TaniaKsiazkaQueries.URL);
		}
		if(document == null)
			throw new NullPointerException("Page"+CzytamPLQueries.URL.toString()+"might not have been initialized.");
		for (Element row : document.select(CzytamPLQueries.TABLE.getQuery())) {
			String title = row.select(CzytamPLQueries.TITLEROW.getQuery()).text();
			String author = row.select(CzytamPLQueries.AUTHORROW.getQuery()).text();
			double price = Double.parseDouble(row.select(CzytamPLQueries.PRICEROW.getQuery()).text());
			double previousPrice = Double.parseDouble(row.select(CzytamPLQueries.PROMODETAILSROW.getQuery()).text());
			String promoDetails = getPromoInPercents(price, previousPrice);
			scrappedBooks.add(ScrappedBook.BookBuilder.create(title).setAuthor(author).setPrice(price).setPromoDetails(promoDetails).build());
		}
		return scrappedBooks;
	}

	private String getPromoInPercents(double price, double previousPrice){
		int promoInPercents = (int) (price/previousPrice);
		return "-"+promoInPercents+"%";
	}
}
