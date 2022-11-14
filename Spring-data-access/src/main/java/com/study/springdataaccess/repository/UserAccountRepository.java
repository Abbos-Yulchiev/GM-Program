package com.study.springdataaccess.repository;

import com.study.springdataaccess.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {


    @Query(value = "SELECT * FROM public.user_account AS ua " +
            "WHERE ua.user_id = ?1", nativeQuery = true)
    UserAccount getUserAccountInfo(long userId);
}
