package com.techie.companyms.company.messaging;

import com.techie.companyms.company.dto.ReviewMessage;
import com.techie.companyms.company.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        System.out.println("Review message::"+reviewMessage.getRating());
        companyService.updateCompanyRating(reviewMessage);
    }

//    @RabbitListener(queues = "testqueue")
//    public void consumeConfirmMsg(String confirmMessage){
//        System.out.println("Confirmation message::"+confirmMessage);
//
//    }
}
