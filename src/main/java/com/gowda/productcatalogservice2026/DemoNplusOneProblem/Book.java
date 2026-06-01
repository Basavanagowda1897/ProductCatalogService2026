package com.gowda.productcatalogservice2026.DemoNplusOneProblem;

import com.gowda.productcatalogservice2026.models.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book extends BaseModel {
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id") // mapping the foreign key column in the Book table
    private Author author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}