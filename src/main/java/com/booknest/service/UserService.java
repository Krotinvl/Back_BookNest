package com.booknest.service;

import com.booknest.dto.UserRegistrationDto;
import com.booknest.dto.UpdateUserDto;
import com.booknest.dto.UserDto;
import com.booknest.model.User;
import com.booknest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // ИМПОРТ ДОБАВЛЕН
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.findById(username) 
                .map(this::convertToDto);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setDescription(user.getDescription());
        dto.setTelephone(user.getTelephone());
        dto.setActiveDays(user.getActiveDays());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setName(registrationDto.getName());
        user.setActiveDays(0);
        user.setDescription("");
        user.setTelephone("");
        
        return userRepository.save(user);
    }
    
    public UserDto updateUser(String username, UpdateUserDto updateUserDto) {
    User user = userRepository.findById(username)
            .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
    
    // Обновляем только переданные поля
    if (updateUserDto.getName() != null) {
        user.setName(updateUserDto.getName());
    }
    if (updateUserDto.getEmail() != null) {
        user.setEmail(updateUserDto.getEmail());
    }
    if (updateUserDto.getDescription() != null) {
        user.setDescription(updateUserDto.getDescription());
    }
    if (updateUserDto.getTelephone() != null) {
        user.setTelephone(updateUserDto.getTelephone());
    }
    
    User updatedUser = userRepository.save(user);
    return convertToDto(updatedUser);
}

    //Удаление пользователя по username

    public void deleteUser(String username) {
        if (!userRepository.existsById(username)) {
            throw new RuntimeException("Пользователь не найден: " + username);
        }
        userRepository.deleteById(username);
    }

}