package com.noel.fullstackbackend.controller;

import com.noel.fullstackbackend.exception.UserNotFoundException;
import com.noel.fullstackbackend.model.User;
import com.noel.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController means this class is a controller
// we can use this controller to handle HTTP requests
@RestController
//@crossOrigin allows us to handle cross-origin requests
@CrossOrigin("http://localhost:3000")
public class UserController {

    // @Autowired allows Spring to resolve and inject the UserRepository bean from the application context.
    // This means that Spring will automatically handle the creation and initialization of the UserRepository instance,
    // reducing boilerplate code and promoting loose coupling by depending on the UserRepository interface rather than a concrete implementation.
    @Autowired
    private UserRepository userRepository;

    // @PostMapping allows us to handle HTTP POST requests to the /user endpoint
    // @RequestBody allows us to bind the request body to the User object
    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
      return userRepository.findById(id).map(user -> {
          user.setUsername(newUser.getUsername());
          user.setName(newUser.getName());
          user.setEmail(newUser.getEmail());
          return userRepository.save(user);
      }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted";
    }
}
