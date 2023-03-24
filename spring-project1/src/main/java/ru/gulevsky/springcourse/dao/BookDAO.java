package ru.gulevsky.springcourse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.gulevsky.springcourse.models.Book;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Book> getList() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }
    
    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[] {id}, new BookMapper())
                .stream().findAny().orElse(null);
    }
    
    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }
    
    public void updateBook(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    
    public List<Book> getTakenBooksList(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE pid=?", new Object[] {id}, new BookMapper());
    }
    
    public void acceptBook(int id) {
        jdbcTemplate.update("UPDATE book SET pid=null WHERE id=?", id);
    }
    
    public void checkoutBook(int id, int pid) {
        jdbcTemplate.update("UPDATE book SET pid=? WHERE id=?", pid, id);
    }
   

}
