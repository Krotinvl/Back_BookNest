package com.booknest.controller;

import com.booknest.dto.AddToLibraryDto;
import com.booknest.dto.LibraryBookDto;
import com.booknest.dto.QuoteDto;
import com.booknest.dto.UpdateCollectionDto;
import com.booknest.dto.UpdateUserDto;
import com.booknest.dto.UserDto;
import com.booknest.service.LibraryService;
import com.booknest.service.QuoteService;
import com.booknest.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserService userService;
    private final LibraryService libraryService;
    private final QuoteService  quoteService;
    
    public UserController(UserService userService, LibraryService libraryService, QuoteService quoteService) {
        this.userService = userService;
        this.libraryService = libraryService;
        this.quoteService = quoteService;
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

    @GetMapping("/{username}/quotes")
    public ResponseEntity<List<QuoteDto>> getUserQuotes(@PathVariable String username) {
        List<QuoteDto> quotes = quoteService.getUserQuotes(username);
        return ResponseEntity.ok(quotes);
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

}