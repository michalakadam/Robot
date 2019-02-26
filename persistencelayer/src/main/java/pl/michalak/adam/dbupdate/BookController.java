package pl.michalak.adam.dbupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/start")
	public String printMainMenu(){
		return "Welcome to reduced books database!";
	}

	@GetMapping("/all")
	public Iterable<BookEntity> printAllPeople(){
		return bookRepository.findAll();
	}

	@GetMapping("/sortbytitle")
	public Iterable<BookEntity> sortByTitleAsc(){
		Sort sort = Sort.by(Sort.Order.asc("title"));
		return bookRepository.findAll(sort);
	}

	@GetMapping("/sortbyauthor")
	public Iterable<BookEntity> sortByAuthorAsc(){
		Sort sort = Sort.by(Sort.Order.asc("author"));
		return bookRepository.findAll(sort);
	}

	@GetMapping("/sortbyprice")
	public Iterable<BookEntity> sortByPriceAsc(){
		Sort sort = Sort.by(Sort.Order.asc("price"));
		return bookRepository.findAll(sort);
	}
}