package Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;
import Bookstore.domain.Category;
import Bookstore.domain.CategoryRepository;
import Bookstore.domain.ApplicationUser;
import Bookstore.domain.ApplicationUserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, ApplicationUserRepository urepository) {
		
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Horror"));
			
			repository.save(new Book("kirja1", "kirjailija1", 2008, "04030304", 10, crepository.findByName("Fantasy").get(0)));
			repository.save(new Book("kirja2", "kirjailija2", 2011, "0403030544", 12, crepository.findByName("Horror").get(0)));
			repository.save(new Book("kirja3", "kirjailija3", 2020, "04030304543", 15, crepository.findByName("Adventure").get(0)));
			repository.save(new Book("kirja4", "kirjailija4", 2018, "040303046789", 9, crepository.findByName("Fantasy").get(0)));
			
			ApplicationUser user1 = new ApplicationUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			ApplicationUser user2 = new ApplicationUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
