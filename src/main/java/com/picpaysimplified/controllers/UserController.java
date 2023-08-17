package com.picpaysimplified.controllers;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.dto.UserDTO;
import com.picpaysimplified.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
