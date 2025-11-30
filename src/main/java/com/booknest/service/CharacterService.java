package com.booknest.service;

import com.booknest.dto.CharacterDto;
import com.booknest.model.Character;
import com.booknest.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {
    
    private final CharacterRepository characterRepository;
    
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    
    // Получение списка персонажей пользователя по username
    public List<CharacterDto> getUserCharacters(String username) {
        List<Character> characters = characterRepository.findByUser_Username(username);
        
        return characters.stream()
                .map(character -> new CharacterDto(
                    character.getName(),
                    character.getDescription(),
                    character.getCollection(),
                    character.getBook()
                ))
                .collect(Collectors.toList());
    }
}
