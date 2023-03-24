package ru.gulevsky.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {
    private int id;
    private int pid;
    
    @NotEmpty(message = "Name should not be empty")
    private String name;
    
    @NotEmpty(message = "Author should not be empty")
    @Pattern(regexp = "([A-Z][a-z]+\\s?){2}", message = "Name should be in format: Ivanov Ivan")
    private String author;
    
    @Min(value = 1900, message = "Year should be in the range 1900-2010")
    @Max(value = 2010, message = "Year should be in the range 1900-2010")
    private int year;
    
    public Book(int id, String name, String author, int year, int pid) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.pid = pid;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
