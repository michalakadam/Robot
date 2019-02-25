package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.Row;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class TaniaKsiazkaScrapper implements PageScrapper {

	private ScrapperAPI scrapper;
	private int pageNumber;

	TaniaKsiazkaScrapper(ScrapperAPI scrapper){
		this.scrapper = scrapper;
		this.pageNumber = 0;
	}

	@Override
	public Set<ScrappedBook> scrapData() {
		Set<ScrappedBook> scrappedBooks = new HashSet<>();
		//TODO: this has to be done concurrently taking into account amount of data scrapped
		while(pageNumber < 1500) { //as of February 2019 there are 1497 pages and accessing page number 1498 returns redirects back to page 1497 -> safe solution.
			pageNumber++;
			try {
				scrapper.connect(getURLOfNextPage());
			} catch (IOException e) {
				System.err.println("Could not connect to " + TaniaKsiazkaQueries.URL);
			}
			for (Row row : scrapper.getTableRows(TaniaKsiazkaQueries.TABLE.getQuery())) {
				String title = scrapper.getTitle(TaniaKsiazkaQueries.TITLEROW.getQuery(), row);
				String author = scrapper.getAuthor(TaniaKsiazkaQueries.AUTHORROW.getQuery(), row);
				double price = DataFormattingHelper.parseStringToDouble(scrapper.getPrice(TaniaKsiazkaQueries.PRICEROW.getQuery(), row));
				String promoDetails = scrapper.getPromoDetails(TaniaKsiazkaQueries.PROMODETAILSROW.getQuery(), row);
				scrappedBooks.add(ScrappedBook.BookBuilder.create(title).setAuthor(author).setPrice(price).setPromoDetails(promoDetails).build());
			}
		}
		return scrappedBooks;
	}

	private String getURLOfNextPage(){
		return new StringBuffer().append(TaniaKsiazkaQueries.URL.getQuery()).append("/page-").append(pageNumber).toString();
	}
}
