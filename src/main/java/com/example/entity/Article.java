package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="article")
@Data
@NoArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Integer article_id;
    @Column(name="title")
    private String title;
    @Lob
    @Column(name="content")
    private String content;
    @ManyToOne
@JoinColumn(name="category_id")
    private Category categories;
    @Column(name="publishcation_date")
    private Date publishcation_date;
    @Column(name="status")
    private String status;
//------------------
    @Transient
    private Integer category_id;
    @Transient
    private String category_name;

    public Article(Integer article_id, String title,
                   String content,  String status,Date publishcation_date,
                   Integer category_id, String category_name) {
        this.article_id = article_id;
        this.title = title;
        this.content = content;
        this.publishcation_date = publishcation_date;
        this.status = status;
        this.category_id = category_id;
        this.category_name = category_name;
    }
    public String checkForNullvalues(){
        if(title==null || title.equals("")){
            return "title is null or empty";
        }if(content==null || content.equals("")){
            return "content is null or empty";
        }if(category_id==null ){
            return "category_id is null or empty";
        }
        if (status==null || status.equals("")){
            return "status is null or empty";
        }
        return null;
    }
}
