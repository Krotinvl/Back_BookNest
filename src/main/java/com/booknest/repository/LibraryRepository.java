package com.booknest.repository;

import com.booknest.model.Library;
import com.booknest.model.LibraryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository< Library, LibraryId> {
    
    //Найти все записи библиотеки по пользователю
    List<Library> findByUser_Username(String username);
    
    //  Альтернативный метод с JOIN для оптимизации
    @Query("SELECT l FROM Library l JOIN FETCH l.book WHERE l.user.username = :username")
    List<Library> findByUsernameWithBooks(@Param("username") String username);
    
    //  Найти книги по пользователю и коллекции
    List<Library> findByUser_UsernameAndCollection(String username, String collection);
}