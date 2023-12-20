package com.example.crud15hours.controller;

import com.example.crud15hours.exception.UserNotFoundException;
import com.example.crud15hours.model.User;
import com.example.crud15hours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/Users")
     User savUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/Users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("Users/{id}")
    User findUserById(@PathVariable Long id){
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("Users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("Users/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id: "+id +"has been deleted success";
    }
}
