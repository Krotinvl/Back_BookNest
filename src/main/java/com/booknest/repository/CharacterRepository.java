package com.booknest.repository;

import com.booknest.model.Character;
import com.booknest.model.CharacterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, CharacterId> {
    List<Character> findByUser_Username(String username);
    List<Character> findByUser_UsernameAndCollection(String username, String collection);
}
