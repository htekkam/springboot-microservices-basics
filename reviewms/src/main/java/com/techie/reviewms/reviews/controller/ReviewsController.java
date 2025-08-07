package com.techie.reviewms.reviews.controller;

import com.techie.reviewms.reviews.messaging.ReviewMessageProducer;
import com.techie.reviewms.reviews.model.Review;
import com.techie.reviewms.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review){

        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved){
            reviewMessageProducer.sendMessage(review);
            //reviewMessageProducer.sendConfirmation("review created");
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review not saved",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);

    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                            @RequestBody Review review){

        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteById(@PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteById(reviewId);
        if(isDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        else
            return  new ResponseEntity<>("Review does not exist",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/averageRating")
    public Double getAvgReview(@RequestParam Long companyId){

        return reviewService.getAllReviews().stream()
                .mapToDouble(Review::getCompanyId)
                .average()
                .orElse(0.0);
    }
}
