package com.example.service;

import com.example.dao.CategoryRepository;
import com.example.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> addNewCategory(Category category) {
        try {
            if(!Objects.isNull(category)&& !Objects.isNull(category.getName())) {
                if(!categoryRepository.existsByName(category.getName())) {
                    categoryRepository.save(category);
                    return new ResponseEntity<>("{\"message\":\"category added successfully\"}", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("{\"message\":\"category already exists\"}", HttpStatus.CONFLICT);
                }

            }else {
                return new ResponseEntity<>("{\"message\":\"Invalid data\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            logger.error("Exeption in addNewCategory : {}", e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        try {
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exeption in getAllCategory : {}", e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        try {
            if(!Objects.isNull(category)&& !Objects.isNull(category.getCategory_id())&& !Objects.isNull(category.getName())) {
                if(!categoryRepository.existsByName(category.getName())) {
                    Integer updateCount=categoryRepository.updateCategory(category.getName(), category.getCategory_id());
                    if(updateCount==0) {
                            return new ResponseEntity<>("{\"message\":\"not found category\"}", HttpStatus.BAD_REQUEST);
                    }else {
                            return new ResponseEntity<>("{\"message\":\"updated sucessfully\"}", HttpStatus.OK);
                    }
                }
            }else {
                return new ResponseEntity<>("{\"message\":\"category already exist\"}", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("{\"message\":\"Invalid data\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Exeption updateCategory : {}", e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
