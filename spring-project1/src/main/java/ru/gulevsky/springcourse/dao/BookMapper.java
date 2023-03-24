package ru.gulevsky.springcourse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.gulevsky.springcourse.models.Book;

public class BookMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setPid(rs.getInt("pid"));
        
        return book;
    }
}
