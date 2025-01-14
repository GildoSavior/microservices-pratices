package com.example.springtest.review;

import com.example.springtest.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
