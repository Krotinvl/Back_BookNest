package com.booknest.repository;

import com.booknest.model.Character;
import com.booknest.model.CharacterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, CharacterId> {
    // Получение всех персонажей пользователя по username
    List<Character> findByUser_Username(String username);

    // Получение персонажей пользователя по названию коллекции
    List<Character> findByUser_UsernameAndCollection(String username, String collection);
}
