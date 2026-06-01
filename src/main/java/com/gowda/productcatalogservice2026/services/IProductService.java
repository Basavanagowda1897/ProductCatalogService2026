package com.gowda.productcatalogservice2026.services;

import com.gowda.productcatalogservice2026.exceptions.ProductNotExistException;
import com.gowda.productcatalogservice2026.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAllProducts();

    public Product getProductById(Long id) throws ProductNotExistException;

    public Product createProduct(Product product);

    public Product replaceProduct(Product newProduct, Long id);

    public Boolean deleteProductById(Long Id);

}
