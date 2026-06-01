package com.gowda.productcatalogservice2026.DemoNplusOneProblem;

import com.gowda.productcatalogservice2026.models.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
public class Author extends BaseModel {
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}