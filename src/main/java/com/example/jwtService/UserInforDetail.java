package com.example.jwtService;

import com.example.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserInforDetail implements UserDetails {

    private String name;
    private String password;
    private String status;
    private List<GrantedAuthority> authorities;

    public UserInforDetail(UserInfo userInfo) {
        name=userInfo.getUsername();
        password=userInfo.getPassword();
        status=userInfo.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        return name;// userInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
