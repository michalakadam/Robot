package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.JSoupScrapper;
import pl.michalak.adam.anticorruptionlayer.Row;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

class TaniaKsiazkaScrapper implements PageScrapper {

	private AtomicInteger pageNumber;
	Queue<Book> scrappedBooks;


	TaniaKsiazkaScrapper(){
		this.pageNumber = new AtomicInteger(0);
		this.scrappedBooks = new LinkedBlockingDeque<>();
	}

	@Override
	public Queue<Book> scrapData() {
		ExecutorService executorService = Executors.newFixedThreadPool(8); //8 is a number of processors on server machine -> no need for running more threads
		while(pageNumber.get() < 250) { //as of February 2019 there are 1497 pages.
			// TODO: when the number of pages > 250, scrapping takes long time and some pages are not loaded.
			// hypothesis: webpage prohibits bot access to its contents. Solution: slow down threads or delegate obtaining data to several servers with different IP.
			executorService.execute(() -> {
				ScrapperAPI scrapper = new ScrapperAPI(new JSoupScrapper());
				connectToNextPage(scrapper);
				getBooksDataFromPage(scrapper);
			});
		}
		executorService.shutdown();
		return scrappedBooks;
	}

	private void connectToNextPage(ScrapperAPI scrapper){
		pageNumber.incrementAndGet();
		try {
			scrapper.connect(getURLOfCurrentPage());
		} catch (IOException e) {
			System.err.println("Could not connect to " + getURLOfCurrentPage());
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

	private URL getURLOfCurrentPage() {
		try {
			return new URL(new StringBuffer().append(TaniaKsiazkaQueries.URL.getQuery()).append("/page-").append(pageNumber).toString());
		} catch (MalformedURLException e) {
			System.err.println("No protocol was specified or an unknown protol was found while parsing String to URL.");
		}
		return null;
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
