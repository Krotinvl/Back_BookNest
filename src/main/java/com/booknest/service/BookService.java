package com.booknest.service;

import com.booknest.dto.BookDto;
import com.booknest.dto.CreateBookDto;
import com.booknest.model.Book;
import com.booknest.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public BookDto createBook(CreateBookDto createBookDto) {
        Book book = new Book();
        book.setTitle(createBookDto.getTitle());
        book.setAuthor(createBookDto.getAuthor());
        book.setGenre(createBookDto.getGenre());
        book.setPages(createBookDto.getPages());
        book.setDescription(createBookDto.getDescription());
        book.setPublishing(createBookDto.getPublishing());
        book.setDateOfPublication(createBookDto.getDateOfPublication());
        book.setCycle(createBookDto.getCycle());
        book.setImage_type(createBookDto.getImage_type());
        
        if (createBookDto.getImage() != null && !createBookDto.getImage().isEmpty()) {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] imageBytes = decoder.decode(createBookDto.getImage());
            book.setImage(imageBytes);
        }
        
        Long newId = generateNewBookId();
        book.setId(newId);
        
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }
        private Long generateNewBookId() {
    List<Book> allBooks = bookRepository.findAll();
    
    if (allBooks.isEmpty()) {
        return 37L;
    }
    
    Long maxId = allBooks.stream()
            .mapToLong(Book::getId)
            .max()
            .getAsLong();
            
    return maxId + 1; 
}

    private BookDto convertToDto(Book book) {
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

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public List<Book> searchBooks(String query) {
        return bookRepository.searchBooks(query);
    }
    
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

}