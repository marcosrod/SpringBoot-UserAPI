package com.marcosrod.SpringBootUserAPI.controller;

import com.marcosrod.SpringBootUserAPI.dto.UserRequest;
import com.marcosrod.SpringBootUserAPI.dto.UserResponse;
import com.marcosrod.SpringBootUserAPI.model.UserPage;
import com.marcosrod.SpringBootUserAPI.repository.UserRepository;
import com.marcosrod.SpringBootUserAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserResponse save(@RequestBody UserRequest userRequest){
        return UserResponse.of(userService.save(userRequest));
    }

    @GetMapping
    public Page<UserResponse> getAll(UserPage userPage){
        return userService.getAll(userPage);
    }

    @GetMapping(path="/{id}")
    public UserResponse getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PutMapping(path="/{id}")
    public UserResponse updateById(@RequestBody UserRequest userRequest, @PathVariable Integer id){
        return userService.updateById(userRequest, id);
    }

    @DeleteMapping(path="/{id}")
    public void deleteById(@PathVariable Integer id){
        userService.deleteById(id);
    }

}
