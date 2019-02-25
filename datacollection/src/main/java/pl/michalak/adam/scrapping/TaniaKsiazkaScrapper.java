package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.Row;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TaniaKsiazkaScrapper implements PageScrapper {

	private ScrapperAPI scrapper;
	private int pageNumber;
	private List<Book> scrappedBooks;

	TaniaKsiazkaScrapper(ScrapperAPI scrapper){
		this.scrapper = scrapper;
		this.pageNumber = 0;
		this.scrappedBooks = new ArrayList<>();
	}

	@Override
	public List<Book> scrapData() {
		//TODO: this has to be done concurrently taking into account amount of data scrapped
		while(pageNumber < 1500) { //as of February 2019 there are 1497 pages and accessing page number 1498 returns redirects back to page 1497 -> safe solution.
			connectToPage();
			getBooksDataFromPage();
		}
		return scrappedBooks;
	}

	private void connectToPage(){
		pageNumber++;
		try {
			scrapper.connect(getURLOfNextPage());
		} catch (IOException e) {
			System.err.println("Could not connect to " + TaniaKsiazkaQueries.URL);
		}

	}

	private void getBooksDataFromPage(){
		for (Row row : getTableWithRows()) {
			scrappedBooks.add(ScrappedBook.BookBuilder
				.create(getBookTitle(row))
				.setAuthor(getBookAuthor(row))
				.setPrice(getBookPrice(row))
				.setPromoDetails(getPromoDetails(row))
				.build());
		}
	}

	private String getURLOfNextPage(){
		return new StringBuffer().append(TaniaKsiazkaQueries.URL.getQuery()).append("/page-").append(pageNumber).toString();
	}

	private List<Row> getTableWithRows(){
		return scrapper.getTableRows(TaniaKsiazkaQueries.TABLE.getQuery());
	}

	private String getBookTitle(Row row){
		return scrapper.getTitle(TaniaKsiazkaQueries.TITLEROW.getQuery(), row);
	}

	private String getBookAuthor(Row row){
		return scrapper.getAuthor(TaniaKsiazkaQueries.AUTHORROW.getQuery(), row);
	}

	private double getBookPrice(Row row){
		return DataFormattingHelper.parseStringToDouble(scrapper.getPrice(TaniaKsiazkaQueries.PRICEROW.getQuery(), row));
	}

	private String getPromoDetails(Row row){
		return scrapper.getPromoDetails(TaniaKsiazkaQueries.PROMODETAILSROW.getQuery(), row);
	}

}
