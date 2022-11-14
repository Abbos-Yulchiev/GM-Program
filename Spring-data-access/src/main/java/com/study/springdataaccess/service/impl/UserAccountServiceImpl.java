package com.study.springdataaccess.service.impl;

import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.UserAccount;
import com.study.springdataaccess.entity.dto.UserAccountDTO;
import com.study.springdataaccess.exceptions.NotFoundException;
import com.study.springdataaccess.repository.UserAccountRepository;
import com.study.springdataaccess.repository.UserRepository;
import com.study.springdataaccess.service.UserAccountService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final Logger LOGGER = Logger.getLogger(UserAccountServiceImpl.class);


    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, ModelMapper mapper,
                                  UserRepository userRepository) {
        this.userAccountRepository = userAccountRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserAccount getUserAccountInfo(long userId) {
        return userAccountRepository.getUserAccountInfo(userId);
    }

    @Override
    public String createUserAccount(UserAccountDTO accountDTO) {

        User user = userRepository.findById(accountDTO.getUserId()).orElseThrow(() ->
                new NotFoundException("User not found id: [" + accountDTO.getUserId() + "]"));
        UserAccount userAccount = new UserAccount();
        userAccount.setUser(user);
        userAccount.setAccountNumber(accountDTO.getAccountNumber());
        userAccount.setBalance(accountDTO.getBalance());

        userAccountRepository.save(userAccount);
        LOGGER.info("User account successfully created for [" + user.getUsername() + "]");
        return "User account successfully created for [" + user.getUsername() + "]";
    }

    @Override
    public String updateUserAccount(long id, UserAccountDTO accountDTO) {

        UserAccount userAccount = userAccountRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found id: [" + id + "]"));
        mapper.map(accountDTO, userAccount);
        userAccountRepository.save(userAccount);

        LOGGER.info("User account successfully updated");
        return "User account successfully updated";
    }

    @Override
    public String deleteUserAccount(long id) {

        userAccountRepository.deleteById(id);
        LOGGER.info("User account successfully deleted");
        return "User account successfully deleted";
    }
}
