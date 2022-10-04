package s22.Bookstore.web;

import javax.validation.Valid;

//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;
import s22.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Show booklist
	@RequestMapping(value= {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "bookList";
	}
	
	// Add new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addbook")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	// Save new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Book book, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			System.out.println("Some error happened.");
			model.addAttribute("categories", crepository.findAll());
			return "addbook";
		}
		repository.save(book);
		return "redirect:booklist";
	}
	
	// Delete book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){ 
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// Edit book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model){ 
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
//	// REST-service to get all books
//	@RequestMapping(value="/books", method = RequestMethod.GET)
//	//public @ResponseBody List<Book> bookListRest() {	
//        return (List<Book>) repository.findAll();
//    }    
//
//	// REST-service to get book by id
//    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
//    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {	
//    	return repository.findById(id);
//    } 
 
}