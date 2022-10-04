package s22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s22.Bookstore.domain.ApplicationUser;
import s22.Bookstore.domain.ApplicationUserRepository;
import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, ApplicationUserRepository applicationUserRepository) {
		return (args) -> {
			
			log.info("Users");
			applicationUserRepository.save(new ApplicationUser("Maija", "Meikäläinen", "USER", "user",
					"$2a$10$57kg9OGuj44FPQyaps/fvOHmP845GQHIFdjphY4ILU/LoF1sVnQPS"));
			applicationUserRepository.save(new ApplicationUser("Elina", "Rouvinen", "ADMIN", "admin",
					"$2a$10$7DJe9e5TiHD5hHpm029KKeVXE/waBITs/9ykEd1qhEfZZbaRVkRiW"));

			log.info("Categories");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Science fiction"));
			crepository.save(new Category("Thriller"));
			
			log.info("New books");
			repository.save(new Book("Kaiken käsikirja", "Esko Valtaoja", 2012, "978-952-5985-05-4", 15.99, crepository.findByName("Science").get(0)));
			repository.save(new Book("Informaatio", "Hans Christian von Bayeyer", 2005, "952-5202-86-0", 4.99, crepository.findByName("Science").get(0)));
			repository.save(new Book("Maria Magdaleenan arvoitus", "Laurence Gardner", 2005, "13978-951-9107-52-3", 9.99, crepository.findByName("History").get(0)));
			repository.save(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", 2005, "178-931-1007-61-8", 9.99, crepository.findByName("Thriller").get(0)));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
