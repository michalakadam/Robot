package pl.michalak.adam.dbubdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.michalak.adam.scrapping.Book;
import pl.michalak.adam.scrapping.DataCollectionAPI;

import java.util.Set;

@SpringBootApplication
public class DBHandler implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(DBHandler.class);
	}

	@Override
	public void run(String... args) throws Exception {
		DataCollectionAPI dataCollectionAPI = new DataCollectionAPI();
		Set<Book> collectedBooks = dataCollectionAPI.updateData();
		for(Book book : collectedBooks)
			bookRepository.save(new BookEntity(book));
	}
}
