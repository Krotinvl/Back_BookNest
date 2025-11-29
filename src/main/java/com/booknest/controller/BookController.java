package com.booknest.controller;

import com.booknest.dto.BookDto;
import com.booknest.dto.CreateBookDto;
import com.booknest.dto.ReviewBookDto;
import com.booknest.model.Book;
import com.booknest.service.BookService;
import com.booknest.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    
    private final BookService bookService;
    private final ReviewService reviewService;
    
    public BookController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }
    
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(b -> ResponseEntity.ok(convertToDto(b)))
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestParam String query) {
        List<Book> books = bookService.searchBooks(query);
        List<BookDto> bookDtos = books.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }
    
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        List<BookDto> bookDtos = books.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }
    
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDto>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        List<BookDto> bookDtos = books.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{id}/review")
    public ResponseEntity<List<ReviewBookDto>>getReviewBook(@PathVariable Long id ) {
        List<ReviewBookDto> reviewBoks = reviewService.getReviewBook(id);
        return ResponseEntity.ok(reviewBoks);
        
        
    }

     @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        try {
            BookDto createdBook = bookService.createBook(createBookDto);
            return ResponseEntity.ok(createdBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    BookDto convertToDto(Book book) {
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getGenre(),
            book.getPages(),
            book.getDescription(),
            book.getPublishing(),
            book.getDateOfPublication(),
            book.getCycle(),
            book.getImage(),
            book.getImage_type()
           );
    }
}