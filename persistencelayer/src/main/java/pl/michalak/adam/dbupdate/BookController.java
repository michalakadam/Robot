package pl.michalak.adam.dbupdate;

import org.springframework.beans.factory.annotation.Autowired;
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
}