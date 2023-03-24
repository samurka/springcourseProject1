package ru.gulevsky.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;
    
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "([A-Z][a-z]+\\s?){3}", message = "Name should be in format: Ivanov Ivan Ivanovich")
    private String name;
    
    @Min(value = 1900, message = "Year should be in the range 1900-2010")
    @Max(value = 2010, message = "Year should be in the range 1900-2010")
    private int year;
    
    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Person() {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
