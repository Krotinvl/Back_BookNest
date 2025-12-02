package com.booknest.service;

import com.booknest.model.PagesPerDay;
import com.booknest.model.User;
import com.booknest.dto.PagesPerDayDto;
import com.booknest.repository.PagesPerDayRepository;
import com.booknest.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class PagesService {
    private final PagesPerDayRepository pagesPerDayRepository;
    private final UserRepository userRepository;

    public PagesService(PagesPerDayRepository pagesPerDayRepository, UserRepository userRepository) {
        this.pagesPerDayRepository = pagesPerDayRepository;
        this.userRepository = userRepository;
    }

    // Добавить или обновить количество страниц за конкретную дату для пользователя и обновление прочитаных за день страниц
    public PagesPerDayDto addOrUpdatePages(String username, LocalDate dateDay, Integer pages) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));

        PagesPerDay date = pagesPerDayRepository.findByUser_UsernameAndDateDay(username, dateDay)
                .orElseGet(() -> new PagesPerDay(user, dateDay, 0));

        Integer oldPages = date.getPages() == null ? 0 : date.getPages();
        date.setPages(pages);
        PagesPerDay saved = pagesPerDayRepository.save(date);

        // Увеличиваем activeDays при переходе 0 -> >0
        if ((oldPages == 0 || oldPages == null) && pages != null && pages > 0) {
            Integer active = user.getActiveDays() == null ? 0 : user.getActiveDays();
            user.setActiveDays(active + 1);
            userRepository.save(user);
        }

        return new PagesPerDayDto(saved.getDateDay(), saved.getPages());
    }

    public PagesPerDayDto incrementPages(String username, LocalDate dateDay, Integer delta) {
        if (delta == null || delta == 0) {
            return pagesPerDayRepository.findByUser_UsernameAndDateDay(username, dateDay)
                    .map(p -> new PagesPerDayDto(p.getDateDay(), p.getPages()))
                    .orElseGet(() -> new PagesPerDayDto(dateDay, 0));
        }

        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));

        PagesPerDay date = pagesPerDayRepository.findByUser_UsernameAndDateDay(username, dateDay)
                .orElseGet(() -> new PagesPerDay(user, dateDay, 0));

        Integer oldPages = date.getPages() == null ? 0 : date.getPages();
        int newPages = oldPages + delta;
        if (newPages < 0) newPages = 0;
        date.setPages(newPages);
        PagesPerDay saved = pagesPerDayRepository.save(date);

        // Увеличиваем activeDays 
        if ((oldPages == 0 || oldPages == null) && newPages > 0) {
            Integer active = user.getActiveDays() == null ? 0 : user.getActiveDays();
            user.setActiveDays(active + 1);
            userRepository.save(user);
        }

        return new PagesPerDayDto(saved.getDateDay(), saved.getPages());
    }

    // Получение количества страниц за последние 7 дней
    public List<PagesPerDayDto> getWeeklyPages(String username) {
        if (!userRepository.existsById(username)) {
            throw new RuntimeException("Пользователь не найден: " + username);
        }

        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(6);

        List<PagesPerDay> entries = pagesPerDayRepository.findByUser_UsernameAndDateDayBetween(username, start, end);

        Map<LocalDate, Integer> map = entries.stream()
                .collect(Collectors.toMap(PagesPerDay::getDateDay, e -> e.getPages()));

        List<PagesPerDayDto> result = new ArrayList<>();
        for (LocalDate dateDay = start; !dateDay.isAfter(end); dateDay = dateDay.plusDays(1)) {
            Integer page = map.getOrDefault(dateDay, 0);
            result.add(new PagesPerDayDto(dateDay, page));
        }

        return result;
    }

    // Добавить день в список 
    public PagesPerDayDto addDay(String username) {
        LocalDate today = LocalDate.now();
        return incrementPages(username, today, 0); 
    }
}
