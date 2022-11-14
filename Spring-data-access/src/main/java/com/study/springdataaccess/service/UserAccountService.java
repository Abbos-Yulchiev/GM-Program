package com.study.springdataaccess.service;

import com.study.springdataaccess.entity.UserAccount;
import com.study.springdataaccess.entity.dto.UserAccountDTO;

public interface UserAccountService {

    UserAccount getUserAccountInfo(long userId);

    String createUserAccount(UserAccountDTO accountDTO);

    String updateUserAccount(long id, UserAccountDTO accountDTO);

    String deleteUserAccount(long id);
}
