package com.example.service;

import com.example.dao.ArticleRepository;
import com.example.dao.CategoryRepository;
import com.example.entity.Article;
import com.example.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addNewArticle(Article article) {
        try {
            if(!Objects.isNull(article)){
                //kiem tra null in entity

                String errorKeyValue=article.checkForNullvalues();
                if(!Objects.isNull(errorKeyValue)){
                    article.setPublishcation_date(new Date());
                    article.setCategories(new Category(article.getCategory_id()));
                    articleRepository.save(article);
                    return new ResponseEntity<>("{\"message\":\"Article added sussessfully\"}", HttpStatus.OK);

                }
                else {
                    return new ResponseEntity<>("{\"message\":\"Invalid value for("+errorKeyValue+")\"}", HttpStatus.BAD_REQUEST);
                }


            }
            return new ResponseEntity<>("{\"message\":\"Invalid date\"}", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Exeption in addNewArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllArticle() {
        try {
            return ResponseEntity.ok(articleRepository.findAll());

        } catch (Exception e) {
            log.error("Exeption in getAllArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllPublishedArticle() {
        try {
            return ResponseEntity.ok(articleRepository.getAllArticle("Published"));

        } catch (Exception e) {
            log.error("Exeption in getAllArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> deleteArticle(Integer id) {
        try {
            if(!Objects.isNull(id)){
                articleRepository.deleteArticle(id);
            }
            return new ResponseEntity<>("{\"message\":\"Article deleted sussessfully\"}", HttpStatus.OK);


        } catch (Exception e) {
            log.error("Exeption in deleteArticle : {} ",e.getMessage());
        }
        return new ResponseEntity<>("{\"message\":\"something_went_wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<?> updateArticle(Article article) {
        try {
            String errorKeyValue = article.checkForNullvalues();
            // Kiểm tra đầu vào
            if (errorKeyValue != null || article.getArticle_id() == null) {
                return new ResponseEntity<>("{\"message\":\"invalid value for(" + errorKeyValue + ")\"}", HttpStatus.BAD_REQUEST);
            }
            // Lấy Category từ DB
            Optional<Category> optionalCategory = categoryRepository.findById(article.getCategory_id());
            if (optionalCategory.isEmpty()) {
                return new ResponseEntity<>("{\"message\":\"category_id does not exist\"}", HttpStatus.BAD_REQUEST);
            }
            // Gán category
            article.setCategories(optionalCategory.get());
            article.setPublishcation_date(new Date());

            articleRepository.save(article);

            return new ResponseEntity<>(
                    "{\"message\":\"Article updated successfully\"}",
                    HttpStatus.OK
            );

        } catch (Exception e) {
            log.error("Exception in updateArticle: {}", e.getMessage(), e);
            return new ResponseEntity<>(
                    "{\"message\":\"something_went_wrong\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
