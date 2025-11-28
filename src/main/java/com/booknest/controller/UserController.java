package com.booknest.controller;

import com.booknest.dto.LibraryBookDto;
import com.booknest.dto.UserDto;
import com.booknest.service.LibraryService;
import com.booknest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserService userService;
    private final LibraryService libraryService; 
    
    public UserController(UserService userService, LibraryService libraryService) {
        this.userService = userService;
        this.libraryService = libraryService;
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
}