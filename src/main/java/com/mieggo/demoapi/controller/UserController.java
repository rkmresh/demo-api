package com.mieggo.demoapi.controller;

import com.mieggo.demoapi.entiy.User;
import com.mieggo.demoapi.exception.UserNotFoundException;
import com.mieggo.demoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User not found with id: "+userId));
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id")long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User not found with id: "+userId));
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        return userRepository.save(existingUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User not found with id: "+userId));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
