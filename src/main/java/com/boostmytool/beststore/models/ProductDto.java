package com.boostmytool.beststore.models;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class ProductDto
{
    private String name;

    private Double price;

    private String category;


    private String brand;


    private String description;

    private MultipartFile imageeFileName;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImageeFileName() {
        return imageeFileName;
    }

    public void setImageeFileName(MultipartFile imageeFileName) {
        this.imageeFileName = imageeFileName;
    }
}
