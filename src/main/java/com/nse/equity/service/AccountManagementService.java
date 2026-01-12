package com.nse.equity.service;

import com.nse.equity.entity.LoginEntity;
import com.nse.equity.model.login.UpdateCredential;
import com.nse.equity.model.login.UserCredential;
import com.nse.equity.model.login.UserLoginResponse;
import com.nse.equity.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountManagementService {

    private final LoginRepository loginRepository;

    public AccountManagementService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public UserLoginResponse saveUserCredential(UserCredential userCredential) {
        try{
            LoginEntity savedCredentials = userCredentialToLoginEntityMap(userCredential);
            loginRepository.save(savedCredentials);

            return successResponseMessage();
        }
        catch(Exception e){
            log.error("Exception occurred while saving user credentials");
            throw new RuntimeException("Exception occurred while saving user credentials");
        }
    }

    public List<LoginEntity> getAllUserInfo() {
        try {
            return loginRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<LoginEntity> getUserInfo(Long id) {
        try {
            return loginRepository.findById(id);
        } catch (Exception e) {
            log.error("Exception occurred while get user info");
            throw new RuntimeException(e);
        }
    }

    public UserLoginResponse removeUserInfo(Long userId) {
        try {
            loginRepository.deleteById(userId);

            return successResponseMessage();
        } catch (Exception e) {
            log.error("Exception occurred while removing user info");
            throw new RuntimeException(e);
        }
    }

    public UserLoginResponse modifyUserCredential(UpdateCredential userCredential) {
        try {
        log.info("modify user credential info");
        Optional<LoginEntity> credentialToUpdate = loginRepository.findById(userCredential.getId());
        log.info(credentialToUpdate.toString());
        credentialToUpdate.ifPresent(loginEntity -> loginRepository.save(updateUserCredential(loginEntity, userCredential)));

        return successResponseMessage();
        } catch (Exception e) {
            log.error("Exception occurred while modify user credential");
            throw new RuntimeException("Exception occurred while modify user credential");
        }
    }

    private LoginEntity userCredentialToLoginEntityMap(UserCredential userCredential) {
        return LoginEntity.builder()
                .loginName(userCredential.getLoginName())
                .loginPassword(userCredential.getLoginPassword())
                .createdBy("SYSTEM")
                .updatedBy("SYSTEM")
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    private LoginEntity updateUserCredential(LoginEntity updateEntity, UpdateCredential updatedCredential) {
        updateEntity.setId(updatedCredential.getId());
        updateEntity.setLoginName(updatedCredential.getLoginName());
        updateEntity.setLoginPassword(updatedCredential.getLoginPassword());
        updateEntity.setUpdatedBy("USER");
        updateEntity.setUpdatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        return updateEntity;
    }

    private static UserLoginResponse successResponseMessage() {
        return UserLoginResponse.builder()
                .message("success")
                .build();
    }
}