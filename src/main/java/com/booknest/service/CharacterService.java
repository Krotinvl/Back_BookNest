package com.booknest.service;

import com.booknest.dto.CharacterDto;
import com.booknest.dto.UpdateCharacterDto;
import com.booknest.dto.CreateCharacterDto;
import com.booknest.model.Character;
import com.booknest.model.CharacterId;
import com.booknest.model.User;
import com.booknest.repository.CharacterRepository;
import com.booknest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

@Service
public class CharacterService {
    
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;
    
    public CharacterService(CharacterRepository characterRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
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

    public CharacterDto getCharacterByCharterName(String username, String characterName) {
        CharacterId characterId = new CharacterId(username, characterName);
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Персонаж не найден: " + characterName));
        
        return new CharacterDto(
                character.getName(),
                character.getDescription(),
                character.getCollection(),
                character.getBook()
        );
    }


    // Получение списка персонажей пользователя по названию коллекции
    
    public List<CharacterDto> getUserCharactersByCollection(String username, String collection) {
        if (!userRepository.existsById(username)) {
            throw new RuntimeException("Пользователь не найден: " + username);
        }

        List<Character> characters = characterRepository.findByUser_UsernameAndCollection(username, collection);

        return characters.stream()
                .filter(Objects::nonNull)
                .map(ch -> new CharacterDto(
                        ch.getName(),
                        ch.getDescription(),
                        ch.getCollection(),
                        ch.getBook()
                ))
                .collect(Collectors.toList());
    }

    public CharacterDto updateCharacter(String username, String characterName, UpdateCharacterDto updateCharacterDto) {
        CharacterId characterId = new CharacterId(username, characterName);
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Персонаж не найден: " + characterName));
        
        if (updateCharacterDto.getDescription() != null) {
            character.setDescription(updateCharacterDto.getDescription());
        }
        if (updateCharacterDto.getCollection() != null) {
            character.setCollection(updateCharacterDto.getCollection());
        }
        if (updateCharacterDto.getBook() != null) {
            character.setBook(updateCharacterDto.getBook());
        }
        
        Character updatedCharacter = characterRepository.save(character);
        return new CharacterDto(
                updatedCharacter.getName(),
                updatedCharacter.getDescription(),
                updatedCharacter.getCollection(),
                updatedCharacter.getBook()
        );
    }

    //Создание нового персонажа
    public CharacterDto createCharacter(String username, CreateCharacterDto createCharacterDto) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
        
        Character character = new Character();
        character.setUser(user);
        character.setName(createCharacterDto.getName());
        character.setDescription(createCharacterDto.getDescription());
        character.setCollection(createCharacterDto.getCollection());
        character.setBook(createCharacterDto.getBook());
        
        Character savedCharacter = characterRepository.save(character);
        return new CharacterDto(
                savedCharacter.getName(),
                savedCharacter.getDescription(),
                savedCharacter.getCollection(),
                savedCharacter.getBook()
        );
    }

    // Удалить персонажа
    public void deleteCharacter(String username, String characterName) {
        CharacterId characterId = new CharacterId(username, characterName);
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Персонаж не найден: " + characterName));
        characterRepository.delete(character);
    }
}
