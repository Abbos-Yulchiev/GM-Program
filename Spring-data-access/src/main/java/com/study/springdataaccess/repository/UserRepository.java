package com.study.springdataaccess.repository;

import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "SELECT * FROM users WHERE email = ?1 ORDER BY id", nativeQuery = true)
    Optional<User> getUserByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Page<User> getUsersByName(String name, Pageable pageable);
}
