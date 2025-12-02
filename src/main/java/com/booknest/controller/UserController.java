package com.booknest.controller;

import com.booknest.dto.AddToLibraryDto;
import com.booknest.dto.CharacterDto;
import com.booknest.dto.CreateCharacterDto;
import com.booknest.dto.CreateQuoteDto;
import com.booknest.dto.LibraryBookDto;
import com.booknest.dto.QuoteDto;
import com.booknest.dto.UpdateCharacterDto;
import com.booknest.dto.UpdateCollectionDto;
import com.booknest.dto.UpdateQuoteDto;
import com.booknest.dto.UpdateUserDto;
import com.booknest.dto.UserDto;
import com.booknest.dto.UpdatePagesDto;
import com.booknest.service.CharacterService;
import com.booknest.service.LibraryService;
import com.booknest.service.QuoteService;
import com.booknest.service.ReviewService;
import com.booknest.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import com.booknest.service.PagesService;
import com.booknest.dto.PagesPerDayDto;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserService userService;
    private final LibraryService libraryService;
    private final QuoteService quoteService;
    private final CharacterService characterService;
    private final PagesService pagesService;
    private final ReviewService reviewService;

    public UserController(UserService userService, LibraryService libraryService, QuoteService quoteService, CharacterService characterService, PagesService pagesService, ReviewService reviewService) {
        this.userService = userService;
        this.libraryService = libraryService;
        this.quoteService = quoteService;
        this.characterService = characterService;
        this.pagesService = pagesService;
        this.reviewService = reviewService;
    }
    

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @GetMapping("/{username}/library")
    public ResponseEntity<List<LibraryBookDto>> getUserLibraryBooks(@PathVariable String username) {
        try {
            List<LibraryBookDto> libraryBooks = libraryService.getUserLibraryBooks(username);
            return ResponseEntity.ok(libraryBooks);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    

    @GetMapping("/{username}/library/{collection}")
    public ResponseEntity<List<LibraryBookDto>> getUserLibraryBooksByCollection(
            @PathVariable String username, 
            @PathVariable String collection) {
        try {
            List<LibraryBookDto> libraryBooks = libraryService.getUserLibraryBooksByCollection(username, collection);
            return ResponseEntity.ok(libraryBooks);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{username}/library")
    public ResponseEntity<LibraryBookDto> addBookToUserLibrary(
            @PathVariable String username,
            @Valid @RequestBody AddToLibraryDto addToLibraryDto) {
        try {
            LibraryBookDto result = libraryService.addBookToUserLibrary(username,addToLibraryDto);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{username}/library/{bookId}/collection")
    public ResponseEntity<LibraryBookDto> addBookToCollection(
        @PathVariable String username,
        @PathVariable Long bookId,
        @Valid @RequestBody UpdateCollectionDto updateCollectionDto) {
    try {
        LibraryBookDto result = libraryService.addBookToCollection(username, bookId, updateCollectionDto);
        return ResponseEntity.ok(result);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}

    @PutMapping("/{username}/library/{bookId}/pages")
    public ResponseEntity<LibraryBookDto> updatePagesReadForBook(
        @PathVariable String username,
        @PathVariable Long bookId,
        @RequestBody UpdatePagesDto updatePagesDto) {
    try {
        LibraryBookDto result = libraryService.updatePagesReadForBook(username, bookId, updatePagesDto.getPages());
        return ResponseEntity.ok(result);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}

    @DeleteMapping("/{username}/library/{bookId}/collection")
    public ResponseEntity<LibraryBookDto> removeBookFromCollection(
        @PathVariable String username,
        @PathVariable Long bookId) {
    try {
        LibraryBookDto result = libraryService.removeBookFromCollection(username, bookId);
        return ResponseEntity.ok(result);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}

    @GetMapping("/{username}/quotes")
    public ResponseEntity<List<QuoteDto>> getUserQuotes(@PathVariable String username) {
        List<QuoteDto> quotes = quoteService.getUserQuotes(username);
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/quotes/{quoteId}")
    public ResponseEntity<List<QuoteDto>> getQuoteById(@PathVariable Long quoteId) {
        List<QuoteDto> quotes = quoteService.getQuoteById(quoteId);
        return ResponseEntity.ok(quotes);
    }
    @GetMapping("/{username}/characters")
    public ResponseEntity<List<CharacterDto>> getUserCharacters(@PathVariable String username) {
        List<CharacterDto> characters = characterService.getUserCharacters(username);
        return ResponseEntity.ok(characters);
    }


    @GetMapping("/{username}/characters/collection/{collection}")
    public ResponseEntity<List<CharacterDto>> getUserCharactersByCollection(@PathVariable String username, @PathVariable String collection) {
        try {
            List<CharacterDto> characters = characterService.getUserCharactersByCollection(username, collection);
            return ResponseEntity.ok(characters);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}/pages/week")
    public ResponseEntity<List<PagesPerDayDto>> getUserWeeklyPages(@PathVariable String username) {
        try {
            List<PagesPerDayDto> result = pagesService.getWeeklyPages(username);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/pages")
    public ResponseEntity<PagesPerDayDto> addOrUpdatePages(@PathVariable String username, @RequestBody com.booknest.dto.CreatePagesDto createPagesDto) {
        try {
            PagesPerDayDto dto = pagesService.addOrUpdatePages(username, createPagesDto.getDateDay(), createPagesDto.getPages());
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{username}/characters/{characterName}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable String username, @PathVariable String characterName) {
        try {
            CharacterDto character = characterService.getCharacterByCharterName(username, characterName);
            return ResponseEntity.ok(character);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    

@PutMapping("/{username}")
public ResponseEntity<UserDto> updateUser(
        @PathVariable String username,
        @RequestBody UpdateUserDto updateUserDto) {
    try {
        UserDto updatedUser = userService.updateUser(username, updateUserDto);
        return ResponseEntity.ok(updatedUser);
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @PutMapping("/{username}/quotes/{quoteId}")
    public ResponseEntity<QuoteDto> updateQuote(
            @PathVariable String username,
            @PathVariable Long quoteId,
            @Valid @RequestBody UpdateQuoteDto updateQuoteDto) {
        try {
            QuoteDto updatedQuote = quoteService.updateQuote(quoteId, updateQuoteDto);
            return ResponseEntity.ok(updatedQuote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/quotes")
    public ResponseEntity<QuoteDto> createQuote(
            @PathVariable String username,
            @Valid @RequestBody CreateQuoteDto createQuoteDto) {
        try {
            QuoteDto createdQuote = quoteService.createQuote(username, createQuoteDto);
            return ResponseEntity.ok(createdQuote);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{username}/characters/{characterName}")
    public ResponseEntity<CharacterDto> updateCharacter(
            @PathVariable String username,
            @PathVariable String characterName,
            @Valid @RequestBody UpdateCharacterDto updateCharacterDto) {
        try {
            CharacterDto updatedCharacter = characterService.updateCharacter(username, characterName, updateCharacterDto);
            return ResponseEntity.ok(updatedCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/characters")
    public ResponseEntity<CharacterDto> createCharacter(
            @PathVariable String username,
            @Valid @RequestBody CreateCharacterDto createCharacterDto) {
        try {
            CharacterDto createdCharacter = characterService.createCharacter(username, createCharacterDto);
            return ResponseEntity.ok(createdCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}/library/{bookId}")
    public ResponseEntity<Void> deleteBookFromLibrary(
        @PathVariable String username,
        @PathVariable Long bookId) {
    try {
        libraryService.deleteBookFromLibrary(username, bookId);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{username}/characters/{characterName}")
    public ResponseEntity<Void> deleteCharacter(
        @PathVariable String username,
        @PathVariable String characterName) {
    try {
        characterService.deleteCharacter(username, characterName);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{username}/quotes/{quoteId}")
    public ResponseEntity<Void> deleteQuote(
        @PathVariable Long quoteId) {
    try {
        quoteService.deleteQuote(quoteId);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{username}/reviews/{bookId}")
    public ResponseEntity<Void> deleteReview(
        @PathVariable String username,
        @PathVariable Long bookId) {
    try {
        reviewService.deleteReview(bookId, username);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/{username}/activeDays")
    public ResponseEntity<Integer> getActiveDays(@PathVariable String username) {
        try {
            Integer activeDays = userService.getActiveDays(username);
            return ResponseEntity.ok(activeDays);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}/activeDays")
    public ResponseEntity<UserDto> updateActiveDays(
        @PathVariable String username,
        @RequestBody Map<String, Integer> body) {
    try {
        Integer activeDays = body.get("activeDays");
        UserDto updatedUser = userService.updateActiveDays(username, activeDays);
        return ResponseEntity.ok(updatedUser);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }
}

    @PostMapping("/{username}/pages/day")
    public ResponseEntity<PagesPerDayDto> addDay(@PathVariable String username) {
        try {
            PagesPerDayDto dto = pagesService.addDay(username);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}