package com.example.TestingSpringApp.repos;


import com.example.TestingSpringApp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    Iterable<User> findUsersByEmail(String email);


}
