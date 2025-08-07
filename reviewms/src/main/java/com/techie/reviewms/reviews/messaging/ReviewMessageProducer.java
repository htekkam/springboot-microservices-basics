package com.techie.reviewms.reviews.messaging;

import com.techie.reviewms.reviews.dto.ReviewMessage;
import com.techie.reviewms.reviews.model.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review){

        ReviewMessage message = new ReviewMessage();
        message.setId(review.getId());
        message.setTitle(review.getTitle());
        message.setDescription(review.getDescription());
        message.setCompanyId(review.getCompanyId());
        message.setRating(review.getRating());
        rabbitTemplate.convertAndSend("","companyRatingQueue",message);

    }

    public void sendConfirmation(String confirmMessage){
        rabbitTemplate.convertAndSend("","testqueue",confirmMessage);
    }
}
