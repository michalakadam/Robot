package pl.michalak.adam.scrapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataCollectionAPI {

	private List<? extends PageScrapper> bookStoresAvailable(){
		List<PageScrapper> bookStores = new ArrayList<>();
		for(SupportedBookStore bookStore : SupportedBookStore.values()){
			bookStores.add(ScrapperFactory.getPageScrapper(bookStore.getUrl()));
		}
		return bookStores;
	}

	public Set<? extends Book> updateData(){
		Set<ScrappedBook> booksScrapped = new HashSet<>();
		for(PageScrapper pageScrapper : bookStoresAvailable()){
			booksScrapped.addAll(pageScrapper.scrapData());
		}
		return booksScrapped;
	}
}
