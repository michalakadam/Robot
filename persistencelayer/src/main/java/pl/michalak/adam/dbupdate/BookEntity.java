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
	@Column(name = "Title")
	private String title;
	@Column(name = "Author")
	private String author;
	@Column(name = "Price")
	private double price;
	@Column(name = "Reduction")
	private String promoDetails;

	BookEntity(){}

	BookEntity(Book book){
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.promoDetails = book.getPromoDetails();
	}
}
