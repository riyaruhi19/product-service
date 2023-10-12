package dev.riya.productservice.Dtos;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class ProductDto {
    private long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
    private RatingDto rating;

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getCategory() {
        return this.category;
    }
}
