package com.booknest.repository;

import com.booknest.model.Review;
import com.booknest.model.ReviewId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {

    // Найти все отзавы по id книги
    List<Review> findByBook_Id(Long bookId);
    
}
