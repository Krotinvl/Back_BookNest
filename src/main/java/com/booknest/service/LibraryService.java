package com.booknest.service;

import com.booknest.dto.AddToLibraryDto;
import com.booknest.dto.LibraryBookDto;
import com.booknest.dto.UpdateCollectionDto;
import com.booknest.model.Book;
import com.booknest.model.Library;
import com.booknest.model.User;
import com.booknest.repository.BookRepository;
import com.booknest.repository.LibraryRepository;
import com.booknest.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryService {
    
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    
    public LibraryService(LibraryRepository libraryRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

public LibraryBookDto addBookToCollection(String username, Long bookId, UpdateCollectionDto updateCollectionDto) {
    // Находим запись в библиотеке
    List<Library> libraryEntries = libraryRepository.findByUser_UsernameAndBook_Id(username, bookId);
    
    if (libraryEntries.isEmpty()) {
        throw new RuntimeException("Книга не найдена в библиотеке пользователя");
    }

    Library library = libraryEntries.get(0);
    
    library.setCollection(updateCollectionDto.getCollection());

    Library updatedLibrary = libraryRepository.save(library);

    return convertToLibraryBookDto(updatedLibrary);
}

public LibraryBookDto addBookToUserLibrary(String username, AddToLibraryDto addToLibraryDto) {
    // Проверяем существование юзеры
    User user = userRepository.findById(username)
            .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
    
    // Проверяем существование НИГГИ
    Book book = bookRepository.findById(addToLibraryDto.getBookId())
            .orElseThrow(() -> new RuntimeException("Книга не найдена: " + addToLibraryDto.getBookId()));
    
    // Проверяем, нет ли уже этой книги в библиотеке юзерка
    boolean bookAlreadyInLibrary = libraryRepository.findByUser_UsernameAndBook_Id(username, addToLibraryDto.getBookId())
            .stream()
            .findAny()
            .isPresent();
    
    if (bookAlreadyInLibrary) {
        throw new RuntimeException("Книга уже есть в библиотеке пользователя");
    }
    
    Library library = new Library();
    library.setUser(user);
    library.setBook(book);
    library.setCollection("не распределено"); 
    library.setPages(0);
    
    // Сохраняем в БД
    Library savedLibrary = libraryRepository.save(library);
    
    // Возвращаем DTO
    return convertToLibraryBookDto(savedLibrary);
}
    
    public List<LibraryBookDto> getUserLibraryBooks(String username) {
        
        // Получаем книги из библиотеки с JOIN FETCH для оптимизации
        List<Library> libraries = libraryRepository.findByUsernameWithBooks(username);
        
        // Преобразуем в DTO
        return libraries.stream()
                .map(this::convertToLibraryBookDto)
                .collect(Collectors.toList());
    }
    
    // Метод для получения книг по конкретной коллекции
    public List<LibraryBookDto> getUserLibraryBooksByCollection(String username, String collection) {
        if (!userRepository.existsById(username)) {
            throw new RuntimeException("Пользователь не найден: " + username);
        }
        
        List<Library> libraries = libraryRepository.findByUser_UsernameAndCollection(username, collection);
        
        return libraries.stream()
                .map(this::convertToLibraryBookDto)
                .collect(Collectors.toList());
    }
    

    private LibraryBookDto convertToLibraryBookDto(Library library) {
        LibraryBookDto dto = new LibraryBookDto();
        dto.setBookId(library.getBook().getId());
        dto.setBookTitle(library.getBook().getTitle());
        dto.setBookAuthor(library.getBook().getAuthor());
        dto.setBookGenre(library.getBook().getGenre());
        dto.setTotalPages(library.getBook().getPages());
        dto.setCollection(library.getCollection());
        dto.setPagesRead(library.getPages());
        dto.setReadingProgress(calculateProgress(library.getPages(), library.getBook().getPages()));
        return dto;
    }
    
    // Расчет прогресса чтения в процентах
    private Integer calculateProgress(Integer pagesRead, Integer totalPages) {
        if (pagesRead == null || totalPages == null || totalPages == 0) {
            return 0;
        }
        return (int) ((pagesRead * 100.0) / totalPages);
    }
}