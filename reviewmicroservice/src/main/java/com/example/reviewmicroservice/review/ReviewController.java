package com.example.reviewmicroservice.review;

import com.example.reviewmicroservice.review.messaging.ReviewMessageProducer;
import com.example.reviewmicroservice.review.service.IReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final IReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewController(IReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        var reviews = reviewService.getAllReviews(companyId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getById(companyId,  reviewId);

        return ResponseEntity.ok(review);
    }

    @PostMapping("/review")
    public ResponseEntity<Review> addReview(@PathVariable Long companyId, @RequestBody Review review) {

        try {
            Review reviewToCreate = reviewService.create(companyId, review);
            reviewMessageProducer.sendMessage(reviewToCreate);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Review> update(@PathVariable Long companyId, @PathVariable Long reviewId,  @RequestBody Review review) {

        Review reviewToCreate = reviewService.updateById(companyId, reviewId, review);

        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long companyId, @PathVariable Long reviewId) {
        reviewService.deleteById(companyId, reviewId);
        return ResponseEntity.ok("Delete success");
    }

}
