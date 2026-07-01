package com.gowda.productcatalogservice2026.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonBackReference //solves circular dependency/ infinite loop problem while serializing the object to JSON
    private List<Product> products;
    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)// nothing but lazy loading
    private CategoryDetails categoryDetails;

    public Category() {}
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public CategoryDetails getCategoryDetails(){return categoryDetails;}

    public void setCategoryDetails(CategoryDetails categoryDetails){this.categoryDetails = categoryDetails;}
}
