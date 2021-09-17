package com.marcosrod.SpringBootUserAPI.controller;

import com.marcosrod.SpringBootUserAPI.model.User;
import com.marcosrod.SpringBootUserAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path="/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users;
        users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(userRepository.findById(id));
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<User> updateById(@PathVariable Integer id, @RequestBody User newUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    User userUpdated = userRepository.save(user);
                    return ResponseEntity.ok().body(userUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Optional<User>> deleteById(@PathVariable Integer id){
        try{
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
