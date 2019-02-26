package pl.michalak.adam.dbupdate;

import lombok.Data;
import pl.michalak.adam.scrapping.Book;

import javax.persistence.*;

@Entity
@Table(name = "Books")
@Data
class BookEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	private double price;
	private String promoDetails;

	BookEntity(){}

	BookEntity(Book book){
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.promoDetails = book.getPromoDetails();
	}
}
