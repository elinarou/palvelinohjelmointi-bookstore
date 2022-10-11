package s22.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {
	// find by title
	List<Book> findByTitle(String title);
	
	// find by category (category can have many books)
	List<Book> findByCategoryName(String name);
}
