package com.example.entity.dtos;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String username;
    private String email;
    private String status;

    public UserInfoDTO(String username, String email, String status) {
        this.username = username;
        this.email = email;
        this.status = status;
    }
}
