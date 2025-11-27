package com.booknest.controller;

import com.booknest.dto.UserDto;
import com.booknest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")

public class UserController {

    private final UserService userService;
    


    public UserController(UserService userService){
        this.userService = userService;
    }


@GetMapping("/{username")
public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
    return userService.getUserByUsername(username)
           .map( ResponseEntity::ok)
           .orElse(ResponseEntity.notFound().build());

}
}