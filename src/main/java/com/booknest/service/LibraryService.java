package com.booknest.service;

import com.booknest.dto.AddToLibraryDto;
import com.booknest.dto.BookDto;
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
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryService {
    
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PagesService pagesService;

    
    public LibraryService(LibraryRepository libraryRepository, UserRepository userRepository, BookRepository bookRepository, PagesService pagesService) {
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.pagesService = pagesService;
    }

    // Сделать книгу сделать нераспределённой
    public LibraryBookDto removeBookFromCollection(String username, Long bookId) {
        List<Library> libraryEntries = libraryRepository.findByUser_UsernameAndBook_Id(username, bookId);
        if (libraryEntries.isEmpty()) {
            throw new RuntimeException("Книга не найдена в библиотеке пользователя");
        }

        Library library = libraryEntries.get(0);
        library.setCollection("не распределено");
        Library saved = libraryRepository.save(library);
        return convertToLibraryBookDto(saved);
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

    // Обновить количество прочитанных страниц для книги в библиотеке пользователя
    public LibraryBookDto updatePagesReadForBook(String username, Long bookId, Integer pagesRead) {
        List<Library> libraryEntries = libraryRepository.findByUser_UsernameAndBook_Id(username, bookId);
        if (libraryEntries.isEmpty()) {
            throw new RuntimeException("Книга не найдена в библиотеке пользователя");
        }

        Library library = libraryEntries.get(0);
        Integer oldPages = library.getPages() == null ? 0 : library.getPages();
        library.setPages(pagesRead);
        Library saved = libraryRepository.save(library);

        int delta = (pagesRead == 0 ? 0 : pagesRead) - (oldPages == 0 ? 0 : oldPages);
        if (delta != 0) {
            pagesService.incrementPages(username, LocalDate.now(), delta);
        }

        return convertToLibraryBookDto(saved);
    }
    
    public List<LibraryBookDto> getUserLibraryBooks(String username) {
        
        List<Library> libraries = libraryRepository.findByUser_Username(username);
        
        // Преобразуем в DTO
        return libraries.stream()
                .map(this::convertToLibraryBookDto)
                .collect(Collectors.toList());
    }

    // Получить конкретную книгу из библиотеки пользователя
    public LibraryBookDto getUserLibraryBook(String username, Long bookId) {
        List<Library> libraryEntries = libraryRepository.findByUser_UsernameAndBook_Id(username, bookId);
        
        if (libraryEntries.isEmpty()) {
            throw new RuntimeException("Книга не найдена в библиотеке пользователя: " + bookId);
        }
        
        return convertToLibraryBookDto(libraryEntries.get(0));
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
        Book book = library.getBook();
        BookDto bookDto = new BookDto(
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
        
        LibraryBookDto dto = new LibraryBookDto();
        dto.setBook(bookDto);
        dto.setCollection(library.getCollection());
        dto.setPagesRead(library.getPages());
        dto.setReadingProgress(calculateProgress(library.getPages(), book.getPages()));
        
        return dto;
    }
    
    // Расчет прогресса чтения в процентах
    private Integer calculateProgress(Integer pagesRead, Integer totalPages) {
        if (pagesRead == null || totalPages == null || totalPages == 0) {
            return 0;
        }
        return (int) ((pagesRead * 100.0) / totalPages);
    }

    // Удалить книгу из библиотеки
    public void deleteBookFromLibrary(String username, Long bookId) {
        List<Library> libraryEntries = libraryRepository.findByUser_UsernameAndBook_Id(username, bookId);
        if (libraryEntries.isEmpty()) {
            throw new RuntimeException("Книга не найдена в библиотеке пользователя");
        }
        libraryRepository.delete(libraryEntries.get(0));
    }

    public List<String> getUserLibraryCollection(String username){
        return libraryRepository.findDistinctCollectionByUser_Username(username);
    }
}