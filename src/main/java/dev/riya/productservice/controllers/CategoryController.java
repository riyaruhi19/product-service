package dev.riya.productservice.controllers;

import dev.riya.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping()
    private String getAllCategories(){
        return "Getting all Categories";
    }

    @GetMapping("/{categoryId}")
    private String getproductsInCategories(@PathVariable ("categoryId") Long categoryId){
        return "Get Products in Category with id:"+ categoryId;
    }
}
