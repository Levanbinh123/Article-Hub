package com.example.dao;

import com.example.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);
    @Modifying
    @Transactional
    @Query("update Category c set c.name=:name where c.id =:id")
    Integer updateCategory(@Param("name") String name, @Param("id") Integer id);

}
