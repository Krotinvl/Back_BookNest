package com.booknest.repository;

import com.booknest.model.Review;
import com.booknest.model.ReviewId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {

    List<Review> findByBook_Id(Long bookId);
    
    Optional<Review> findByBook_IdAndUser_Username(Long bookId, String username);
    
}
