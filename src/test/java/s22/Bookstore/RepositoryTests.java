package s22.Bookstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;

@DataJpaTest
class RepositoryTests {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void findBookByTitle() {
		List<Book> books = bookRepository.findByTitle("Kaiken käsikirja");
		assertEquals(books.size(), 1);
		assertEquals(books.get(0).getAuthor(), "Esko Valtaoja");
	}
	
	@Test
	public void findCategoryByName() {
		List<Category> categories = categoryRepository.findByName("History");
		// How many books is in the category
		assertEquals(categories.size(), 1); 
	}
	
	@Test
	public void findBooksByCategory() {
		List<Book> books = bookRepository.findByCategoryName("Science");
		// How many books is in the category
		assertEquals(books.size(), 2); 
	}

	@Test
	public void insertNewBook() {
		Book book = new Book("Testikirja", "Testikirjoittaja", 2022, "248-7452-85-85-3", 22.99, categoryRepository.findByName("Science").get(0));
		bookRepository.save(book);
		assertNotNull(book.getId());
	}
	
//	@Test
//	public void deleteNewBook() {
//		List<Book> books = bookRepository.findByTitle("Testikirja");
//		Book book = books.get(0);
//		bookRepository.delete(book);
//		List<Book> newBooks = bookRepository.findByTitle("Testikirja");
//		assertEquals(newBooks.size(), 0);
//	}

}