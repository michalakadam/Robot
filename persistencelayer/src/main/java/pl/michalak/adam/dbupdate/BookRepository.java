package pl.michalak.adam.dbupdate;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

interface BookRepository extends CrudRepository<BookEntity, Integer> {
	Iterable<BookEntity> findAll(Sort sort);
}