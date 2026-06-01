package com.gowda.productcatalogservice2026.dtos;

import com.gowda.productcatalogservice2026.models.Category;
import com.gowda.productcatalogservice2026.models.Product;

public class ProductDTO {
    private String name;
    private String description;
    private String imageUrl;
    private CategoryDTO categoryDto;
    private Double price;

    public Product toProduct() {
        /*
        This method is used to convert the ProductDTO object to a Product object.
         */
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);

        CategoryDTO categoryDTO = this.categoryDto;
        if (categoryDTO != null) {
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            product.setCategory(category);
        }

        return product;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryDTO getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDTO categoryDto) {
        this.categoryDto = categoryDto;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}