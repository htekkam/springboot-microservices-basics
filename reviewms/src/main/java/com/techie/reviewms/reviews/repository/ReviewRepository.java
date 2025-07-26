package com.techie.reviewms.reviews.repository;

import com.techie.reviewms.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);

    List<Review> findByCompanyIdAndId(Long companyId, Long id);

    int deleteByCompanyIdAndId(Long companyId, Long id);
}
