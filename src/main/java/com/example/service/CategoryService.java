package com.example.service;

import com.example.entity.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> addNewCategory(Category category);

    ResponseEntity<?> getAllCategory();

    ResponseEntity<?> updateCategory(Category category);
}
