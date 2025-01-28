package com.example.reviewmicroservice.review.dto;

public class ReviewMessage {
    private long id;
    private String title;
    private String description;
    private double rating;
    private long companyId;

    public long getId() {
        return id;
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

    public long getCompanyId() {
        return companyId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
