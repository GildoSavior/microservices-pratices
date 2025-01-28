package com.example.companymicroservice.company.messaging;

import com.example.companymicroservice.company.dto.ReviewMessage;
import com.example.companymicroservice.company.service.ICompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReviewMessageConsumer {
    private  final ICompanyService companyService;

    public ReviewMessageConsumer(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updateCompanyRating(reviewMessage);
    }
}
