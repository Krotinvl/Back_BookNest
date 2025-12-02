package com.booknest.service;

import com.booknest.dto.QuoteDto;
import com.booknest.dto.UpdateQuoteDto;
import com.booknest.dto.CreateQuoteDto;
import com.booknest.model.Quote;
import com.booknest.model.User;
import com.booknest.repository.QuoteRepository;
import com.booknest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    
    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }
    
    public List<QuoteDto> getUserQuotes(String username) {
        List<Quote> quotes = quoteRepository.findByUser_Username(username);
        
        return quotes.stream()
                .map(quote -> new QuoteDto(
                    quote.getId(),
                    quote.getText(),
                    quote.getBook(),
                    quote.getCharacter()
                ))
                .collect(Collectors.toList());
    }
    
    // Получение цитаты по ID
    public List<QuoteDto> getQuoteById(Long id) {
        List<Quote> quotes = quoteRepository.findByIdQuote(id);
        
        return quotes.stream()
                .map(quote -> new QuoteDto(
                    quote.getId(),
                    quote.getText(),
                    quote.getBook(),
                    quote.getCharacter()
                ))
                .collect(Collectors.toList());
    }

    public QuoteDto updateQuote(Long id, UpdateQuoteDto updateQuoteDto) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Цитата не найдена: " + id));
        
        if (updateQuoteDto.getText() != null) {
            quote.setText(updateQuoteDto.getText());
        }
        if (updateQuoteDto.getBook() != null) {
            quote.setBook(updateQuoteDto.getBook());
        }
        if (updateQuoteDto.getCharacter() != null) {
            quote.setCharacter(updateQuoteDto.getCharacter());
        }
        
        Quote updatedQuote = quoteRepository.save(quote);
        return new QuoteDto(
                updatedQuote.getId(),
                updatedQuote.getText(),
                updatedQuote.getBook(),
                updatedQuote.getCharacter()
        );
    }

    //Создание новой цитаты
    public QuoteDto createQuote(String username, CreateQuoteDto createQuoteDto) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
        
        Quote quote = new Quote();
        // Генерируем ID вручную: берём max ID + 1
        Long nextId = getNextQuoteId();
        quote.setId(nextId);
        quote.setUser(user);
        quote.setText(createQuoteDto.getText());
        quote.setBook(createQuoteDto.getBook());
        quote.setCharacter(createQuoteDto.getCharacter());
        
        Quote savedQuote = quoteRepository.save(quote);
        return new QuoteDto(
                savedQuote.getId(),
                savedQuote.getText(),
                savedQuote.getBook(),
                savedQuote.getCharacter()
        );
    }
    
    // Получить следующий доступный ID для цитаты
    private Long getNextQuoteId() {
        try {
            List<Quote> allQuotes = quoteRepository.findAll();
            if (allQuotes.isEmpty()) {
                return 1L;
            }
            return allQuotes.stream()
                    .map(Quote::getId)
                    .max(Long::compareTo)
                    .orElse(0L) + 1;
        } catch (Exception e) {
            // Fallback: используем текущее время в миллисекундах как ID (уникален)
            return System.currentTimeMillis();
        }
    }

    // Удалить цитату
    public void deleteQuote(Long quoteId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Цитата не найдена: " + quoteId));
        quoteRepository.delete(quote);
    }
}