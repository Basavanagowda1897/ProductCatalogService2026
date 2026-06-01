package com.gowda.productcatalogservice2026.repositories;

import com.gowda.productcatalogservice2026.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;;
    @Transactional
    public void testJpaMethods(){
//        List<Product> products = productRepo.findByPriceBetween(10.0, 500.0);
//        System.out.println(products);
//        products.forEach(product -> {
//            assertTrue(product.getPrice() >= 10.0 && product.getPrice() <= 500.0);
        List<Product> products = productRepo.findAllByOrderByPrice();
        System.out.println(products);

        String desc = productRepo.getDescriptionById(3L);
        System.out.println();
        }
    }

