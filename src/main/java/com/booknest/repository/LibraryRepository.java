package com.booknest.repository;

import com.booknest.model.Library;
import com.booknest.model.LibraryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, LibraryId> {

    List<Library> findByUser_Username(String username);

    List<Library> findByUser_UsernameAndCollection(String username, String collection);

    List<Library> findByUser_UsernameAndBook_Id(String username, Long bookId);

    
}