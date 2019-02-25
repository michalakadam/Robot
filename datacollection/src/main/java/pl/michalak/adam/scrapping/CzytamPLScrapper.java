package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.Row;
import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CzytamPLScrapper implements PageScrapper {
	private ScrapperAPI scrapper;
	private int pageNumber;
	private final List<Book> scrappedBooks;

	CzytamPLScrapper(ScrapperAPI scrapper){
		this.scrapper = scrapper;
		this.pageNumber = 0;
		scrappedBooks = new ArrayList<>();
	}

	@Override
	public List<Book> scrapData() {
		while(pageNumber < 10) { //as of February 2019 there are 7 pages and accessing page number 8 returns responseCode 200 -> safe solution.
			connectToNextPage();
			getBooksDataFromPage();
		}
		return scrappedBooks;
	}

	private void connectToNextPage() {
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
		return CzytamPLQueries.URL.getQuery().replace("25642-1", "25642-"+pageNumber);
	}

	private List<Row> getTableWithRows(){
		return scrapper.getTableRows(CzytamPLQueries.TABLE.getQuery());
	}

	private String getBookTitle(Row row){
		return scrapper.getTitle(CzytamPLQueries.TITLEROW.getQuery(), row);
	}

	private String getBookAuthor(Row row){
		return scrapper.getAuthor(CzytamPLQueries.AUTHORROW.getQuery(), row);
	}

	private double getBookPrice(Row row){
		return DataFormattingHelper.parseStringToDouble(scrapper.getPrice(CzytamPLQueries.PRICEROW.getQuery(), row).substring(9));
	}

	private String getPromoDetails(Row row){
		double price = getBookPrice(row);
		double previousPrice = DataFormattingHelper.parseStringToDouble(scrapper.getPromoDetails(CzytamPLQueries.PROMODETAILSROW.getQuery(), row));
		return DataFormattingHelper.getPromoInPercents(price, previousPrice);
	}
}
