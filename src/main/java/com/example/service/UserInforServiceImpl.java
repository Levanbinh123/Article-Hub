package com.example.service;

import com.example.dao.UserInfoRepository;
import com.example.entity.UserInfo;
import com.example.entity.dtos.AuthRequestDTO;
import com.example.filter.JwtAuthFilter;
import com.example.jwtService.JwtService;
import com.example.jwtService.UserInforDetail;
import org.slf4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserInforServiceImpl implements UserInforService {
    Logger logger =  LoggerFactory.getLogger(UserInforServiceImpl.class);
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Override
    public ResponseEntity<?> addNewUser(UserInfo userInfo) {
        try {
            if (!validateInfo(userInfo))

            return new ResponseEntity<>("{\"message\":\"Missing required data.\"}", HttpStatus.BAD_REQUEST);

           Optional<UserInfo> db= userInfoRepository.findByUsername(userInfo.getUsername());

           if(db.isPresent()){
               return new ResponseEntity<>("{\"message\":\"Username Already Exit.\"}", HttpStatus.BAD_REQUEST);}
           userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
           userInfo.setIsDeletable("true");
           userInfo.setStatus("false");
           userInfo.setEmail(userInfo.getEmail().toLowerCase());
           userInfoRepository.save(userInfo);
           return new ResponseEntity<>("{\"message\":\"Singup successful\"}", HttpStatus.CREATED);
        }
        catch (Exception e) {
            logger.error("Exception in addUser : {}",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
//dang nhap
    @Override
    public ResponseEntity<?> loginUser(AuthRequestDTO authRequest) {
try {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    if(authentication.isAuthenticated()&&authentication!=null){
        UserInforDetail userInforDetail=(UserInforDetail) authentication.getPrincipal();
        if("true".equalsIgnoreCase(userInforDetail.getStatus())){
            return new ResponseEntity<>("{\"token\":\""+jwtService.generateToken(authRequest.getUsername().toLowerCase())+"\"}", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("{\"message\":\"wait for admin approval\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    else {
        throw new UsernameNotFoundException("Invalid username or password");
    }

} catch (BadCredentialsException e) {
    return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.UNAUTHORIZED);
}
catch (UsernameNotFoundException e) {
   throw new UsernameNotFoundException("Invalid username or password");
}
catch (Exception e) {
    logger.error("Exception in login : {}",e.getMessage(),e);
}

        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllAppuser() {
        try {
        return new ResponseEntity<>(userInfoRepository.getAllAppuser(jwtAuthFilter.getUsername()),HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception in getAllAppuser : {}",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateUserStatus(UserInfo userInfo) {
      try {
        if(!Objects.isNull(userInfo)&&!Objects.isNull(userInfo.getId())&&!Objects.isNull(userInfo.getStatus())){
            Integer updateCount=userInfoRepository.updateUserStatus(userInfo.getStatus(),userInfo.getId());
            if(updateCount==0){
                return new ResponseEntity<>("{\"message\":\"Id not found\"}", HttpStatus.BAD_REQUEST);

            }
            else {
                return new ResponseEntity<>("{\"message\":\"User updated successfully\"}", HttpStatus.OK);
            }

        }
        else {
            return new ResponseEntity<>("{\"message\":\"invalid date\"}", HttpStatus.BAD_REQUEST);
        }

        } catch (Exception e) {
            logger.error("Exception in UpdateUSerStatus : {}",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> checkToken() {
        return new ResponseEntity<>("{\"message\":\"true\"}", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUser(UserInfo userInfo) {
        try {
            Optional<UserInfo> db= userInfoRepository.findById(userInfo.getId());
            if(!db.isPresent()){
                return new ResponseEntity<>("{\"message\":\"not found UserId\"}", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            UserInfo userInfo1=db.get();
            userInfo1.setEmail(userInfo.getEmail().toLowerCase());
            userInfo1.setUsername(userInfo.getUsername().toLowerCase());
            userInfoRepository.save(userInfo1);return new ResponseEntity<>("{\"message\":\"User updated successfully\"}", HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception in UpdateUser : {}",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //kiem tra nuoi dung hop le
    private boolean validateInfo(UserInfo userInfo) {
        return !Objects.isNull(userInfo)&& StringUtils.hasText(userInfo.getUsername())&&StringUtils.hasText(userInfo.getEmail())&&StringUtils.hasText(userInfo.getPassword());
    }
}
