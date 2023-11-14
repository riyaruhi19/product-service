package dev.riya.productservice.services;

import dev.riya.productservice.Dtos.CategoryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories();


     List<CategoryDto> getproductsInCategories(String categoryId) ;

}
