package com.gowda.productcatalogservice2026.DemoNplusOneProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping("/authors")
    public String getAuthors() {
        return authorService.getAuthorDetails();
    }

}