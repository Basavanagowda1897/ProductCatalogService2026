package com.gowda.productcatalogservice2026.controllers;

import com.gowda.productcatalogservice2026.dtos.ProductDTO;
import com.gowda.productcatalogservice2026.exceptions.ProductNotExistException;
import com.gowda.productcatalogservice2026.models.Product;
import com.gowda.productcatalogservice2026.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("storageProductService")
    private IProductService productService;

//    Either this or Autowired can be used
//    private ProductController(IProductService productService){
//        this.productService = productService;
//    }

    @GetMapping("/products")
      public List<ProductDTO> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(Product product: products) {
            productDTOS.add(product.toProductDTO());
        }
         return productDTOS;
     }

     @GetMapping("/products/{id}")
     public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) throws ProductNotExistException {
//         Product product;
//        try{
//            product = productService.getProductById(id);
//            return ResponseEntity.ok(product.toProductDTO());
//        }catch (ProductNotExistException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
         Product product = productService.getProductById(id);
         return ResponseEntity.ok(product.toProductDTO());
     }

    @PostMapping("/products")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        Product product = productDTO.toProduct();
        Product createdProduct = productService.createProduct(product);
        return createdProduct.toProductDTO();
    }


     @PutMapping("/products/{id}")
     public ProductDTO replaceProduct(@PathVariable("id")Long productId,
                                      @RequestBody ProductDTO productDTO){
        Product response = productService.replaceProduct(productDTO.toProduct(),productId);
        return response.toProductDTO();
     }


     // Local exception handler
     @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<String> handleProductNotExistException(ProductNotExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/products/{productId}/{userID}")
//    public ProductDTO getProductBasedOnUserRole(@PathVariable Long productId, @PathVariable Long userId) {
//        System.out.println("Call reaching this api");
//        Product product = productService.getProductBasedOnUserScope(productId, userId);
//        return null;
//    }
}
