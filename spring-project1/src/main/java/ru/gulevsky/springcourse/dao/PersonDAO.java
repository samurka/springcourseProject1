package ru.gulevsky.springcourse.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.gulevsky.springcourse.models.Person;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Person> getList() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }
    
    public boolean isPresentNameDuplication(Person person) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=? and id!=?", new Object[] {person.getName(), person.getId()}, new PersonMapper())
                .stream().findAny().isPresent();
    }
    
    public Optional<Person> getByBookId(int id) {
        return jdbcTemplate.query("SELECT person.id, person.name, person.year FROM book JOIN person ON person.id=book.pid WHERE book.id=?", new Object[] {id}, new PersonMapper())
                .stream().findAny();
    }
    
    public Person getById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[] {id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }
    
    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, year) VALUES(?, ?)", person.getName(), person.getYear());
    }
    
    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, year=? WHERE id=?", updatedPerson.getName(), updatedPerson.getYear(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
        
}
