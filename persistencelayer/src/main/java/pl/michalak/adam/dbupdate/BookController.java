package pl.michalak.adam.dbupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
class BookController {

	@Autowired
	BookRepository bookRepository;


	@GetMapping
	public Iterable<BookEntity> printAllPeople(){
		return bookRepository.findAll();
	}

	@GetMapping("/menu")
	public String showAvailablePages(){
		return new StringBuffer()
			                    .append("Welcome to reduced books database!\n\n")
								.append("See all books -> http://10.30.0.61:8081/books/all\n")
								.append("See all books sorted alphabetically by title -> http://10.30.0.61:8081/books/all/sortbytitle\n")
								.append("See all books sorted alphabetically by author -> http://10.30.0.61:8081/books/all/sortbyauthor\n")
								.append("See all books sorted in ascending order by price  -> http://10.30.0.61:8081/books/all/sortbyprice\n")
								.append("See all books sorted in ascending order by price reduction -> http://10.30.0.61:8081/books/all/sortbyreduction\n")
								.toString();
	}

	@GetMapping("/all/sortbytitle")
	public Iterable<BookEntity> sortByTitleAsc(){
		Sort sort = Sort.by(Sort.Order.asc("title"));
		return bookRepository.findAll(sort);
	}

	@GetMapping("/all/sortbyauthor")
	public Iterable<BookEntity> sortByAuthorAsc(){
		Sort sort = Sort.by(Sort.Order.asc("author"));
		return bookRepository.findAll(sort);
	}

	@GetMapping("/all/sortbyprice")
	public Iterable<BookEntity> sortByPriceAsc(){
		Sort sort = Sort.by(Sort.Order.asc("price"));
		return bookRepository.findAll(sort);
	}

	@GetMapping("/all/sortbyreduction")
	public Iterable<BookEntity> sortByReductionDesc(){
		Sort sort = Sort.by(Sort.Order.desc("promo"));
		return bookRepository.findAll(sort);
	}
}