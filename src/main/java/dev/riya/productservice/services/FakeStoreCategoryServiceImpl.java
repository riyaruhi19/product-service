package dev.riya.productservice.services;

import org.springframework.stereotype.Service;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{

    @Override
    public String getproductsInCategories(Long categoryId) {
        return CategoryService.super.getproductsInCategories(categoryId);
    }

    @Override
    public String getAllCategories() {
        return CategoryService.super.getAllCategories();
    }
}
