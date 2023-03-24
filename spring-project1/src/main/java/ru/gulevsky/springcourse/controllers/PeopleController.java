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
import ru.gulevsky.springcourse.models.Person;
import ru.gulevsky.springcourse.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final BookDAO  bookDAO;
    
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.bookDAO = bookDAO;
    }
    
    @GetMapping()
    public String callListForm(Model model) {
        model.addAttribute("people", personDAO.getList());
        return "people/listForm";
    }
    
    @GetMapping("/{id}")
    public String callUnitForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getById(id));
        model.addAttribute("takenBooks", bookDAO.getTakenBooksList(id));
        return "people/unitForm";
    }
    
    @GetMapping("/new")
    public String callNewForm(@ModelAttribute("person") Person person) {
        return "people/newForm";
    }
    
    @PostMapping()
    public String checkAddPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        
        if(bindingResult.hasErrors()) {
            return "people/newForm";
        }
        personDAO.addPerson(person);
        return "redirect:/people";
    }
    
    @GetMapping("/{id}/edit")
    public String callEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getById(id));
        return "people/editForm";
    }
    
    @PatchMapping("/{id}")
    public String checkUpdatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        
        if(bindingResult.hasErrors()) {
            return "people/editForm";
        }
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }
    
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
    
}
