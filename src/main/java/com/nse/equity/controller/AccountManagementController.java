package com.nse.equity.controller;

import com.nse.equity.entity.LoginEntity;
import com.nse.equity.model.login.UpdateCredential;
import com.nse.equity.model.login.UserCredential;
import com.nse.equity.model.login.UserLoginResponse;
import com.nse.equity.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountManagementController {

    @Autowired
    private AccountManagementService accountManagementService;

    @GetMapping(value = "/usersList")
    public ResponseEntity<List<LoginEntity>> getUserInfo() {
        List<LoginEntity> response = accountManagementService.getAllUserInfo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Optional<LoginEntity>> getCredential(@PathVariable(value = "id") Long id) {
        Optional<LoginEntity> response = accountManagementService.getUserInfo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(name = "Modifying existing user credentials", value = "/modifyUser")
    public ResponseEntity<UserLoginResponse> modifyUserCredential(@RequestBody UpdateCredential updateCredential) {
        UserLoginResponse modifiedUserCredential = accountManagementService.modifyUserCredential(updateCredential);
        return new ResponseEntity<>(modifiedUserCredential, HttpStatus.OK);
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<UserLoginResponse> addCredential(@RequestBody UserCredential userCredential) {
         UserLoginResponse savedUserCredential = accountManagementService.saveUserCredential(userCredential);
         return new ResponseEntity<>(savedUserCredential, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeUser/{id}")
    public ResponseEntity<UserLoginResponse> removeCredential(@PathVariable(value = "id") Long userId) {
        UserLoginResponse response = accountManagementService.removeUserInfo(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}