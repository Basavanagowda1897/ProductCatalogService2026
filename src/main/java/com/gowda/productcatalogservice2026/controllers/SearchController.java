package com.gowda.productcatalogservice2026.controllers;

import com.gowda.productcatalogservice2026.dtos.SearchRequestDTO;
import com.gowda.productcatalogservice2026.dtos.SortParam;
import com.gowda.productcatalogservice2026.models.Product;
import com.gowda.productcatalogservice2026.services.ISearchService;
import com.gowda.productcatalogservice2026.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private ISearchService searchService;

    @PostMapping("/search")
    public Page<Product> searchProducts(@RequestBody SearchRequestDTO searchRequestDTO) {
        return searchService.searchProducts(searchRequestDTO.getQuery(), searchRequestDTO.getPageNo(), searchRequestDTO.getPageSize(), searchRequestDTO.getSortParams());
    }
}
