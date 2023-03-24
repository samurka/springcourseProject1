package ru.gulevsky.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.gulevsky.springcourse.dao.BookDAO;
import ru.gulevsky.springcourse.models.Book;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;
    
    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
                        
    }

}
