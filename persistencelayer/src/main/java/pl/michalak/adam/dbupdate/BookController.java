package pl.michalak.adam.dbupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/all")
class BookController {

	@Autowired
	BookRepository bookRepository;


	@GetMapping
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

	@GetMapping("/sortbyreduction")
	public Iterable<BookEntity> sortByReductionDesc(){
		Sort sort = Sort.by(Sort.Order.desc("promo"));
		return bookRepository.findAll(sort);
	}
}