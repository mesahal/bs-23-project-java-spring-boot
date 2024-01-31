package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);

    Optional<User> findByEmail(String username);

    boolean existsByUserName(String userName);
}