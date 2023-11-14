package dev.riya.productservice.services;

import dev.riya.productservice.Dtos.CategoryDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCategoryServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public List<String> getAllCategories() {
        RestTemplate restTemplate=  restTemplateBuilder.build();
        ResponseEntity<String[]> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",
                String[].class)  ;
      //  System.out.println("response : " + response.toString());
        List<String> ans= new ArrayList<>();

        for (String categoryName: response.getBody()){
            ans.add(categoryName);
        }
        return ans;
    }

    @Override
    public List<CategoryDto> getproductsInCategories(String categoryId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<CategoryDto[]> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/{id}",
                CategoryDto[].class,
                categoryId);

        List<CategoryDto> ans= new ArrayList<>();

        for(CategoryDto categoryDto: response.getBody()){
            ans.add(categoryDto);
        }


        return ans;
    }
}
