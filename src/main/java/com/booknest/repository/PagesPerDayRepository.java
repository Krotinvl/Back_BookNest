package com.booknest.repository;

import com.booknest.model.PagesPerDay;
import com.booknest.model.PagesPerDayId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagesPerDayRepository extends JpaRepository<PagesPerDay, PagesPerDayId> {
    List<PagesPerDay> findByUser_UsernameAndDateDayBetween(String username, LocalDate start, LocalDate end);
    Optional<PagesPerDay> findByUser_UsernameAndDateDay(String username, LocalDate dateDay);
}
