package com.gowda.productcatalogservice2026.services;

import com.gowda.productcatalogservice2026.dtos.SortParam;
import com.gowda.productcatalogservice2026.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, int page, int size, List<SortParam> sortParams);
}
