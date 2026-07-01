package com.gowda.productcatalogservice2026.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gowda.productcatalogservice2026.dtos.CategoryDTO;
import com.gowda.productcatalogservice2026.dtos.FakestoreProductDto;
import com.gowda.productcatalogservice2026.dtos.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private Category category;

    public Product() {
    };

    public Product(String name, String description, Double price, String imageUrl, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public ProductDTO toProductDTO(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(this.name);
        productDTO.setDescription(this.description);
        productDTO.setPrice(this.price);
        productDTO.setImageUrl(this.imageUrl);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(this.category.getName());
        categoryDTO.setDescription(this.category.getDescription());
        productDTO.setCategoryDto(categoryDTO);
        return productDTO;
    }
    public FakestoreProductDto toFakestoreProductDto(){
        /*
        This method is used to convert the Product object to a FakestoreProductDto object.
         */
        FakestoreProductDto fakestoreProductDto = new FakestoreProductDto();
        fakestoreProductDto.setId(this.getId());
        fakestoreProductDto.setTitle(this.name);
        fakestoreProductDto.setDescription(this.description);
        fakestoreProductDto.setPrice(this.price);
        fakestoreProductDto.setImage(this.imageUrl);
        fakestoreProductDto.setCategory(this.category.getName());

        return fakestoreProductDto;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
