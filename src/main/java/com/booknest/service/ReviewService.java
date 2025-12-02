package com.booknest.service;

import com.booknest.repository.ReviewRepository;
import com.booknest.model.Review;
import com.booknest.model.User;
import com.booknest.model.Book;
import com.booknest.dto.ReviewBookDto;
import com.booknest.dto.UpdateReviewDto;
import com.booknest.dto.CreateReviewDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.booknest.repository.BookRepository;
import com.booknest.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository ){
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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

    public ReviewBookDto updateReview(Long bookId, String username, UpdateReviewDto updateReviewDto) {
        Review review = reviewRepository.findByBook_IdAndUser_Username(bookId, username)
                .orElseThrow(() -> new RuntimeException("Отзыв не найден"));
        
        if (updateReviewDto.getRating() != null) {
            review.setRating(updateReviewDto.getRating());
        }
        if (updateReviewDto.getText() != null) {
            review.setText(updateReviewDto.getText());
        }
        
        Review updatedReview = reviewRepository.save(review);
        return convetToReviewBookDto(updatedReview);
    }

    // Создание нового отзыва
     
        public ReviewBookDto createReview(Long bookId, String username, CreateReviewDto createReviewDto) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Книга не найдена: " + bookId));
        
        User user = userRepository.findById(username)
            .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
        
        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setRating(createReviewDto.getRating());
        review.setText(createReviewDto.getText());
        
        Review savedReview = reviewRepository.save(review);
        return convetToReviewBookDto(savedReview);
        }

    // Удалить отзыв
    public void deleteReview(Long bookId, String username) {
        Review review = reviewRepository.findByBook_IdAndUser_Username(bookId, username)
                .orElseThrow(() -> new RuntimeException("Отзыв не найден"));
        reviewRepository.delete(review);
    }
}