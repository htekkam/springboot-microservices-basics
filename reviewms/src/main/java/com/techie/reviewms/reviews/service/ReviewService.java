package com.techie.reviewms.reviews.service;


import com.techie.reviewms.reviews.model.Review;
import com.techie.reviewms.reviews.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews(Long companyId){
        return reviewRepository.findByCompanyId(companyId);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public boolean addReview(Long companyId, Review review){

        if(companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        else
            return false;
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteById(Long id) {
        reviewRepository.deleteById(id);
        return true;
    }

    public boolean updateReview(Long reviewId, Review updatedReview){

        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }
}
