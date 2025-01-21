package com.example.jobmicroservice.job.clients;

import com.example.jobmicroservice.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("REVIEW-SERVICE")
public interface ReviewClient {

    @GetMapping("/companies/{id}/reviews")
    List<Review> getReviews(@PathVariable Long id);
}
