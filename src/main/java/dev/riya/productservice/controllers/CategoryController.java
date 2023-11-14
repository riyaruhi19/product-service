package dev.riya.productservice.controllers;

import dev.riya.productservice.Dtos.CategoryDto;
import dev.riya.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
//    @GetMapping()
//    private String getAllCategories(){
//        return "Getting all Categories";
//    }

    @GetMapping()
    private List<String> getAllCategories(){
        ResponseEntity<List> response= new ResponseEntity<>(
                categoryService.getAllCategories(),
                HttpStatus.OK);
        return (List<String>)response.getBody();
    }


//    @GetMapping("/{categoryId}")
//    private String getproductsInCategories(@PathVariable ("categoryId") Long categoryId){
//        return "Get Products in Category with id:"+ categoryId;
//    }

    @GetMapping("/{categoryId}")
    private List<CategoryDto> getproductsInCategories(@PathVariable ("categoryId") String categoryId){
        ResponseEntity<List>  response= new ResponseEntity<>(
                categoryService.getproductsInCategories(categoryId),
                HttpStatus.OK
        );
        return response.getBody();
    }

}
