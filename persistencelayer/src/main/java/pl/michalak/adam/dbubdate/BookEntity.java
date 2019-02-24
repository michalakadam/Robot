package pl.michalak.adam.dbubdate;

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
	@Column
	private String title;
	@Column
	private String author;
	@Column
	private double price;
	@Column
	private String promoDetails;

	BookEntity(){}

	public BookEntity(Book book){
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.promoDetails = book.getPromoDetails();
	}
}
