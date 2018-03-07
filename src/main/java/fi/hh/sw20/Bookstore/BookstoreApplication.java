package fi.hh.sw20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.hh.sw20.Bookstore.domain.User;
import fi.hh.sw20.Bookstore.domain.Book;
import fi.hh.sw20.Bookstore.domain.BookRepository;
import fi.hh.sw20.Bookstore.domain.Category;
import fi.hh.sw20.Bookstore.domain.CategoryRepository;
import fi.hh.sw20.Bookstore.domain.UserRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of new interesting books");
			categoryRepository.save(new Category("Drama"));
			categoryRepository.save(new Category("Fantasy"));
			categoryRepository.save(new Category("Science fiction"));
			
			bookRepository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21",0.0, categoryRepository.findByName("Drama").get(0)));
			bookRepository.save(new Book("Animal Farm", "George Orwell", 1945, "2212343-5",0.0, categoryRepository.findByName("Drama").get(0)));	
			
			
			String adminPassword= "salasanaAdmin";
			String userPassword= "salasana";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String adminPwd = passwordEncoder.encode(adminPassword);
			String userPwd = passwordEncoder.encode(userPassword);
			
			//user = salasana, admin = salasanaAdmin
			User userUser = new User("user", userPwd,"user@bookstore", "USER");
			User userAdmin = new User("admin", adminPwd,"admin@bookstore", "ADMIN");
			userRepository.save(userUser);
			userRepository.save(userAdmin);
			
			log.info("fetch all students");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
