package com.workintech.Model;

import java.util.List;
import java.util.Objects;

public class Author {
    private String name;

    private String id;
    List<Book> books;

    public Author(String name,  String id, List<Book> books) {
        this.name = name;

        this.id = id;
        this.books = books;
    }

    public String getName() {
        return name;
    }



    public String getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author: " +
                 name + '\'' +
                ", id=" + id;
    }
}
