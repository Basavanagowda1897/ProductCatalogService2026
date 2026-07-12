package com.gowda.productcatalogservice2026.services;

import com.gowda.productcatalogservice2026.exceptions.ProductNotExistException;
import com.gowda.productcatalogservice2026.models.Product;
import com.gowda.productcatalogservice2026.models.State;
import com.gowda.productcatalogservice2026.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("storageProductService")
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.orElseThrow(() -> new ProductNotExistException("Product with id " + id + " does not exist"));
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optionalProduct;
        if(product.getId() != null) {
            optionalProduct = productRepo.findById(product.getId());
            if (optionalProduct.isPresent()) {
                throw new RuntimeException("Product with id " + product.getId() + " already exists");
            }
        }
        return productRepo.save(product);


//        if(optionalProduct.isEmpty()){
//            return productRepo.save(product);
//        }else {
//            throw new RuntimeException("Product with id " + product.getId() + " already exists");
//        }
    }

    @Override
    public Product replaceProduct(Product newProduct, Long id) {
        return null;
    }

    @Override
    public Boolean deleteProductById(Long Id) {
        Optional<Product> optionalProduct = productRepo.findById(Id);

        if(optionalProduct.isEmpty()){
            return false;
           // throw new RuntimeException("Product with id " + Id + " does not exist");
        }else {
            Product product = optionalProduct.get();
            product.setState(State.DELETED);
            productRepo.save(product);
            return true;
        }
    }
//    public Product getProductBasedOnUserScope(Long productId, Long userId) {
//        Optional<Product> optionalProduct = productRepo.findById(productId);
//        try {
//            UserDTO userDto = restTemplate.getForEntity(
//                    "https://UserAuthService/users/{userID}",
//                    UserDTO.class,
//                    userId).getBody();
//        }catch(Exception e){
//                System.out.println(e.getMessage());
//            }
//            return null;
//        }
//    }
}
