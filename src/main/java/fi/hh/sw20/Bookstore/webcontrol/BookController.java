package fi.hh.sw20.Bookstore.webcontrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import fi.hh.sw20.Bookstore.domain.Book;
import fi.hh.sw20.Bookstore.domain.BookRepository;
import fi.hh.sw20.Bookstore.domain.CategoryRepository;



@Controller
public class BookController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String books(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}	
	
	@RequestMapping(value = "/addbook")
    public String addBookModel(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("category", categoryRepository.findAll());
        return "addbook";
    }
	
	@RequestMapping(value="/addbook", method=RequestMethod.POST)
	public String addBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value="/editbook/{id}", method=RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book", bookRepository.findOne(bookId));
    	model.addAttribute("category", categoryRepository.findAll());
        return "editbook";
    }
	
	@RequestMapping(value="/editbook/editbook", method=RequestMethod.POST)
	public String editBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) bookRepository.findAll();		
	}
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long id) {
		return bookRepository.findOne(id);
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.delete(bookId);
        return "redirect:/booklist";
    }
}
