package fi.hh.sw20.Bookstore.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hh.sw20.Bookstore.domain.Book;

@Controller
public class BookController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String books(Model model, Book book) {
		return "index";
	}

}
