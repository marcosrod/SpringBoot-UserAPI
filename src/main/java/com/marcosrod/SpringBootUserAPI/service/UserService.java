package com.marcosrod.SpringBootUserAPI.service;

import com.marcosrod.SpringBootUserAPI.dto.UserRequest;
import com.marcosrod.SpringBootUserAPI.dto.UserResponse;
import com.marcosrod.SpringBootUserAPI.exception.UserNotFoundException;
import com.marcosrod.SpringBootUserAPI.model.User;
import com.marcosrod.SpringBootUserAPI.model.UserPage;
import com.marcosrod.SpringBootUserAPI.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final UserNotFoundException USER_NOT_FOUND_EXCEPTION = new UserNotFoundException();

    @Autowired
    private UserRepository userRepository;

    public User save(UserRequest userRequest) {
        var user = User.of(userRequest);
        //TODO check user existence
        return userRepository.save(user);
    }

    public Page<UserResponse> getAll(UserPage userPage) {
        Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
        Pageable pageable = PageRequest.of(userPage.getPageNumber(),
                userPage.getPageSize(), sort);
        return userRepository.findAll(pageable).map(UserResponse::of);
    }

    public UserResponse getById(Integer id) {
        return UserResponse.of((userRepository.findById(id)).orElseThrow(() -> USER_NOT_FOUND_EXCEPTION));
    }

    public UserResponse updateById(UserRequest userRequest, Integer id) {
        var user = userRepository.findById(id).orElseThrow(() -> USER_NOT_FOUND_EXCEPTION);
        BeanUtils.copyProperties(userRequest, user);
        return UserResponse.of(userRepository.save(user));
    }

    public void deleteById(Integer id) {
        userRepository.findById(id).orElseThrow(() -> USER_NOT_FOUND_EXCEPTION);
        userRepository.deleteById(id);
    }
}

