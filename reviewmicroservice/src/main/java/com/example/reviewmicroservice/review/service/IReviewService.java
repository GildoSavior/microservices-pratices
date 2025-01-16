package com.example.reviewmicroservice.review.service;

import com.example.reviewmicroservice.review.Review;

import java.util.List;

public interface IReviewService {

    List<Review> getAllReviews(Long companyId);

    Review getById(Long companyId, Long reviewId);

    Review create(Long companyId, Review review);

    Review updateById(Long companyId, Long reviewId, Review review);

    void deleteById(Long company, Long reviewId);
}
