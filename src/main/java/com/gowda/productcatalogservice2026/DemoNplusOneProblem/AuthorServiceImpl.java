package com.gowda.productcatalogservice2026.DemoNplusOneProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl {

    @Autowired
    private AuthorRepo authorRepo;

    public String getAuthorDetails() {
        List<Author> authors = authorRepo.findAllWithBooks();
        for (Author author : authors) {
            System.out.println("No of books by author " +
                    author.getName() + " is " + author.getBooks().size());
        }
        return "Author details printed";
    }
}