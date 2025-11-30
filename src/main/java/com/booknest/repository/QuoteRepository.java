package com.booknest.repository;

import com.booknest.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByUser_Username(String username);    
    
    @Query("SELECT q FROM Quote q WHERE q.id = :id")
    List<Quote> findByIdQuote(@Param("id") Long id);
}