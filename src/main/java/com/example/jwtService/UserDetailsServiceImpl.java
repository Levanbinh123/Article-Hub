package com.example.jwtService;

import com.example.dao.UserInfoRepository;
import com.example.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        System.out.println("Authentication: " + username);
       return userInfo.map(UserInforDetail::new).orElseThrow(() -> new UsernameNotFoundException("not found"+username));

    }

}
