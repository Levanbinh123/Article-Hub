package com.example.dao;

import com.example.entity.UserInfo;
import com.example.entity.dtos.UserInfoDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByPassword(String password);
    Optional<UserInfo> findByEmailAndPassword(String email, String password);
    @Query("select new com.example.entity.dtos.UserInfoDTO(u.username, u.email, u.status) from UserInfo u where u.isDeletable='true' and u.username not in (:username)")
    List<UserInfoDTO> getAllAppuser(String username);
    @Transactional
    @Modifying
    @Query("update UserInfo u set u.status=:status where u.id=:id and u.isDeletable='true'")
    Integer updateUserStatus(@Param("status") String status, @Param("id") Integer id);

    Optional<UserInfo> findById(int id);
}
