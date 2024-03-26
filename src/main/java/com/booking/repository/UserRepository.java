package com.booking.repository;

import com.booking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(Long Id);
    Optional<User> findByfirstName(String firstname);


    List<User> findAll();
}

