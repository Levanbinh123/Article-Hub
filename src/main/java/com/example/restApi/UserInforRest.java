package com.example.restApi;

import com.example.entity.UserInfo;

import com.example.entity.dtos.AuthRequestDTO;
import com.example.service.UserInforService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/appUser")
public class UserInforRest {
    Logger logger = (Logger) LoggerFactory.getLogger(UserInforRest.class);
    Map<String, String> map = new HashMap<>();

    UserInforService userInforService;
    @Autowired
    public UserInforRest(UserInforService userInforService) {
        this.userInforService = userInforService;
    }

    @PostMapping(path="/appNewAppuser")
    ResponseEntity<?> appNewUser(@RequestBody UserInfo userInfo) {
        try {
            logger.info("appNewUser called");
            return userInforService.addNewUser(userInfo);
        } catch (Exception e) {
            logger.error("Exception in addUser : {}",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(path="/login")
    ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            return userInforService.loginUser(authRequest);

        } catch (Exception e) {
            logger.error("Exception in login : {}",e.getMessage());

        }

        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(path = "/getAllAppuser")
    ResponseEntity<?> getAllAppuser() {
        try {
            return userInforService.getAllAppuser();

        } catch (Exception e) {
            logger.error("Exception in login : {}",e.getMessage());

        }

        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/updateUserStatus")
    ResponseEntity<?> updateUserStatus(@RequestBody UserInfo userInfo) {
        try {
            return userInforService.updateUserStatus(userInfo);

        } catch (Exception e) {
            logger.error("Exception in updateStatus : {}",e.getMessage());

        }

        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/checkToken")
    ResponseEntity<?> checkToken() {
        return userInforService.checkToken();
    }
    @PostMapping("/updateUser")
    ResponseEntity<?> updateUser(@RequestBody UserInfo userInfo) {
        try {
            return userInforService.updateUser(userInfo);

        } catch (Exception e) {
            logger.error("Exception in updateUser : {}",e.getMessage());

        }

        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
