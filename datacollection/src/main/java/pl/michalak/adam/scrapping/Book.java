package pl.michalak.adam.scrapping;

/**
 * Defines POJO that represents a book in the bookstore.
 */
public interface Book {
	String getTitle();
	String getAuthor();
	double getPrice();
	String getPromoDetails();
}
