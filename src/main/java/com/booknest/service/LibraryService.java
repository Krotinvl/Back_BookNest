package com.booknest.service;

import com.booknest.dto.LibraryBookDto;
import com.booknest.model.Library;
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
    
    public LibraryService(LibraryRepository libraryRepository, UserRepository userRepository) {
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
    }
    
    public List<LibraryBookDto> getUserLibraryBooks(String username) {
        // Проверяем существует ли пользователь
        if (!userRepository.existsById(username)) {
            throw new RuntimeException("Пользователь не найден: " + username);
        }
        
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
    
    // Вспомогательный метод для преобразования Library → LibraryBookDto
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