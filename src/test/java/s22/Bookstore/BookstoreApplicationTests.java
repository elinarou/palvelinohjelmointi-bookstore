package s22.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import s22.Bookstore.web.BookController;
import s22.Bookstore.web.BookRestController;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	BookController bookController;
	
	@Autowired
	BookRestController bookRestController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
	}

}
