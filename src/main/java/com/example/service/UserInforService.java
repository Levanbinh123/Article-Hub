package com.example.service;

import com.example.entity.UserInfo;
import com.example.entity.dtos.AuthRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserInforService {
    ResponseEntity<?> addNewUser( UserInfo userInfo);
    ResponseEntity<?> loginUser( AuthRequestDTO authRequest);
    ResponseEntity<?> getAllAppuser();

    ResponseEntity<?> updateUserStatus(UserInfo userInfo);

    ResponseEntity<?> checkToken();

    ResponseEntity<?> updateUser(UserInfo userInfo);
}
