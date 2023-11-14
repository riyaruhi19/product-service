package dev.riya.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private long id;
    private String title;
    private Double price;
    private String Category;
    private String description;
    private String image;

 }
