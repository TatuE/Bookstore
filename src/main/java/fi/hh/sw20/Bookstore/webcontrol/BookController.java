package fi.hh.sw20.Bookstore.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hh.sw20.Bookstore.domain.BookRepository;



@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String books(Model model) {
		model.addAttribute("book", bookRepository.findAll());
		return "booklist";
	}

}
