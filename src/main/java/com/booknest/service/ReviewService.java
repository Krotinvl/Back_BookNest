package com.booknest.service;

import com.booknest.repository.ReviewRepository;
import com.booknest.model.Review;
import com.booknest.dto.ReviewBookDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.booknest.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository ){
        this.reviewRepository = reviewRepository;
        
    }

    public List<ReviewBookDto> getReviewBook(Long id) {
        
    List<Review> reviews = reviewRepository.findByBook_Id(id);
   
    return reviews.stream()
           .map(this::convetToReviewBookDto)
           .collect(Collectors.toList());
    }

    private ReviewBookDto convetToReviewBookDto(Review review) {
        ReviewBookDto dto = new ReviewBookDto();
        dto.setUsername(review.getUser().getUsername());
        dto.setBookId(review.getBook().getId());
        dto.setBookTitle(review.getBook().getTitle());
        dto.setBookAuthor(review.getBook().getAuthor());
        dto.setBookGenre(review.getBook().getGenre());
        dto.setRating(review.getRating());
        dto.setText(review.getText());
        return dto;

    }
}