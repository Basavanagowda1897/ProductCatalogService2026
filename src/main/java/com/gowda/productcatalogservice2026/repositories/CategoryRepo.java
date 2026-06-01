package com.gowda.productcatalogservice2026.repositories;

import com.gowda.productcatalogservice2026.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long Id);
}
