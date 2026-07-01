package com.gowda.productcatalogservice2026.repositories;

import com.gowda.productcatalogservice2026.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long Id);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);;

    Product getById(Long id);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findAllByOrderByPrice();

    @Query("SELECT p.description FROM Product p WHERE p.id = :id")
    String getDescriptionById(Long  id);

    Page<Product> findByName(String name, Pageable pageable);
}
