package com.gowda.productcatalogservice2026.services;

import com.gowda.productcatalogservice2026.clients.FakeStoreAPIclient;
import com.gowda.productcatalogservice2026.dtos.FakestoreProductDto;
import com.gowda.productcatalogservice2026.exceptions.ProductNotExistException;
import com.gowda.productcatalogservice2026.models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakestoreProductService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;
    private FakestoreProductDto fakestoreProductDto;
//    @Value("${redis.products.section}")
//    private String productsSection;


//    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request,
//                                              Class<T> responseType, @Nullable Object... uriVariables) throws RestClientException {
//
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
//        return (restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables));
//    }
    @Autowired
    private FakeStoreAPIclient fakeStoreAPIclient;

    public FakestoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        //Calls fakestore apis
        ResponseEntity<FakestoreProductDto[]> fakestoreProductDtos = fakeStoreAPIclient.requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products", null, FakestoreProductDto[].class
        );
        if (fakestoreProductDtos.getBody() != null &&
                fakestoreProductDtos.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            for (FakestoreProductDto fakestoreProductDto : fakestoreProductDtos.getBody()) {
                products.add(fakestoreProductDto.toProduct());
            }
            return products;
        }
        return null;
    }

    public Product getProductById(Long id) throws ProductNotExistException {
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT" + id);
        if (product != null) {
            return product;
        }
        ResponseEntity<FakestoreProductDto> fakestoreProductDto = fakeStoreAPIclient.requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products/{id}",
                null,
                FakestoreProductDto.class,
                id
        );
        if (fakestoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            if (fakestoreProductDto.getBody() != null) {
                return fakestoreProductDto.getBody().toProduct();

            } else {
                throw new ProductNotExistException("Product with id" + id + "does'nt exist");
            }
        } else {
            Product products = fakestoreProductDto.getBody().toProduct();
            redisTemplate.opsForHash().put("PRODUCTS","PRODUCT" + id, product);
            //redisTemplate.opsForHash().put(productsSection,"PRODUCT" + id, product);
            return product;
        }
    }
    public Product createProduct(Product product) {
        return null;
    }

/*
Compile and runtime

Compile time exceptions are inherited from Exceptions class
Runtime exceptions are inherited from RuntimeException class

Implement replaceProduct()
Theory:-
        1. What is the difference between PUT and PATCH HTTP methods?


replace / PUT: It is used for Replacing a resource completely.
If you send an object, the old one is entirely removed and replaced by the new one.
        update / PATCH: It is used for Partial Updates.
For example, if you only want to change the price of a product but keep the name and description same, you use PATCH.
*/

    @Override
    public Product replaceProduct(Product newProduct, Long id) {
        ResponseEntity<FakestoreProductDto> response = fakeStoreAPIclient.requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                newProduct.toFakestoreProductDto(),
                FakestoreProductDto.class,
                id);
        if (response.getBody() != null && response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return response.getBody().toProduct();
        }
        return null;
    }

    @Override
    public Boolean deleteProductById(Long Id) {
        return null;
    }
}