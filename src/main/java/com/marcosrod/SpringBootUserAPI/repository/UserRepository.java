package com.marcosrod.SpringBootUserAPI.repository;

import com.marcosrod.SpringBootUserAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
