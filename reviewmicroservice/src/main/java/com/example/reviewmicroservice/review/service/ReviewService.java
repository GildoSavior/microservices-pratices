package com.example.springtest.review.service;

import com.example.springtest.company.Company;
import com.example.springtest.company.ICompanyRepository;
import com.example.springtest.review.IReviewRepository;
import com.example.springtest.review.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    private final IReviewRepository reviewRepository;
    private final ICompanyRepository companyRepository;

    public ReviewService(IReviewRepository reviewRepository, ICompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getById(Long companyId, Long reviewId) {
        List<Review> reviewsByCompany = reviewRepository.findByCompanyId(companyId);

        Review review = reviewsByCompany.stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElse(null);

        return review;
    }

    @Override
    public Review create(Long companyId, Review review) {
        Company company = companyRepository.findById(companyId).orElse(null);
        review.setCompany(company);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateById(Long companyId, Long reviewId, Review review) {

        Review reviewToUpdate = reviewRepository.findById(reviewId).orElse(null);

        reviewToUpdate.setTitle(review.getTitle());
        reviewToUpdate.setDescription(review.getDescription());
        reviewToUpdate.setRating(review.getRating());

        return reviewRepository.save(reviewToUpdate);
    }

    @Override
    public void deleteById(Long companyId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        Company reviewCompany = review.getCompany();
        reviewCompany.getReviews().remove(review);
        companyRepository.save(reviewCompany);
        reviewRepository.deleteById(reviewId);
    }
}
