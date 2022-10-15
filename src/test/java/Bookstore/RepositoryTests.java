package Bookstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;
import Bookstore.domain.Category;
import Bookstore.domain.CategoryRepository;

@DataJpaTest
class RepositoryTests {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void findBooksCategoryShouldReturnOneCategory() {

		Category category = bookRepository.findById((long) 6).get().getCategory();
		assertEquals(category.getName(), "Adventure");
	}

	@Test
	public void findByCategoryNameShouldReturnBooks() {
		
		List<Book> books = bookRepository.findByCategoryName("Fantasy");
		assertEquals(books.size(), 2);
	}
	
	@Test
	public void insertNewBook() {
		Book book = new Book("kirja5", "kirjailija5", 2018, "040303046789", 9, categoryRepository.findByName("Fantasy").get(0));
		bookRepository.save(book);
		assertNotNull(book.getId());
	}
	
	@Test
	public void deleteBookShouldReturnRightAmount() {
		bookRepository.deleteById((long) 4);
		assertEquals(bookRepository.count(), 3);
	}
	
}
