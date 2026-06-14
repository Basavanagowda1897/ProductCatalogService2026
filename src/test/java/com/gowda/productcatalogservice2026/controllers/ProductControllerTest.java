package com.gowda.productcatalogservice2026.controllers;

import com.gowda.productcatalogservice2026.dtos.ProductDTO;
import com.gowda.productcatalogservice2026.exceptions.ProductNotExistException;
import com.gowda.productcatalogservice2026.models.Category;
import com.gowda.productcatalogservice2026.models.Product;
import com.gowda.productcatalogservice2026.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
    private ObjectMapper objectMapper;

   @MockitoBean(name = "storageProductService")
   private IProductService productService;

   @Autowired
   private ProductController productController;

   @Test
   void createProduct_Api_Success() throws Exception {
       Category category = new Category("Category 1", "Description 1");
       Product product = new Product("Product 1", "Description 1", 10.0, "imageUrl1", category);
       ProductDTO productDTO = product.toProductDTO();
       when(productService.createProduct(any(Product.class))).thenReturn(product);

       String requestBody = objectMapper.writeValueAsString(productDTO);

       String expectedResponse = objectMapper.writeValueAsString(productDTO);

       MvcResult result = mockMvc.perform(post("/products")
                       .contentType("application/json")
                       .content(requestBody))
               .andExpect(status().isOk())
               .andExpect(content().string(expectedResponse))
               .andExpect(r -> assertEquals("--->", expectedResponse, r.getResponse().getContentAsString()))
               .andReturn();
   }

    @Test
    void getAllProduct_Api_Success() throws Exception {
        //ARRANGE
        Category category = new Category("Category 1", "Description 1");
        Category category1 = new Category("Category 2", "Description 2");

        Product product1 = new Product("Product 1", "Description 1", 10.0, "imageUrl1", category);
        Product product2 = new Product("Product 2", "Description 2", 20.0, "imageUrl2", category1);

        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        //expected response
        ProductDTO productDTO1 = product1.toProductDTO();
        ProductDTO productDTO2 = product2.toProductDTO();
        List<ProductDTO> expectedProductDTOs = Arrays.asList(productDTO1, productDTO2);
        String expectedResponse = objectMapper.writeValueAsString(expectedProductDTOs);
        System.out.println(expectedResponse);

        //ACT and ASSERT
        MvcResult result = mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse))
                .andExpect(r -> assertEquals("--->",expectedResponse, r.getResponse().getContentAsString()))
                .andReturn();
    }

    @Test
    void getProductById_Api_Failure() throws Exception {
        // Product with id -1 does not exist
        //ARRANGE
        Long productId = -1L;
        when(productService.getProductById(productId)).thenThrow(new ProductNotExistException("Product not found"));

        //ACT and ASSERT
        MvcResult result = mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Something went wrong"))
                .andReturn();
    }

   @Test
   void getAllProducts_Success() throws Exception {
       Category category1 = new Category("Category 1", "Description 1"); Category category2 = new Category("Category 2", "Description 2");
       Product product1 = new Product("Product 1", "Description 1", 100.0, "imageUrl1", category1);
       Product product2 = new Product("Product 2", "Description 2", 200.0, "imageUrl2", category2);
       List<Product> productList = Arrays.asList(product1, product2);
       when(productService.getAllProducts()).thenReturn(productList);

    List<ProductDTO> expectedProductDTOs = productController.getAllProducts();

    assertEquals("Product list size should be 2", 2, expectedProductDTOs.size());
    assertEquals("First product name should be 'Product 1'", "Product 1",expectedProductDTOs.get(0).getName());
    assertEquals("First product description should be 'Description 1'", "Description 1",expectedProductDTOs.get(0).getDescription());
    assertEquals("First product image url should be 'imageUrl1'", "imageUrl1",expectedProductDTOs.get(0).getImageUrl());
    assertEquals("First product price should be 100.0", 100.0,expectedProductDTOs.get(0).getPrice());
    assertEquals("Second product name should be 'Product 2'", "Product 2",expectedProductDTOs.get(1).getName());
    assertEquals("Second product description should be 'Description 2'", "Description 2",expectedProductDTOs.get(1).getDescription());
    assertEquals("Second product image url should be 'imageUrl2'", "imageUrl2",expectedProductDTOs.get(1).getImageUrl());
    assertEquals("Second product price should be 200.0",    200.0,expectedProductDTOs.get(1).getPrice());
   }
//   @Test
//    void getProductById_Failure() throws Exception {
//       Long productId = 1L;
//       when(productService.getProductById(productId)).thenThrow(new ProductNotExistException("Product not found"));
//       ResponseEntity<ProductDTO> expectedProductDTO= null;
//       try {
//           expectedProductDTO = productController.getProductById(productId);
//       }catch (ProductNotExistException e){
//           System.out.println("Exception caught as expected" +e.getMessage());
//       }
//       assertThrows(ProductNotExistException.class, () -> productController.getProductById(productId));
//    }

    @Test
    void getProductById_Failure() throws Exception {
        // Product with id -1 does not exist
        //ARRANGE
        Long productId = -1L;
        when(productService.getProductById(productId)).thenThrow(new ProductNotExistException("Product not found"));
        //ASSERT
        Exception exception = assertThrows(ProductNotExistException.class, () -> productController.getProductById(productId));
        assertEquals("Exception message should be 'Product not found'", "Product not found", exception.getMessage());
        verify(productService, times(1)).getProductById(productId);
        // here we are checking that productService.getProductById(productId) is called exactly
        // once during the execution of the test,
        // and it is called with the correct productId.
        // If the method is not called or called with a different productId, the test will fail.
    }
    @Test
    void getProductById_Success() throws Exception {
       Long productId = 1L;
       Category category1 = new Category("Category 1", "Description 1");
       Product product1 = new Product("Product 1", "Description 1", 100.0, "imageUrl1", category1);
       when(productService.getProductById(productId)).thenReturn(product1);
       ResponseEntity<ProductDTO> expectedProductDTO= productController.getProductById(productId);
       assertEquals("Product name should be 'Product 1'", "Product 1",expectedProductDTO.getBody().getName());
       assertEquals("Product description should be 'Description 1'", "Description 1",expectedProductDTO.getBody().getDescription());
       assertEquals("Product image url should be 'imageUrl1'", "imageUrl1",expectedProductDTO.getBody().getImageUrl());
       assertEquals("Product price should be 100.0", 100.0,expectedProductDTO.getBody().getPrice());
    }
}
