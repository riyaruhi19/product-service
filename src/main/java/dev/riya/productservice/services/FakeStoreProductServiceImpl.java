package dev.riya.productservice.services;

import dev.riya.productservice.Dtos.FakeStoreProductDto;
import dev.riya.productservice.Dtos.ProductDto;
import dev.riya.productservice.models.Category;
import dev.riya.productservice.models.Product;
import jakarta.websocket.OnClose;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        Product product= new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category= new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        return product;
    }

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate= restTemplateBuilder.
                requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url,httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate= restTemplateBuilder.build();
//        ResponseEntity<List> response= restTemplate.getForEntity(
//                               "https://fakestoreapi.com/products",
//                               List.class
//                       );
//        List<Product> answer= new ArrayList<>();
//
//        for (Object obj :response.getBody()){
//            HashMap<String,Object> hm = (HashMap<String, Object>) obj;
//
//            Product product= new Product();
//            product.setId(Long.valueOf((Integer)hm.get("id")));
//            product.setTitle((String)hm.get("tittle"));
//            product.setPrice(Double.valueOf(hm.get("price").toString()));
//            Category category= new Category();
//            category.setName((String) hm.get("category"));
//            product.setCategory(category);
//            product.setDescription((String) hm.get("description"));
//            product.setImageUrl((String)hm.get("image"));
//
//            answer.add(product);
//        }

        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> answer= new ArrayList<>();

        for (FakeStoreProductDto productDto :response.getBody()){
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }


        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        //FIrst create the object of rest template builder.
        RestTemplate restTemplate=restTemplateBuilder.build();// object of restTemplate

        ResponseEntity<FakeStoreProductDto> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                productId);
        //(url,return_type,params_in the url)

//        if (response.getStatusCode().is2xxSuccessful()){
//            //..
//        }
//        else
//            //exception

        FakeStoreProductDto productDto=response.getBody();
        if (productDto==null){
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        Object Product;
        ResponseEntity<FakeStoreProductDto> response= restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class);

        FakeStoreProductDto productDto=response.getBody();

        return convertFakeStoreProductDtoToProduct(productDto);

    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate= restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId );

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId );

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}

