package com.marcosrod.SpringBootUserAPI.repository;

import com.marcosrod.SpringBootUserAPI.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
    Page<User> findAll(Pageable page);
}
