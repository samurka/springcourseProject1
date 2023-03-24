package ru.gulevsky.springcourse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gulevsky.springcourse.dao.BookDAO;
import ru.gulevsky.springcourse.dao.PersonDAO;
import ru.gulevsky.springcourse.models.Book;
import ru.gulevsky.springcourse.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, BookValidator bookValidator, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }
    
    @GetMapping()
    public String callListForm(Model model) {
        model.addAttribute("books", bookDAO.getList());
        return "books/listForm";
    }
    
    @GetMapping("/{id}")
    public String callUnitForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getById(id));
        model.addAttribute("optionalPerson", personDAO.getByBookId(id));    
        model.addAttribute("people", personDAO.getList());
        return "books/unitForm";
    }
    
    @GetMapping("/new")
    public String callNewForm(@ModelAttribute("book") Book book) {
        return "books/newForm";
    }
    
    @PostMapping()
    public String checkAddBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
                
        if(bindingResult.hasErrors()) {
            return "books/newForm";
        }
        bookDAO.addBook(book);
        return "redirect:/books";
    }
    
    @GetMapping("/{id}/edit")
    public String callEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getById(id));
        return "books/editForm";
    }
    
    @PatchMapping("/{id}")
    public String checkUpdateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        
        if(bindingResult.hasErrors()) {
            return "books/editForm";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }
    
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
    
    @PatchMapping("/{id}/checkout")
    public String checkoutBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.checkoutBook(id, book.getPid());
        return "redirect:/books/{id}";
    }
    
    @PatchMapping("/{id}/accept")
    public String acceptBook(@PathVariable("id") int id) {
        bookDAO.acceptBook(id);
        return "redirect:/books/{id}";
    }
    
}
