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
import java.util.concurrent.atomic.AtomicInteger;

class TaniaKsiazkaScrapper implements PageScrapper {

	private AtomicInteger pageNumber;
	private Set<Book> scrappedBooks;

	TaniaKsiazkaScrapper(){
		this.pageNumber = new AtomicInteger(0);
		this.scrappedBooks = new HashSet<>();
	}

	@Override
	public Set<Book> scrapData() {
		ExecutorService executorService = Executors.newFixedThreadPool(8); //8 is a number of processors on server machine -> no need for running more threads
		while(pageNumber.get() < 1490) { //as of February 2019 there are 1497 pages and accessing page number 1498 returns redirects back to page 1497 -> safe solution.
			executorService.execute(() -> {
				ScrapperAPI scrapper = new ScrapperAPI(new JSoupScrapper());
				connectToNextPage(scrapper);
				getBooksDataFromPage(scrapper);
			});
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return scrappedBooks;
	}

	private void connectToNextPage(ScrapperAPI scrapper){
		pageNumber.incrementAndGet();
		try {
			scrapper.connect(getURLOfNextPage());
		} catch (IOException e) {
			System.err.println("Could not connect to " + getURLOfNextPage());
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
		return new StringBuffer().append(TaniaKsiazkaQueries.URL.getQuery()).append("/page-").append(pageNumber).toString();
	}

	private List<Row> getTableWithRows(ScrapperAPI scrapper){
		return scrapper.getTableRows(TaniaKsiazkaQueries.TABLE.getQuery());
	}

	private String getBookTitle(ScrapperAPI scrapper, Row row){
		return scrapper.getTitle(TaniaKsiazkaQueries.TITLEROW.getQuery(), row);
	}

	private String getBookAuthor(ScrapperAPI scrapper, Row row){
		return scrapper.getAuthor(TaniaKsiazkaQueries.AUTHORROW.getQuery(), row);
	}

	private double getBookPrice(ScrapperAPI scrapper, Row row){
		return DataFormattingHelper.parseStringToDouble(scrapper.getPrice(TaniaKsiazkaQueries.PRICEROW.getQuery(), row));
	}

	private String getPromoDetails(ScrapperAPI scrapper, Row row){
		return scrapper.getPromoDetails(TaniaKsiazkaQueries.PROMODETAILSROW.getQuery(), row);
	}

}
