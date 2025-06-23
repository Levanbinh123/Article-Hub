package com.example.dao;

import com.example.entity.Article;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query("select new com.example.entity.Article(a.article_id,a.title,a.content,a.status,a.publishcation_date,a.categories.category_id,a.categories.name) from Article a where a.status=:status")
    List<Article> getAllArticle(@Param("status") String status);
    @Modifying
    @Transactional
    @Query("update Article a set a.title=:title, a.content=:content,a.categories.category_id=:categoryId,a.publishcation_date=:publiccation_date,a.status=:status where a.article_id=:id")
    Integer updateArticle(@Param("title") String title, @Param("content") String content,
                          @Param("categoryId") Integer categoryId,@Param("publiccation_date")
                          Date publiccation_date,@Param("status")
                          String status,@Param("id") Integer id);
    //test
    @Modifying
    @Transactional
    @Query("delete from Article a where a.article_id=:id")
    Integer deleteArticle(@Param("id") Integer id);
}
