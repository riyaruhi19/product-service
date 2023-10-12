package dev.riya.productservice.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {

    default String getAllCategories() {
        return "Getting all Categories";
    }


    default String getproductsInCategories(Long categoryId) {
        return "Get Products in Category with id:" + categoryId;
    }
}
