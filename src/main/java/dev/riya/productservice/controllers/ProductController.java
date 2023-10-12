package dev.riya.productservice.controllers;

import dev.riya.productservice.Dtos.ErrorResponseDto;
import dev.riya.productservice.Dtos.GetSingleProductResponseDto;
import dev.riya.productservice.Dtos.ProductDto;
import dev.riya.productservice.Exceptions.NotFoundException;
import dev.riya.productservice.models.Category;
import dev.riya.productservice.models.Product;
import dev.riya.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

//    @GetMapping()
//    private String getAllProducts(){
//
//        return "Getting All products";
//    }

    @GetMapping()
    private List<Product> getAllProducts(){
       ResponseEntity<List> response= new ResponseEntity<>(
               productService.getAllProducts(),
               HttpStatus.OK
       );
       return (List<Product>)response.getBody();

    }

    // method returning product
//    @GetMapping("/{productId}")
//    private GetSingleProductResponseDto getSingleProduct(@PathVariable("productId")  Long productId){
//        GetSingleProductResponseDto responseDto= new GetSingleProductResponseDto();
//        responseDto.setProduct(productService.getSingleProduct(productId));
//        return responseDto;
//       // return "Returning Single Product with ID:"+ productId ;
//    }

    @GetMapping("/{productId}")
    private ResponseEntity<Product> getSingleProduct(@PathVariable("productId")
                                                         Long productId) throws NotFoundException {
        MultiValueMap<String,String> headers= new LinkedMultiValueMap<>();
        headers.add(
                "auth-token","no access to Riya"
        );

        Optional<Product> productOptional= productService.getSingleProduct(productId);
        if (productOptional.isEmpty()){
            throw new NotFoundException("No Product available with id :"+productId);
        }

        ResponseEntity<Product> response= new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND);
        return response;
    }

//    @PostMapping()
//    private String addNewProduct(@RequestBody ProductDto productDto){
//
//        return "Adding new Product with:" + productDto;
//    }
    @PostMapping()
    private ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product){
    Product newProduct = productService.addNewProduct(product);
    ResponseEntity<Product> response= new ResponseEntity<>(newProduct,HttpStatus.OK);
    return  response;
}

//    @PatchMapping Mapping("/{productId}")
//    private String updateProduct(@PathVariable("productId") Long productId){
//        return "Updating Product";
//    }

    @PatchMapping ("/{productId}")
    private Product updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody ProductDto productDto){
        Product product= new Product();
        product.setId(productDto.getId());
        product.setCategory( new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return productService.updateProduct(productId, product);
    }

    @PutMapping("/{productId}")
    private Product replaceProduct(@PathVariable("productId") Long productId,
                                   @RequestBody ProductDto productDto) {
        Product product= new Product();
        product.setId(productDto.getId());
        product.setCategory( new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    private String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting a Product";
    }

//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ErrorResponseDto> handleExceptions(Exception exception){
//    ErrorResponseDto errorResponseDto= new ErrorResponseDto();
//    errorResponseDto.setErrorMessage(exception.getMessage());
//    return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
//    }
}
