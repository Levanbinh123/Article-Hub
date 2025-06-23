package com.example.restApi;

import com.example.entity.Category;
import com.example.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryRest {
    Logger logger = LoggerFactory.getLogger(CategoryRest.class);
    @Autowired
    CategoryService categoryService;

    @PostMapping("/addNewCategory")
    ResponseEntity<?> addNewCategory(@RequestBody Category category) {
        try {
            return categoryService.addNewCategory(category);
        } catch (Exception e) {
            logger.error("Exeption in addNewCategory : {}", e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/getAllCategory")
    ResponseEntity<?> getAllCategory() {
        try {
            return categoryService.getAllCategory();
        } catch (Exception e) {
            logger.error("Exeption in getAllCategory : {}", e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/updateCategory")
    ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            return categoryService.updateCategory(category);
        } catch (Exception e) {
            logger.error("Exeption in updateCategory : {}", e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
