package fi.hh.sw20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.sw20.Bookstore.domain.Book;
import fi.hh.sw20.Bookstore.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;	
	
	 @Test
	    public void findByAuthorShouldReturnBook() {
	        List<Book> books = repository.findByAuthor("Ernest Hemingway");
	        
	        assertThat(books).hasSize(1);
	        assertThat(books.get(0).getTitle()).isEqualTo("A Farewell to Arms");
	    }
	    
	    @Test
	    public void createNewBook() {
	    	Book book = new Book("A Farewell to Arms 2, in the beginning", "Ernest Hemingway", 1930, "1232323-22",0.0);
	    	repository.save(book);
	    	assertThat(book.getId()).isNotNull();
	    	List<Book> books = repository.findByTitle("A Farewell to Arms 2, in the beginning");
	    	assertThat(books).hasSize(1);
	    }
	    
	    @Test
	    public void deleteBook() {
	    	Book book = new Book("A Farewell to Arms 3, in the future", "Ernest Hemingway", 1931, "1232323-23",0.0);
	    	repository.save(book);	    	
	    	repository.delete(book);	    	
	    	List<Book> books = repository.findByTitle("A Farewell to Arms 3, in the future");	        
	        assertThat(books).hasSize(0);
	    }
}
