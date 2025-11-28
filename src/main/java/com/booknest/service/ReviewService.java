package com.booknest.service;

import com.booknest.repository.ReviewRepository;
import com.booknest.model.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.booknest.repository.UserRepository;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository ){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewUserDto> getUserReview(String username) {
        
    }


}
