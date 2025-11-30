package com.booknest.service;

import com.booknest.dto.QuoteDto;
import com.booknest.model.Quote;
import com.booknest.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    
    private final QuoteRepository quoteRepository;
    
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
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
}