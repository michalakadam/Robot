package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.JSoupScrapper;
import pl.michalak.adam.anticorruptionlayer.Row;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CzytamPLScrapper implements PageScrapper {
	private int pageNumber;
	private final Set<Book> scrappedBooks;

	CzytamPLScrapper(){
		this.pageNumber = 0;
		scrappedBooks = new HashSet<>();
	}

	@Override
	public Set<Book> scrapData() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		while(pageNumber < 10) { //as of February 2019 there are 7 pages and accessing page number 8 returns responseCode 200 -> safe solution.
			executorService.execute(() -> {
				ScrapperAPI scrapper = new ScrapperAPI(new JSoupScrapper());
				connectToNextPage(scrapper);
				getBooksDataFromPage(scrapper);
			});
		}
		return scrappedBooks;
	}

	private void connectToNextPage(ScrapperAPI scrapper) {
		pageNumber++;
		try {
			scrapper.connect(getURLOfNextPage());
		} catch (IOException e) {
			System.err.println("Could not connect to " + TaniaKsiazkaQueries.URL);
		}

	}

	private void getBooksDataFromPage(ScrapperAPI scrapper){
		for (Row row : getTableWithRows(scrapper)) {
			scrappedBooks.add(ScrappedBook.BookBuilder
				.create(getBookTitle(scrapper, row))
				.setAuthor(getBookAuthor(scrapper, row))
				.setPrice(getBookPrice(scrapper, row))
				.setPromoDetails(getPromoDetails(scrapper, row))
				.build());
		}
	}


	private String getURLOfNextPage(){
		return CzytamPLQueries.URL.getQuery().replace("25642-1", "25642-"+pageNumber);
	}

	private List<Row> getTableWithRows(ScrapperAPI scrapper){
		return scrapper.getTableRows(CzytamPLQueries.TABLE.getQuery());
	}

	private String getBookTitle(ScrapperAPI scrapper, Row row){
		return scrapper.getTitle(CzytamPLQueries.TITLEROW.getQuery(), row);
	}

	private String getBookAuthor(ScrapperAPI scrapper, Row row){
		return scrapper.getAuthor(CzytamPLQueries.AUTHORROW.getQuery(), row);
	}

	private double getBookPrice(ScrapperAPI scrapper, Row row){
		return DataFormattingHelper.parseStringToDouble(scrapper.getPrice(CzytamPLQueries.PRICEROW.getQuery(), row).substring(9));
	}

	private String getPromoDetails(ScrapperAPI scrapper, Row row){
		double price = getBookPrice(scrapper, row);
		double previousPrice = DataFormattingHelper.parseStringToDouble(scrapper.getPromoDetails(CzytamPLQueries.PROMODETAILSROW.getQuery(), row));
		return DataFormattingHelper.getPromoInPercents(price, previousPrice);
	}
}
