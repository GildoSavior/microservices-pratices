package com.example.reviewmicroservice.review.messaging;

import com.example.reviewmicroservice.review.Review;
import com.example.reviewmicroservice.review.dto.ReviewMessage;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review) {
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setCompanyId(review.getCompanyId());

        try {
            rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
        } catch (AmqpException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
