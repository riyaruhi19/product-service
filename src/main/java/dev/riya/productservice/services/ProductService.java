package dev.riya.productservice.services;

import dev.riya.productservice.Dtos.ProductDto;
import dev.riya.productservice.models.Category;
import dev.riya.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
     List<Product> getAllProducts() ;

    Optional<Product> getSingleProduct(Long productId) ;


    Product addNewProduct(ProductDto product);

    /*Product object has only those fields filled which needs to be updated.
     everything else is null.
    */
    Product updateProduct(Long productId, Product product) ;

    Product replaceProduct(Long productId, Product product) ;

    boolean deleteProduct( Long productId) ;
}
