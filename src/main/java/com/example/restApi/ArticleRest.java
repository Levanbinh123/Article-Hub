package com.example.restApi;

import com.example.entity.Article;
import com.example.service.ArticleService;
import com.example.service.ArticleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleRest {
    Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    ArticleService articleService;
    @PostMapping("/addNewArticle")
    ResponseEntity<?> addNewArticle(@RequestBody Article article) {
        try {
            return articleService.addNewArticle(article);
        }
        catch (Exception e) {
            logger.error("Exeption in addNewArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/getAllArticle")
    ResponseEntity<?> getAllArticle() {
        try {
            return articleService.getAllArticle();
        }
        catch (Exception e) {
            logger.error("Exeption in getAllArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/getAllPublishedArticle")
    ResponseEntity<?> getAllPublishedArticle() {
        try {
            return articleService.getAllPublishedArticle();
        }
        catch (Exception e) {
            logger.error("Exeption in getAllPublishedArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/updateArticle")
    ResponseEntity<?> updateArticle(@RequestBody Article article) {
        try {
            return articleService.updateArticle(article);
        }
        catch (Exception e) {
            logger.error("Exeption in updateArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/deleteArticle/{id}")
    ResponseEntity<?> deleteArticle(@PathVariable Integer id) {
        try {
            return articleService.deleteArticle(id);
        }
        catch (Exception e) {
            logger.error("Exeption in deleteArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
