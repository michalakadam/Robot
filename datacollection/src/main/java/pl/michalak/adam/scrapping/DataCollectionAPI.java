package pl.michalak.adam.scrapping;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This API provides a method for books information update.
 */
public class DataCollectionAPI {

	public DataCollectionAPI(){
	}

	/**
	 * Scraps supported bookstores for books which price is reduced.
	 * Warning: information is unfiltered, ie. it contains information
	 * that may be already present in the database.
	 * @return Set of all books in supported bookstores which price is reduced.
	 */
	public Set<Book> updateData(){
		Set<Book> booksScrapped = new HashSet<>();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<?>> tasks = new LinkedList<>();
		for(PageScrapper pageScrapper : bookStoresAvailable()){
			tasks.add(executorService.submit(() -> booksScrapped.addAll(pageScrapper.scrapData())));
		}
		for(Future<?> currentTask : tasks){
			try {
				currentTask.get();
			} catch (InterruptedException | ExecutionException e) {
				System.err.println("Future task interrupted during execution.");;
			}
		}
		executorService.shutdown();
		System.out.println("Scrapping done! Now you can browse the results.");
		return booksScrapped;
	}

	private List<? extends PageScrapper> bookStoresAvailable(){
		List<PageScrapper> bookStores = new ArrayList<>();
		for(SupportedBookStore bookStore : SupportedBookStore.values()){
			bookStores.add(ScrapperFactory.getPageScrapper(bookStore.getUrl()));
		}
		return bookStores;
	}
}
